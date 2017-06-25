package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code GoToPutPage} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}. Redirects to SuggestionPage.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class GoToPutPage implements Command {
    private static Logger logger = Logger.getLogger(GoToPutPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getSession().getAttribute("idBidder").toString());
        Bidder bidder;
        try {
            bidder = ServiceFactory.getInstance().getBidderService().getBidderById(id);
        } catch (ServiceException e) {
            logger.error("Can't go to lots page and get bidder by my id.");
            return JspPath.ERROR_PAGE.getPath();
        }
        request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
        return JspPath.PUT_PAGE.getPath();
    }
}
