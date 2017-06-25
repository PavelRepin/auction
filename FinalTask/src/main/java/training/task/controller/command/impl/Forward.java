package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.BidderService;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code Forward} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}. Redirects to MainPage.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class Forward implements Command {
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    private static Logger logger = Logger.getLogger(Forward.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        JspPath jspPageName = null;
        Cookie cookies[] = request.getCookies();
        boolean hasLogin = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(ParameterName.LOGIN.getValue())) {
                hasLogin = true;
                try {
                    BidderService bidderService = serviceFactory.getBidderService();
                    Bidder bidder = bidderService.getBidderByLogin(cookie.getValue());
                    request.getSession().setAttribute(ParameterName.ID_BIDDER.getValue(), bidder.getId());
                    request.getSession().setAttribute(ParameterName.LOGIN.getValue(), bidder.getLogin());
                    request.getSession().setAttribute(ParameterName.LOCAL.getValue(), bidder.getLocal());
                    request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
                    jspPageName = JspPath.MAIN_PAGE;
                } catch (ServiceException e) {
                    logger.error("Can't get local", e);
                    return JspPath.ERROR_PAGE.getPath();
                }
                break;
            }
        }
        if (!hasLogin) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(ParameterName.WELCOME_LOCAL.getValue())) {
                    request.getSession().setAttribute(ParameterName.LOCAL.getValue(), "en");
                    break;
                }
            }
            jspPageName = JspPath.MAIN_PAGE;
        }
        return jspPageName.getPath();
    }
}
