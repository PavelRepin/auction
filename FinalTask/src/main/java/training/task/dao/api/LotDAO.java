package training.task.dao.api;

import training.task.bean.Lot;
import training.task.dao.api.exception.DAOException;

import java.util.List;


public interface LotDAO {

    /**
     * Gets lots page by category.
     *
     * @param category the operand to use as lot's category.
     * @param start    the operand to use in sql query for limit.
     * @param quantity the operand to use in sql query for limit.
     * @return the list - page of lots by category.
     * @throws training.task.dao.api.exception.DAOException
     */
    List<Lot> getLotsPageByCategory(String category, int start, int quantity) throws DAOException;

    /**
     * Gets all lots page.
     *
     * @param start    the operand to use in sql query for limit.
     * @param quantity the operand to use in sql query for limit.
     * @return the list - page of all lots.
     * @throws training.task.dao.api.exception.DAOException
     */
    List<Lot> getAllLotsPage(int start, int quantity) throws DAOException;

    /**
     * Gets lot by id.
     *
     * @param id the operand to use as lot's id.
     * @return the lot by id.
     * @throws training.task.dao.api.exception.DAOException
     */
    Lot getLotById(int id) throws DAOException;

    /**
     * Gets lots that bidder suggested, selling now or owning now and bargaining now.
     *
     * @param idBidder the operand to use as bidder's id.
     * @return the list of lots that that bidder suggested, selling now or owning now and bargaining now.
     * @throws training.task.dao.api.exception.DAOException
     */
    List<Lot> getLotsBidderSuggestedSellingOwnBargaining(int idBidder) throws DAOException;

    /**
     * Gets all lots count.
     *
     * @return the all lots count.
     * @throws training.task.dao.api.exception.DAOException
     */
    int getAllLotsCount() throws DAOException;

    /**
     * Gets all bidders page.
     *
     * @param category the operand to use in sql query in where statement for lot category.
     * @return the lots count by category.
     * @throws training.task.dao.api.exception.DAOException
     */
    int getLotsCountByCategory(String category) throws DAOException;

    /**
     * Updates the state of lot.
     *
     * @param idLot  the operand to use as the lot's id.
     * @param owner  the operand to use as the lot's ownerBidder.
     * @param seller the operand to use as the lot's sellerBidder.
     * @throws training.task.dao.api.exception.DAOException
     */
    void updateLotOwnerSeller(int idLot, int owner, int seller) throws DAOException;

    /**
     * Inserts lot.
     *
     * @param lot the operand to use as lot for insertion.
     * @throws training.task.dao.api.exception.DAOException
     */
    void insertLot(Lot lot) throws DAOException;

    /**
     * Updates lot.
     *
     * @param lot the operand to use as lot for update.
     * @throws training.task.dao.api.exception.DAOException
     */
    void updateLot(Lot lot) throws DAOException;

    /**
     * Gets suggested lots page.
     *
     * @param start    the operand to use in sql query for limit.
     * @param quantity the operand to use in sql query for limit.
     * @return the list of all suggested lots.
     * @throws training.task.dao.api.exception.DAOException
     */
    List<Lot> getSuggestedLotsPage(int start, int quantity) throws DAOException;

    /**
     * Gets all suggestions count.
     *
     * @return the list of bidders.
     * @throws training.task.dao.api.exception.DAOException
     */
    int getAllSuggestionsCount() throws DAOException;

}
