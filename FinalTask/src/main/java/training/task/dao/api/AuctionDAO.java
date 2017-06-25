package training.task.dao.api;

import training.task.bean.Sale;
import training.task.dao.api.exception.DAOException;

import java.util.List;

/**
 * Provider of data from database for Auction logic.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public interface AuctionDAO {

    /**
     * Removes last sale from db.
     *
     * @throws training.task.dao.api.exception.DAOException
     */
    void removeLastSale() throws DAOException;

    /**
     * Inserts sale into sale table in database.
     *
     * @param sale the operand to use for insertion.
     * @throws training.task.dao.api.exception.DAOException
     */
    void insertSale(Sale sale) throws DAOException;

    /**
     * Inserts bid info into bi table in database.
     *
     * @param idBidder the operand to use as ID of bidder.
     * @param idLot    the operand to use as ID of lot.
     * @throws training.task.dao.api.exception.DAOException
     */
    void insertBid(int idBidder, int idLot) throws DAOException;

    /**
     * Gets all lots that bidder bid.
     *
     * @param idBidder the operand to use as ID of bidder.
     * @return the list of lot ids.
     * @throws training.task.dao.api.exception.DAOException
     */
    List<Integer> getAllLotsBidderBid(int idBidder) throws DAOException;
}
