package training.task.controller.command.impl;

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
 * Class {@code GoToLotsPage} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}. Redirects to LotsPage.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class GoToLotsPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String category;
        try {
            int page = Integer.parseInt(request.getParameter(ParameterName.PAGINATION.getValue()));
            category = request.getParameter(ParameterName.CATEGORY.getValue());
            LotService lotService = ServiceFactory.getInstance().getLotService();
            List<Lot> lots = lotService.getLotsPageByCategory(category, page);
            int lotsQuantity = lotService.getLotsQuantity(category);
            int pages = lotService.getNumberOfPages(lotsQuantity);
            request.setAttribute(ParameterName.LOTS.getValue(), lots);
            request.setAttribute(ParameterName.PAGES_FOR_LOTS.getValue(), pages);
            request.setAttribute(ParameterName.CURRENT_PAGE_FOR_LOTS_PAGE.getValue(), page);

            int id = Integer.parseInt(request.getSession().getAttribute("idBidder").toString());
            Bidder bidder = ServiceFactory.getInstance().getBidderService().getBidderById(id);
            request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
            request.getSession().setAttribute(ParameterName.CATEGORY.getValue(), category);
            request.getSession().setAttribute(ParameterName.CURRENT_PAGE_FOR_LOTS_PAGE.getValue(), page);
        } catch (ServiceException e) {
            return JspPath.ERROR_PAGE.getPath();
        }
        return JspPath.LOTS_PAGE.getPath();
    }
}
