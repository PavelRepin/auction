package training.task.dao.api;

import training.task.bean.Bidder;
import training.task.dao.api.exception.DAOException;

import java.util.List;

public interface BidderDAO {

    /**
     * Inserts bidder into bidder table in db.
     *
     * @param bidder the operand to use for insertion.
     * @throws training.task.dao.api.exception.DAOException
     */
    void insertBidder(Bidder bidder) throws DAOException;

    /**
     * Updates bidder in the bidder table in db.
     *
     * @param bidder the operand to use for update.
     * @throws training.task.dao.api.exception.DAOException
     */
    void updateBidder(Bidder bidder) throws DAOException;

    /**
     * Gets bidder by login.
     *
     * @param login the operand to use as bidder's login.
     * @return the bidder by login.
     * @throws training.task.dao.api.exception.DAOException
     */
    Bidder getBidderByLogin(String login) throws DAOException;

    /**
     * Gets bidder's password by its id.
     *
     * @param id the operand to use as ID of bidder.
     * @return password.
     * @throws training.task.dao.api.exception.DAOException
     */
    String getBidderPasswordById(int id) throws DAOException;

    /**
     * Updates bidder's local in db.
     *
     * @param login the operand to use as bidder's login.
     * @param local the operand to use as bidder's local.
     * @throws training.task.dao.api.exception.DAOException
     */
    void updateLocal(String login, String local) throws DAOException;

    /**
     * Updates bidder's isAdmin value in db.
     *
     * @param idBidder the operand to use as ID of bidder.
     * @param isAdmin  the operand to use as bidder's isAdmin value.
     * @throws training.task.dao.api.exception.DAOException
     */
    void updateIsAdmin(int idBidder, boolean isAdmin) throws DAOException;

    /**
     * Updates bidder's isBlocked value in db.
     *
     * @param idBidder  the operand to use as ID of bidder.
     * @param isBlocked the operand to use as bidder's isBlocked value.
     * @throws training.task.dao.api.exception.DAOException
     */
    void updateIsBlocked(int idBidder, boolean isBlocked) throws DAOException;

    /**
     * Gets all bidders page.
     *
     * @param start    the operand to use in sql query for limit.
     * @param quantity the operand to use in sql query for limit.
     * @return the list of bidders.
     * @throws training.task.dao.api.exception.DAOException
     */
    List<Bidder> getAllBiddersPage(int start, int quantity) throws DAOException;

    /**
     * Gets bidder by id.
     *
     * @param id the operand to use as ID of bidder.
     * @return the bidder by id.
     * @throws training.task.dao.api.exception.DAOException
     */
    Bidder getBidderById(int id) throws DAOException;

    /**
     * Gets all bidders count.
     *
     * @return the count of all bidders.
     * @throws training.task.dao.api.exception.DAOException
     */
    int getAllBiddersCount() throws DAOException;

    /**
     * Checks login if it is not used.
     *
     * @param login the operand to use as bidder's login.
     * @return 0 if login is not used, otherwise 1.
     * @throws training.task.dao.api.exception.DAOException
     */
    int checkLogin(String login) throws DAOException;
}
