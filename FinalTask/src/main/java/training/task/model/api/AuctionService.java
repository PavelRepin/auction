package training.task.model.api;

import training.task.model.api.exception.ServiceException;

public interface AuctionService {

    /**
     * Places a lot on the auction.
     *
     * @param idBidder  id of bidder who places the lot.
     * @param idLot     id of lot to be placed.
     * @param stateZero state zero for making selling state of lot.
     * @throws training.task.model.api.exception.ServiceException
     */
    void placeLot(int idBidder, int idLot, int stateZero) throws ServiceException;

    /**
     * Closes the placed lot at the current price.
     *
     * @param idLot id of lot to be closed.
     * @throws training.task.model.api.exception.ServiceException
     */
    void closeLot(int idLot) throws ServiceException;

    /**
     * Bids the placed lot.
     *
     * @param idBidder id of bidder who wants to bid lot.
     * @param idLot    id of lot to be bid.
     * @throws training.task.model.api.exception.ServiceException
     */
    void bidLot(int idBidder, int idLot) throws ServiceException;

}
