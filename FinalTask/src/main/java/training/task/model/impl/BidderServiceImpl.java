package training.task.model.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.controller.ParameterName;
import training.task.dao.api.BidderDAO;
import training.task.dao.api.DAOFactory;
import training.task.dao.api.exception.DAOException;
import training.task.model.api.BidderService;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BidderServiceImpl implements BidderService {
    private static Logger logger = Logger.getLogger(BidderServiceImpl.class);
    private static final int quantityOnPage = 8;

    public Bidder signUp(String login, String password, String email, int age, String local) throws ServiceException {
        logger.info("Bidder is signing up.");
        try {
            Validation.isNull(login, password, email, age, local);
            Validation.isEmptyString(login, password, email, Integer.toString(age), local);
            Validation.matchCorrectLogin(login);
            Validation.matchSecurePassword(password);
            Validation.matchEmail(email);
            Validation.isAdult(age);

            Bidder bidder = new Bidder(login, password, email, age, local);
            bidder.setAdmin(false);
            bidder.setBlocked(false);
            BidderDAO dao = DAOFactory.getInstance().getBidderDAO();
            dao.insertBidder(bidder);
            logger.info("Bidder is signed up.");
            return bidder;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Bidder signIn(String login, String password) throws ServiceException {
        logger.info("Bidder is signing in.");
        try {
            Validation.isNull(login, password);
            Validation.isEmptyString(login, password);

            BidderDAO dao = DAOFactory.getInstance().getBidderDAO();
            if (dao.checkLogin(login) == 1) {
                Bidder bidder = dao.getBidderByLogin(login);
                if (!bidder.getPassword().equals(password)) {
                    throw new ServiceException("Wrong password!");
                }
                logger.info("Bidder is signed in.");
                return bidder;
            } else {
                throw new ServiceException("Wrong login!");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Bidder getBidderByLogin(String login) throws ServiceException {
        logger.info("getBidderByLogin(String login)");
        try {
            Validation.isNull(login);
            Validation.isEmptyString(login);
            Validation.matchCorrectLogin(login);

            BidderDAO dao = DAOFactory.getInstance().getBidderDAO();
            Bidder bidder = dao.getBidderByLogin(login);
            logger.info("Success.");
            return bidder;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public void updateProfile(Bidder bidder, String newPassword, String repeatedNewPassword) throws ServiceException {
        logger.info("Bidder is updating his profile.");
        try {
            Validation.matchCorrectLogin(bidder.getLogin());
            Validation.isAdult(bidder.getAge());
            Validation.matchEmail(bidder.getEmail());

            BidderDAO dao = DAOFactory.getInstance().getBidderDAO();

            if (!bidder.getPassword().equals("") && !newPassword.equals("") && !repeatedNewPassword.equals("")) {
                Validation.matchSecurePassword(bidder.getPassword(), newPassword, repeatedNewPassword);
                String password = dao.getBidderPasswordById(bidder.getId());
                if (!password.equals(bidder.getPassword())) {
                    throw new ServiceException("Wrong password!");
                }
                if (!newPassword.equals(repeatedNewPassword)) {
                    throw new ServiceException("New passwords do not match each other!");
                } else {
                    bidder.setPassword(newPassword);
                }
            }

            if (bidder.getPassword().equals("")) {
                bidder.setPassword(dao.getBidderPasswordById(bidder.getId()));
            }
            dao.updateBidder(bidder);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void signOut(Cookie[] cookies, HttpServletRequest request, HttpServletResponse response) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ParameterName.LOGIN.getValue())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                request.getSession().invalidate();
            }
        }
        logger.info(request.getSession().getAttribute(ParameterName.LOGIN.getValue()) + " have signed out.");
    }

    @Override
    public void changeLocal(String login, String local) throws ServiceException {
        logger.info("ServiceImpl changing local.");
        try {
            Validation.isNull(login, local);
            Validation.isEmptyString(login, local);
            Validation.matchCorrectLogin(login);

            DAOFactory.getInstance().getBidderDAO().updateLocal(login, local);
            logger.info("ServiceImpl changed local.");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int getNumberOfPages(int biddersQuantity) throws ServiceException {
        logger.info("Getting number of pages for pagination.");
        return (int) Math.ceil(1.0 * biddersQuantity / quantityOnPage);

    }

    @Override
    public int getBiddersQuantity() throws ServiceException {
        logger.info("Getting number of all bidders.");
        int biddersQuantity;
        try {
            biddersQuantity = DAOFactory.getInstance().getBidderDAO().getAllBiddersCount();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        logger.info("Got number of bidders.");
        return biddersQuantity;
    }

    @Override
    public void lockUnlockBidder(int idBidder, boolean isBlocked) throws ServiceException {
        logger.info("Trying to lock/unlock bidder service.");
        try {
            isBlocked = !isBlocked;
            DAOFactory.getInstance().getBidderDAO().updateIsBlocked(idBidder, isBlocked);
            logger.info("Bidder is locked/unlocked.");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setRemoveAdminRights(int idBidder, boolean isAdmin) throws ServiceException {
        logger.info("Trying to set/remove admin rights service.");
        try {
            isAdmin = !isAdmin;
            DAOFactory.getInstance().getBidderDAO().updateIsAdmin(idBidder, isAdmin);
            logger.info("Rights are set/removed successfully.");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Bidder> getAllBiddersPage(int page) throws ServiceException {
        logger.info("Getting List<bidder> of bidders page.");

        List<Bidder> bidders;
        try {
            bidders = DAOFactory.getInstance().getBidderDAO().getAllBiddersPage((page - 1) * quantityOnPage, quantityOnPage);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        logger.info("Got page of all bidders.");
        return bidders;
    }

    @Override
    public Bidder getBidderById(int idBidder) throws ServiceException {
        logger.info("getBidderById(int idBidder)");
        try {
            BidderDAO dao = DAOFactory.getInstance().getBidderDAO();
            Bidder bidder = dao.getBidderById(idBidder);
            logger.info("Success.");
            return bidder;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
