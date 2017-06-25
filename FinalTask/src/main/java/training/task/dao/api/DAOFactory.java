package training.task.dao.api;

import training.task.dao.impl.DefaultDAOFactory;

public abstract class DAOFactory {
    public static DAOFactory getInstance() {
        return Implementation.INSTANCE;
    }

    public abstract BidderDAO getBidderDAO();
    public abstract LotDAO getLotDAO();
    public abstract AuctionDAO getAuctionDAO();

    private static class Implementation {
        private static final DAOFactory INSTANCE = new DefaultDAOFactory();
    }
}
