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
 * Class {@code SuggestFromCart} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class SuggestFromCart implements Command {
    private static Logger logger = Logger.getLogger(SuggestFromCart.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Controller suggestion from cart.");

        try {
            int idLot = Integer.parseInt(request.getParameter(ParameterName.ID_LOT.getValue()));
            String description = request.getParameter(ParameterName.DESCRIPTION.getValue());
            double startPrice = Double.parseDouble(request.getParameter(ParameterName.START_PRICE.getValue()));
            int hours = Integer.parseInt(request.getParameter(ParameterName.HOURS.getValue()));

            ServiceFactory.getInstance().getLotService().suggestLotFromCart(idLot, description, startPrice, hours);

            int idBidder = Integer.parseInt(request.getSession().getAttribute(ParameterName.ID_BIDDER.getValue()).toString());
            List<Lot> lots = ServiceFactory.getInstance().getLotService().getLotsForCartPage(idBidder);
            request.getSession().setAttribute(ParameterName.LOTS.getValue(), lots);
        } catch (ServiceException e) {
            logger.error("Can't suggest the item.");
            return JspPath.ERROR_PAGE.getPath();
        }
        return JspPath.CART_PAGE.getPath();
    }
}
