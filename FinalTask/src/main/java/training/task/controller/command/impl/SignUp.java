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
 * Class {@code SignUp} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class SignUp implements Command {
    private static Logger logger = Logger.getLogger(SignUp.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Bidder bidder;
        try {
            String login = request.getParameter(ParameterName.LOGIN.getValue());
            String password = request.getParameter(ParameterName.PASSWORD.getValue());
            String email = request.getParameter(ParameterName.EMAIL.getValue());
            int age = Integer.parseInt(request.getParameter(ParameterName.AGE.getValue()));
            String local = request.getSession().getAttribute(ParameterName.LOCAL.getValue()).toString();

            BidderService bidderService = ServiceFactory.getInstance().getBidderService();

            bidder = bidderService.signUp(login, password, email, age, local);

            request.getSession().setAttribute(ParameterName.ID_BIDDER.getValue(), bidder.getId());
            request.getSession().setAttribute(ParameterName.LOGIN.getValue(), bidder.getLogin());
            request.getSession().setAttribute(ParameterName.EMAIL.getValue(), bidder.getEmail());
            request.getSession().setAttribute(ParameterName.AGE.getValue(), bidder.getAge());
            request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
            Cookie cookie = new Cookie(ParameterName.LOGIN.getValue(), bidder.getLogin());
            response.addCookie(cookie);
            request.getSession().setAttribute(ParameterName.LOCAL.getValue(), bidder.getLocal());
        } catch (ServiceException e) {
            logger.error(e);
        }
        return JspPath.MAIN_PAGE.getPath();
    }
}