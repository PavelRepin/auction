package training.task.controller.command.impl;

import org.apache.log4j.Logger;
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
 * Class {@code CancelSuggestion} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class CancelSuggestion implements Command {
    private static Logger logger = Logger.getLogger(CancelSuggestion.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Controller cancel suggestion.");

        try {
            int idLot = Integer.parseInt(request.getParameter(ParameterName.ID_LOT.getValue()));
            int idBidder = Integer.parseInt(request.getParameter(ParameterName.ID_BIDDER.getValue()));
            ServiceFactory.getInstance().getLotService().cancelLot(idLot, idBidder);
            List<Lot> lots = ServiceFactory.getInstance().getLotService().getLotsForCartPage(idBidder);
            request.getSession().setAttribute(ParameterName.LOTS.getValue(), lots);
        } catch (ServiceException e) {
            logger.info("Can't get updated list for cart page and go to cart page.");
            return JspPath.ERROR_PAGE.getPath();
        }
        return JspPath.CART_PAGE.getPath();
    }
}
