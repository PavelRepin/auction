package training.task.model.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.bean.Lot;
import training.task.bean.Sale;
import training.task.dao.api.AuctionDAO;
import training.task.dao.api.BidderDAO;
import training.task.dao.api.DAOFactory;
import training.task.dao.api.LotDAO;
import training.task.dao.api.exception.DAOException;
import training.task.model.api.AuctionService;
import training.task.model.api.exception.ServiceException;

import java.util.*;

import static java.lang.System.currentTimeMillis;


public class AuctionServiceImpl implements AuctionService {

    protected static final Logger logger = Logger.getLogger(AuctionService.class);

    protected static final double BID_PRICE_MULTIPLIER = 1.15;

    private final Map<Integer, TimerTask> cancelTaskByLotId = new HashMap<Integer, TimerTask>();
    private final Timer timer = new Timer("Auction timer", true);

    @Override
    public synchronized void placeLot(int idBidder, int idLot, int stateZero) throws ServiceException {
        try {
            LotDAO lotDAO = DAOFactory.getInstance().getLotDAO();

            Lot lot = lotDAO.getLotById(idLot);

            double price = lot.getStartPrice();

            long currentTime = currentTimeMillis();
            lot.setCreatedAt(currentTime);
            lot.setCurrentPrice(price);
            lot.setOwnerBidder(stateZero);
            lot.setSellerBidder(idBidder);
            lot.setCloseAt(lot.getCreatedAt() + lot.getCloseAt());

            lotDAO.updateLot(lot);

            long closeAt = lot.getCloseAt();
            Date closeAtDate = new Date(closeAt);

            CloseLotTask closeTask = new CloseLotTask(idLot);
            timer.schedule(closeTask, closeAtDate);

            cancelTaskByLotId.put(idLot, closeTask);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public synchronized void closeLot(int idLot) throws ServiceException {
        try {
            LotDAO lotDAO = DAOFactory.getInstance().getLotDAO();
            BidderDAO bidderDAO = DAOFactory.getInstance().getBidderDAO();
            AuctionDAO auctionDAO = DAOFactory.getInstance().getAuctionDAO();

            Lot lot = lotDAO.getLotById(idLot);

            int lastBidder = lot.getLastBidder();

            if (lastBidder == 0) {
                lot.setOwnerBidder(lot.getSellerBidder());
            } else {

                Bidder seller = bidderDAO.getBidderById(lot.getSellerBidder());
                seller.setMoney(seller.getMoney() + lot.getCurrentPrice());

                bidderDAO.updateBidder(seller);

                lot.setOwnerBidder(lastBidder);
                lot.setLastBidder(0);

                lotDAO.updateLot(lot);

                TimerTask closeTask = cancelTaskByLotId.remove(idLot);
                if (closeTask != null) {
                    closeTask.cancel();
                }
                Sale sale = new Sale();
                sale.setSoldAt(currentTimeMillis());
                sale.setPrice(lot.getCurrentPrice());
                sale.setIdBidder(lot.getOwnerBidder());
                sale.setIdLot(lot.getId());
                auctionDAO.insertSale(sale);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public synchronized void bidLot(int idBidder, int idLot) throws ServiceException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();

            LotDAO lotDAO = daoFactory.getLotDAO();
            AuctionDAO auctionDAO = daoFactory.getAuctionDAO();
            BidderDAO bidderDAO = daoFactory.getBidderDAO();

            Bidder bidder = bidderDAO.getBidderById(idBidder);
            Lot lot = lotDAO.getLotById(idLot);

            if (lot.getOwnerBidder() == 0 && lot.getSellerBidder() != 0 && lot.getSellerBidder() != idBidder) {

                double price = lot.getCurrentPrice();
                double newPrice = price * BID_PRICE_MULTIPLIER;

                if (bidder.getMoney() < newPrice) {
                    throw new ServiceException("Not enough money!");
                } else {
                    List<Integer> lotsBidderBid = auctionDAO.getAllLotsBidderBid(idBidder);
                    if (!lotsBidderBid.contains(idLot)) {
                        auctionDAO.insertBid(idBidder, idLot);
                    }
                    Bidder lastBidder = bidderDAO.getBidderById(lot.getLastBidder());
                    bidder.setMoney(bidder.getMoney() - newPrice);
                    lastBidder.setMoney(lastBidder.getMoney() + price);
                    bidderDAO.updateBidder(bidder);
                    bidderDAO.updateBidder(lastBidder);
                    lot.setCurrentPrice(newPrice);
                    lot.setLastBidder(idBidder);
                }

            } else {
                throw new ServiceException("This lot is already closed.");
            }

            lotDAO.updateLot(lot);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private class CloseLotTask extends TimerTask {

        private final int idLot;

        public CloseLotTask(int idLot) {
            this.idLot = idLot;
        }

        @Override
        public void run() {
            try {
                closeLot(idLot);
            } catch (Throwable e) {
                logger.warn("Error occurred in timer thread: ", e);
            }
        }

    }

}
