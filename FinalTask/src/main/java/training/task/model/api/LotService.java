package training.task.model.api;

import training.task.bean.Lot;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;


public interface LotService {

    /**
     * Gets lots for cart page.
     *
     * @param idBidder id of bidder to use in sql query to get the list.
     * @return list of lots for cart page.
     * @throws training.task.model.api.exception.ServiceException
     */
    List<Lot> getLotsForCartPage(int idBidder) throws ServiceException;

    /**
     * Gets lots page by category.
     *
     * @param category category of lot to use in sql query to get lots by it.
     * @param page the number of page to get.
     * @return list - page of lots by category.
     * @throws training.task.model.api.exception.ServiceException
     */
    List<Lot> getLotsPageByCategory(String category, int page) throws ServiceException;

    /**
     * Gets lots quantity.
     *
     * @param category category of lot to use in sql query to get lots quantity.
     * @return lots quantity by category.
     * @throws training.task.model.api.exception.ServiceException
     */
    int getLotsQuantity(String category) throws ServiceException;

    /**
     * Signs up a bidder.
     *
     * @param lotsQuantity quantity of lots.
     * @return number of pages for such a quantity.
     * @throws training.task.model.api.exception.ServiceException
     */
    int getNumberOfPages(int lotsQuantity) throws ServiceException;

    /**
     * Gets all the lots list.
     *
     * @return all lots list.
     * @throws training.task.model.api.exception.ServiceException
     */
    List<Lot> getAllLots() throws ServiceException;

    /**
     * Puts lot in a suggest state.
     *
     * @param name name of lot to be suggested.
     * @param category category of lot to be suggested.
     * @param author author of lot to be suggested.
     * @param year year of lot to be suggested.
     * @param description description of lot to be suggested.
     * @param startPrice start price of lot to be suggested.
     * @param hoursBeforeClose hours before close of lot to be suggested.
     * @param image image of lot to be suggested.
     * @param ownerBidder owner bidder of lot to be suggested.
     * @throws training.task.model.api.exception.ServiceException
     */
    void suggestLot(String name, String category, String author, int year, String description, double startPrice,
                    long hoursBeforeClose, Part image, int ownerBidder) throws ServiceException;

    /**
     * Gets all suggestions page.
     *
     * @param page the number of page to get.
     * @return list - page of all suggestions.
     * @throws training.task.model.api.exception.ServiceException
     */
    List<Lot> getAllSuggestedLotsPage(int page) throws ServiceException;

    /**
     * Accept/rejects suggested lot.
     *
     * @param idLot id of lot to be accepted/rejected.
     * @param acceptReject checks if we should accept or reject the lot.
     * @param idBidder id of bidder that suggested the lot.
     * @throws training.task.model.api.exception.ServiceException
     */
    void acceptRejectLot(int idLot, String acceptReject, int idBidder) throws ServiceException;

    /**
     * Suggest lot from cart page.
     *
     * @param id id of lot to be suggested.
     * @param description description of lot.
     * @param startPrice new start price of lot.
     * @param hours hours after lot closes.
     * @throws training.task.model.api.exception.ServiceException
     */
    void suggestLotFromCart(int id, String description, double startPrice, int hours) throws ServiceException;

    /**
     * Gets all suggested lots quantity.
     *
     * @return all suggested lots quantity.
     * @throws training.task.model.api.exception.ServiceException
     */
    int getAllSuggestedLotsQuantity() throws ServiceException;

    /**
     * Cancels lot from suggestion state to own state.
     *
     * @param idLot id of lot to be canceled.
     * @param idBidder id of bidder who suggested the lot.
     * @throws training.task.model.api.exception.ServiceException
     */
    void cancelLot(int idLot, int idBidder) throws ServiceException;

}
