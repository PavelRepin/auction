package training.task.model.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import training.task.bean.Bidder;
import training.task.bean.Lot;
import training.task.dao.api.AuctionDAO;
import training.task.dao.api.BidderDAO;
import training.task.dao.api.DAOFactory;
import training.task.dao.api.LotDAO;
import training.task.dao.api.exception.DAOException;

public class AuctionServiceImplTest {
    private static Lot LOT = new Lot();
    private static Bidder SELLER = new Bidder();
    @Before
    public final void before() {
        try {
            int id = 13;
            LotDAO lotDAO = DAOFactory.getInstance().getLotDAO();
            BidderDAO bidderDAO = DAOFactory.getInstance().getBidderDAO();
            LOT = lotDAO.getLotById(id);
            int idSeller = LOT.getSellerBidder();
            SELLER = bidderDAO.getBidderById(1);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCloseLot() throws Exception {
        AuctionServiceImpl auctionService = new AuctionServiceImpl();
        LotDAO lotDAO = DAOFactory.getInstance().getLotDAO();
        Lot lot = lotDAO.getLotById(13);
        lot.setLastBidder(1);
        lotDAO.updateLot(lot);
        auctionService.closeLot(13);
        Assert.assertEquals("Close lot is not working properly.", lotDAO.getLotById(13).getLastBidder(), 0);
    }

    @After
    public final void after() {
        LotDAO lotDAO = DAOFactory.getInstance().getLotDAO();
        BidderDAO bidderDAO = DAOFactory.getInstance().getBidderDAO();
        AuctionDAO auctionDAO = DAOFactory.getInstance().getAuctionDAO();
        try {
            bidderDAO.updateBidder(SELLER);
            lotDAO.updateLot(LOT);
            auctionDAO.removeLastSale();
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}