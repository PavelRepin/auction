package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.bean.Lot;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class {@code GoToCartPage} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}. Redirects to CartPage.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class GoToCartPage implements Command {
    private static Logger logger = Logger.getLogger(GoToCartPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Getting list of lots for cart page. Controller.");
        try {
            int id = Integer.parseInt(request.getSession().getAttribute(ParameterName.ID_BIDDER.getValue()).toString());
            List<Lot> lots = ServiceFactory.getInstance().getLotService().getLotsForCartPage(id);
            request.getSession().setAttribute(ParameterName.LOTS.getValue(), lots);
            Bidder bidder = ServiceFactory.getInstance().getBidderService().getBidderById(id);
            request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);

        } catch (ServiceException e) {
            logger.info("Can't get list for cart page and go to cart page.");
            return JspPath.ERROR_PAGE.getPath();
        }

        return JspPath.CART_PAGE.getPath();
    }
}
