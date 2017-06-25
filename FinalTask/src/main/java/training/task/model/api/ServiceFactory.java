package training.task.model.api;

import training.task.model.impl.DefaultServiceFactory;

public abstract class ServiceFactory {
    public static ServiceFactory getInstance() {
        return Impl.INSTANCE;
    }

    public abstract BidderService getBidderService();

    public abstract LotService getLotService();

    public abstract AuctionService getAuctionService();

    private static class Impl {
        private static final ServiceFactory INSTANCE = new DefaultServiceFactory();
    }
}
