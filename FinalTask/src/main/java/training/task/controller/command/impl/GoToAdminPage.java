package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.bean.Lot;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.BidderService;
import training.task.model.api.LotService;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class {@code GoToAdminPage} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}. Redirects to AdminPage.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class GoToAdminPage implements Command {
    private static Logger logger = Logger.getLogger(GoToAdminPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Go to admin page, getting lists of bidders and suggestions.");

        try {
            int pageForBidders = Integer.parseInt(request.getParameter(ParameterName.PAGINATION_BIDDERS.getValue()));
            BidderService bidderService = ServiceFactory.getInstance().getBidderService();
            List<Bidder> bidders = bidderService.getAllBiddersPage(pageForBidders);
            int biddersQuantity = bidderService.getBiddersQuantity();
            int pagesOfBidders = bidderService.getNumberOfPages(biddersQuantity);
            request.getSession().setAttribute(ParameterName.BIDDERS.getValue(), bidders);
            request.getSession().setAttribute(ParameterName.PAGES_FOR_BIDDERS.getValue(), pagesOfBidders);
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE_FOR_ADMIN_PAGE_BIDDERS.getValue(), pageForBidders);

            int pageForSuggestions = Integer.parseInt(request.getParameter(ParameterName.PAGINATION_SUGGESTIONS.getValue()));
            LotService lotService = ServiceFactory.getInstance().getLotService();
            List<Lot> suggestions = lotService.getAllSuggestedLotsPage(pageForSuggestions);
            int suggestionsQuantity = lotService.getAllSuggestedLotsQuantity();
            int pagesOfSuggestions = lotService.getNumberOfPages(suggestionsQuantity);
            request.getSession().setAttribute(ParameterName.SUGGESTIONS.getValue(), suggestions);
            request.getSession().setAttribute(ParameterName.PAGES_FOR_SUGGESTIONS.getValue(), pagesOfSuggestions);
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE_FOR_ADMIN_PAGE_SUGGESTIONS.getValue(), pageForSuggestions);

            int id = Integer.parseInt(request.getSession().getAttribute("idBidder").toString());
            Bidder bidder = ServiceFactory.getInstance().getBidderService().getBidderById(id);
            request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
        } catch (ServiceException e) {
            logger.info("Can't go to admin page and get needed lists.");
            return JspPath.ERROR_PAGE.getPath();
        }
        logger.info("Got needed lists and went to admin page successfully.");
        return JspPath.ADMIN_PAGE.getPath();
    }
}
