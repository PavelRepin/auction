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
 * Class {@code GoToUserAccountPage} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}. Redirects to UserAccountPage.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class GoToUserAccountPage implements Command {
    private static Logger logger = Logger.getLogger(GoToUserAccountPage.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Getting bidder by login to get into bidder account page.");
        try {
            String login = (String) request.getSession().getAttribute(ParameterName.LOGIN.getValue());
            Bidder bidder = ServiceFactory.getInstance().getBidderService().getBidderByLogin(login);
            request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
        } catch (ServiceException e) {
            logger.error("Can't get bidder by login.");
            return JspPath.USER_ACCOUNT.getPath();
        }
        return JspPath.USER_ACCOUNT.getPath();
    }
}
