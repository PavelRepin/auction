package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.bean.Lot;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.LotService;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class {@code BidFromLotsPage} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class BidFromLotsPage implements Command {
    private static Logger logger = Logger.getLogger(BidFromLotsPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Bid controller.");
        try {
            int idLot = Integer.parseInt(request.getParameter(ParameterName.ID_LOT.getValue()));
            int idBidder = Integer.parseInt(request.getParameter(ParameterName.ID_BIDDER.getValue()));
            ServiceFactory.getInstance().getAuctionService().bidLot(idBidder, idLot);

            String category = request.getSession().getAttribute(ParameterName.CATEGORY.getValue()).toString();
            int page = Integer.parseInt(request.getSession().getAttribute(ParameterName.CURRENT_PAGE_FOR_LOTS_PAGE.getValue()).toString());
            LotService lotService = ServiceFactory.getInstance().getLotService();
            List<Lot> lots = lotService.getLotsPageByCategory(category, page);
            int lotsQuantity = lotService.getLotsQuantity(category);
            int pages = lotService.getNumberOfPages(lotsQuantity);
            request.setAttribute(ParameterName.LOTS.getValue(), lots);
            request.setAttribute(ParameterName.PAGES_FOR_LOTS.getValue(), pages);
            request.setAttribute(ParameterName.CURRENT_PAGE_FOR_LOTS_PAGE.getValue(), page);

            Bidder bidder = ServiceFactory.getInstance().getBidderService().getBidderById(idBidder);
            request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
            request.getSession().setAttribute(ParameterName.CATEGORY.getValue(), category);
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE_FOR_LOTS_PAGE.getValue(), page);
        } catch (ServiceException e) {
            logger.info("Can't bid and get updated list for lots page and go to lots page.");
            return JspPath.ERROR_PAGE.getPath();
        }
        return JspPath.LOTS_PAGE.getPath();
    }
}
