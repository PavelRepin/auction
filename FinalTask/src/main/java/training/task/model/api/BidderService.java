package training.task.model.api;

import training.task.bean.Bidder;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface BidderService {

    /**
     * Signs up a bidder.
     *
     * @param login login of bidder who want to sign up.
     * @param password password of bidder who want to sign up.
     * @param email email of bidder who want to sign up.
     * @param age age of bidder who want to sign up.
     * @param local local of bidder who want to sign up.
     * @return signed up bidder.
     * @throws training.task.model.api.exception.ServiceException
     */
    Bidder signUp(String login, String password, String email, int age, String local) throws ServiceException;

    /**
     * Signs in a bidder.
     *
     * @param login login of bidder who want to sign in.
     * @param password password of bidder who want to sign in.
     * @return signed in bidder.
     * @throws training.task.model.api.exception.ServiceException
     */
    Bidder signIn(String login, String password) throws ServiceException;

    /**
     * Gets bidder by login.
     *
     * @param login login of bidder.
     * @return bidder by login.
     * @throws training.task.model.api.exception.ServiceException
     */
    Bidder getBidderByLogin(String login) throws ServiceException;

    /**
     * Updates bidder.
     *
     * @param bidder the bidder with updated data.
     * @param newPassword new password.
     * @param repeatedNewPassword repeated new password.
     * @throws training.task.model.api.exception.ServiceException
     */
    void updateProfile(Bidder bidder, String newPassword, String repeatedNewPassword) throws ServiceException;

    /**
     * Signs out a bidder.
     *
     * @param cookies cookies of the bidder who want to sign out.
     * @param request request to get the coolies from.
     * @param response response to set the cookies.
     * @throws training.task.model.api.exception.ServiceException
     */
    void signOut(Cookie[] cookies, HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    /**
     * Sets or removes admin rights.
     *
     * @param idBidder id of bidder to set/remove rights.
     * @param isAdmin checks if he is admin already.
     * @throws training.task.model.api.exception.ServiceException
     */
    void setRemoveAdminRights(int idBidder, boolean isAdmin) throws ServiceException;

    /**
     * Changes bidder local.
     *
     * @param login login of bidder.
     * @param local local of bidder.
     * @throws training.task.model.api.exception.ServiceException
     */
    void changeLocal(String login, String local) throws ServiceException;

    /**
     * Locks/unlocks the bidder.
     *
     * @param idBidder id of bidder to lock/unlock.
     * @param isBlocked checks if he is blocked already.
     * @throws training.task.model.api.exception.ServiceException
     */
    void lockUnlockBidder(int idBidder, boolean isBlocked) throws ServiceException;

    /**
     * Gets all bidders page.
     *
     * @param page the number of page to get.
     * @return list - page of all bidders.
     * @throws training.task.model.api.exception.ServiceException
     */
    List<Bidder> getAllBiddersPage(int page) throws ServiceException;

    /**
     * Gets bidder by id.
     *
     * @param idBidder id of bidder to get.
     * @return the bidder by id.
     * @throws training.task.model.api.exception.ServiceException
     */
    Bidder getBidderById(int idBidder) throws ServiceException;

    /**
     * Gets number of pages of all bidders.
     *
     * @param biddersQuantity all bidders count.
     * @return number of pages of all bidders.
     * @throws training.task.model.api.exception.ServiceException
     */
    int getNumberOfPages(int biddersQuantity) throws ServiceException;

    /**
     * Gets quantity of all bidders.
     *
     * @return all bidders quantity.
     * @throws training.task.model.api.exception.ServiceException
     */
    int getBiddersQuantity() throws ServiceException;
}