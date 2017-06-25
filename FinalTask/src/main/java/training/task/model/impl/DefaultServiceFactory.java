package training.task.model.impl;

import training.task.model.api.AuctionService;
import training.task.model.api.BidderService;
import training.task.model.api.LotService;
import training.task.model.api.ServiceFactory;

public class DefaultServiceFactory extends ServiceFactory {

    private final BidderServiceImpl bidderServiceImpl = new BidderServiceImpl();
    private final LotServiceImpl lotServiceImpl = new LotServiceImpl();
    private final AuctionServiceImpl auctionServiceImpl = new AuctionServiceImpl();

    @Override
    public BidderService getBidderService() {
        return bidderServiceImpl;
    }

    @Override
    public LotService getLotService() {
        return lotServiceImpl;
    }

    @Override
    public AuctionService getAuctionService() {
        return auctionServiceImpl;
    }
}
