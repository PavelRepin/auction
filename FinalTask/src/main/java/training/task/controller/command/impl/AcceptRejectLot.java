package training.task.controller.command.impl;

import org.apache.log4j.Logger;
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
 * Class {@code AcceptRejectLot} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class AcceptRejectLot implements Command {
    private static Logger logger = Logger.getLogger(AcceptRejectLot.class);
    private static final int FIRST_PAGE = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Accepting/rejecting lot controller.");
        try {
            int idLot = Integer.parseInt(request.getParameter(ParameterName.ID_LOT.getValue()));
            int idBidder = Integer.parseInt(request.getParameter(ParameterName.ID_BIDDER.getValue()));
            String acceptReject = request.getParameter(ParameterName.ACCEPT_REJECT.getValue());

            ServiceFactory.getInstance().getLotService().acceptRejectLot(idLot, acceptReject, idBidder);

            LotService lotService = ServiceFactory.getInstance().getLotService();
            List<Lot> suggestions = lotService.getAllSuggestedLotsPage(FIRST_PAGE);
            int suggestionsQuantity = lotService.getAllSuggestedLotsQuantity();
            int pagesOfSuggestions = lotService.getNumberOfPages(suggestionsQuantity);
            request.getSession().setAttribute(ParameterName.SUGGESTIONS.getValue(), suggestions);
            request.getSession().setAttribute(ParameterName.PAGES_FOR_SUGGESTIONS.getValue(), pagesOfSuggestions);
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE_FOR_ADMIN_PAGE_SUGGESTIONS.getValue(), FIRST_PAGE);

        } catch (ServiceException e) {
            logger.info("Can't accept/reject lot and go to admin page getting needed lists.");
            return JspPath.ERROR_PAGE.getPath();
        }
        logger.info("Accepted/rejected lot successfully and got new list of suggestions.");
        return JspPath.ADMIN_PAGE.getPath();
    }
}
