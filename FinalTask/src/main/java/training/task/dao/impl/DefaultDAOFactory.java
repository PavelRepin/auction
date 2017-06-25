package training.task.dao.impl;

import training.task.dao.api.AuctionDAO;
import training.task.dao.api.BidderDAO;
import training.task.dao.api.DAOFactory;
import training.task.dao.api.LotDAO;

public class DefaultDAOFactory extends DAOFactory {
    private final BidderDAOImpl bidderDAOImpl = new BidderDAOImpl();
    private final LotDAOImpl lotDAOImpl = new LotDAOImpl();
    private final AuctionDAOImpl auctionDAOImpl = new AuctionDAOImpl();


    @Override
    public BidderDAO getBidderDAO() {
        return bidderDAOImpl;
    }

    @Override
    public LotDAO getLotDAO() {
        return lotDAOImpl;
    }

    @Override
    public AuctionDAO getAuctionDAO() {
        return auctionDAOImpl;
    }
}
