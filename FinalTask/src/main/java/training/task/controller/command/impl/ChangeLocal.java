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
 * Class {@code ChangeLocal} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class ChangeLocal implements Command {
    private static Logger logger = Logger.getLogger(ChangeLocal.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Changing local.");
        try {
            String login = (String) request.getSession().getAttribute(ParameterName.LOGIN.getValue());
            String local = request.getParameter(ParameterName.LOCAL.getValue());
            Cookie cookies[] = request.getCookies();
            if (login != null) {
                ServiceFactory.getInstance().getBidderService().changeLocal(login, local);
                request.getSession().setAttribute(ParameterName.LOCAL.getValue(), local);
            } else {
                boolean hasCookie = false;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(ParameterName.LOGIN.getValue())) {
                        hasCookie = true;
                        ServiceFactory.getInstance().getBidderService().changeLocal(cookie.getValue(), local);
                        request.getSession().setAttribute(ParameterName.LOCAL.getValue(), local);
                        break;
                    }
                }
                if (!hasCookie) {
                    request.getSession().setAttribute(ParameterName.LOCAL.getValue(), local);
                    Cookie cookie = new Cookie(ParameterName.LOCAL.getValue(), local);
                    response.addCookie(cookie);
                }
            }
            logger.info("Success in changing local.");
        } catch (ServiceException e) {
            logger.error("Can't change local.");
            return JspPath.ERROR_PAGE.getPath();
        }

        return JspPath.MAIN_PAGE.getPath();
    }
}
