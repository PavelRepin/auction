package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code SignIn} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class SignIn implements Command {
    private static Logger logger = Logger.getLogger(SignIn.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Signing in command impl.");
        try {
            request.getSession().setAttribute(ParameterName.WRONG_PASSWORD.getValue(), 0);
            request.getSession().setAttribute(ParameterName.WRONG_LOGIN.getValue(), 0);
            String login = request.getParameter(ParameterName.LOGIN.getValue());
            String password = request.getParameter(ParameterName.PASSWORD.getValue());
            Bidder bidder = ServiceFactory.getInstance().getBidderService().signIn(login, password);
            request.getSession().setAttribute(ParameterName.ID_BIDDER.getValue(), bidder.getId());
            request.getSession().setAttribute(ParameterName.LOGIN.getValue(), bidder.getLogin());
            request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
            request.getSession().setAttribute(ParameterName.LOCAL.getValue(), bidder.getLocal());
            Cookie cookie = new Cookie(ParameterName.LOGIN.getValue(), bidder.getLogin());
            response.addCookie(cookie);
        } catch (ServiceException e) {
            if (e.getMessage().equals("Wrong password!")) {
                request.getSession().setAttribute(ParameterName.WRONG_PASSWORD.getValue(), 2);
            }
            if (e.getMessage().equals("Wrong login!")) {
                request.getSession().setAttribute(ParameterName.WRONG_LOGIN.getValue(), 1);
            }
            return JspPath.MAIN_PAGE.getPath();
        }
        return JspPath.MAIN_PAGE.getPath();
    }
}
