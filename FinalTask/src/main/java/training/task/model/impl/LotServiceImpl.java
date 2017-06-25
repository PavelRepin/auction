package training.task.model.impl;

import org.apache.log4j.Logger;
import training.task.bean.Lot;
import training.task.dao.api.DAOFactory;
import training.task.dao.api.LotDAO;
import training.task.dao.api.exception.DAOException;
import training.task.model.api.LotService;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LotServiceImpl implements LotService {
    private static Logger logger = Logger.getLogger(LotServiceImpl.class);
    private static final int quantityOnPage = 8;
    private static final int OWN_SELL_SUGGEST_LOGICAL_ZERO = 0;
    private static final String PATH_FOR_IMAGES = "C:/images";
    private static final int MILLISECONDS_IN_HOUR = 3600000;


    @Override
    public List<Lot> getLotsPageByCategory(String category, int page) throws ServiceException {
        logger.info("Getting List<lot> page by category.");

        List<Lot> lots;
        try {
            Validation.isNull(category);
            Validation.isEmptyString(category);
            LotDAO lotDAO = DAOFactory.getInstance().getLotDAO();
            if (category.equals("All lots")) {
                lots = lotDAO.getAllLotsPage((page - 1) * quantityOnPage, quantityOnPage);
            } else {
                lots = lotDAO.getLotsPageByCategory(category, (page - 1) * quantityOnPage, quantityOnPage);
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        logger.info("Got page of lots by category.");
        return lots;
    }

    @Override
    public int getLotsQuantity(String category) throws ServiceException {
        logger.info("Getting number of lots by category.");
        int lotsQuantity;
        try {
            Validation.isNull(category);
            Validation.isEmptyString(category);
            if (category.equals("All lots")) {
                lotsQuantity = DAOFactory.getInstance().getLotDAO().getAllLotsCount();
            } else {
                lotsQuantity = DAOFactory.getInstance().getLotDAO().getLotsCountByCategory(category);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        logger.info("Got number of lots.");
        return lotsQuantity;
    }

    @Override
    public int getNumberOfPages(int lotsQuantity) throws ServiceException {
        logger.info("Getting number of pages for pagination.");
        return (int) Math.ceil(1.0 * lotsQuantity / quantityOnPage);

    }

    @Override
    public void suggestLot(String name, String category, String author, int year, String description, double startPrice,
                           long hoursBeforeClose, Part image, int ownerBidder) throws ServiceException {
        logger.info("Suggesting lot in ServiceImpl.");
        try {
            Validation.isNull(name, category, author, description);
            Validation.isEmptyString(name, category, author, description);

            Lot lot = new Lot();
            lot.setName(name);
            lot.setCategory(category);
            lot.setAuthor(author);
            lot.setYear(year);
            lot.setDescription(description);
            lot.setStartPrice(startPrice);
            lot.setCloseAt(MILLISECONDS_IN_HOUR * hoursBeforeClose);
            if (image != null) {
                String fileName = new File(image.getSubmittedFileName()).getName();
                if (!fileName.isEmpty()) {
                    String prefix = fileName;
                    String suffix = null;

                    int index = fileName.indexOf('.');
                    if (index != -1) {
                        prefix = fileName.substring(0, index);
                        suffix = fileName.substring(index);
                    }

                    File imageDirectory = new File(PATH_FOR_IMAGES);
                    File imageFile = File.createTempFile(prefix, suffix, imageDirectory);

                    lot.setImage(imageFile.getName());
                    uploadImage(image, imageFile);
                }
            }
            lot.setOwnerBidder(ownerBidder);
            lot.setSellerBidder(OWN_SELL_SUGGEST_LOGICAL_ZERO);

            DAOFactory.getInstance().getLotDAO().insertLot(lot);
        } catch (IOException e) {
            logger.error("Can't create image file.");
            throw new ServiceException(e);
        } catch (DAOException e) {
            logger.error("Can't insert lot.");
            throw new ServiceException(e);
        }
    }

    @Override
    public void suggestLotFromCart(int id, String description, double startPrice, int hours) throws ServiceException {
        logger.info("Trying to suggest lot from cart.");
        try {
            Validation.isNull(description);
            Validation.isEmptyString(description);
            DAOFactory daoFactory = DAOFactory.getInstance();

            Lot lot = daoFactory.getLotDAO().getLotById(id);
            lot.setDescription(description);
            lot.setStartPrice(startPrice);
            lot.setCloseAt(hours);
            lot.setSellerBidder(OWN_SELL_SUGGEST_LOGICAL_ZERO);
            daoFactory.getLotDAO().updateLotOwnerSeller(lot.getId(), OWN_SELL_SUGGEST_LOGICAL_ZERO, OWN_SELL_SUGGEST_LOGICAL_ZERO);
            daoFactory.getLotDAO().insertLot(lot);
        } catch (DAOException e) {
            logger.error("Can't insert lot from cart.");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Lot> getLotsForCartPage(int idBidder) throws ServiceException {
        logger.info("Getting List<lot> for cart page. Service.");

        List<Lot> lots;
        try {
            Validation.isNull(idBidder);
            lots = DAOFactory.getInstance().getLotDAO().getLotsBidderSuggestedSellingOwnBargaining(idBidder);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return lots;
    }

    private static void uploadImage(Part inputFile, File outputFile) throws ServiceException {
        try {
            logger.debug("Trying to upload image.");
            File directory = outputFile.getParentFile();
            if (!directory.exists() && !directory.mkdirs()) {
                throw new IOException("Can't create directory: " + directory);
            }

            inputFile.write(outputFile.getAbsolutePath());
        } catch (IOException e) {
            throw new ServiceException("Can't upload image.", e);
        }
        logger.info("Successfully uploaded.");
    }

    @Override
    public void acceptRejectLot(int idLot, String acceptReject, int idBidder) throws ServiceException {
        logger.info("Accept/reject lot service.");
        try {
            if (acceptReject.equals("accept")) {
                ServiceFactory.getInstance().getAuctionService().placeLot(idBidder, idLot, OWN_SELL_SUGGEST_LOGICAL_ZERO);
            } else {
                DAOFactory.getInstance().getLotDAO().updateLotOwnerSeller(idLot, idBidder, idBidder);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        logger.info("Accepted/rejected lot successfully. Now it's in selling state.");
    }

    @Override
    public List<Lot> getAllLots() throws ServiceException {
        return null;
    }

    @Override
    public List<Lot> getAllSuggestedLotsPage(int page) throws ServiceException {
        logger.info("Getting List<lot> of suggestions page.");

        List<Lot> lots;
        try {
            lots = DAOFactory.getInstance().getLotDAO().getSuggestedLotsPage((page - 1) * quantityOnPage, quantityOnPage);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        logger.info("Got page of lots by category.");
        return lots;
    }

    @Override
    public int getAllSuggestedLotsQuantity() throws ServiceException {
        logger.info("Getting number of all suggestions.");
        int suggestionsQuantity;
        try {
            suggestionsQuantity = DAOFactory.getInstance().getLotDAO().getAllSuggestionsCount();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        logger.info("Got number of suggestions.");
        return suggestionsQuantity;
    }

    @Override
    public void cancelLot(int idLot, int idBidder) throws ServiceException {
        logger.info("Canceling lot from suggestion state.");
        try {
            DAOFactory.getInstance().getLotDAO().updateLotOwnerSeller(idLot, idBidder, idBidder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        logger.info("Lot is canceled.");
    }
}
