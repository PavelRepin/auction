package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code SignOut} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class SignOut implements Command {
    private static Logger logger = Logger.getLogger(SignOut.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        try {
            String local = request.getParameter(ParameterName.LOCAL.getValue());
            ServiceFactory.getInstance().getBidderService().signOut(cookies, request, response);
            request.getSession().setAttribute(ParameterName.LOCAL.getValue(), local);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return JspPath.MAIN_PAGE.getPath();
    }
}
