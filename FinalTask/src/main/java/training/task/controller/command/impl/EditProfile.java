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
 * Class {@code EditProfile} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class EditProfile implements Command {
    private static Logger logger = Logger.getLogger(EditProfile.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Bidder bidder = new Bidder();
        String newPassword;
        String repeatedNewPassword;
        try {
            bidder.setId(Integer.parseInt(request.getParameter(ParameterName.ID_BIDDER.getValue())));
            bidder.setName(request.getParameter(ParameterName.NAME.getValue()));
            bidder.setLastName(request.getParameter(ParameterName.LAST_NAME.getValue()));
            bidder.setLogin(request.getParameter(ParameterName.LOGIN.getValue()));
            bidder.setPassword(request.getParameter(ParameterName.PASSWORD.getValue()));
            newPassword = request.getParameter(ParameterName.NEW_PASSWORD.getValue());
            repeatedNewPassword = request.getParameter(ParameterName.NEW_PASSWORD1.getValue());
            if (!request.getParameter(ParameterName.MONEY.getValue()).equals("")) {
                bidder.setMoney(Double.parseDouble(request.getParameter(ParameterName.MONEY.getValue())));
            }
            if (!request.getParameter(ParameterName.AGE.getValue()).equals("")) {
                bidder.setAge(Integer.parseInt(request.getParameter(ParameterName.AGE.getValue())));
            }
            bidder.setDocumentNumber(request.getParameter(ParameterName.DOC_NAME.getValue()));
            bidder.setCountry(request.getParameter(ParameterName.COUNTRY.getValue()));
            bidder.setFullAddress(request.getParameter(ParameterName.FULL_ADDRESS.getValue()));
            bidder.setEmail(request.getParameter(ParameterName.EMAIL.getValue()));
            bidder.setLocal(request.getLocale().toString());
            BidderService bidderService = ServiceFactory.getInstance().getBidderService();

            bidderService.updateProfile(bidder, newPassword, repeatedNewPassword);

            Cookie cookie = new Cookie(ParameterName.LOGIN.getValue(), bidder.getLogin());
            response.addCookie(cookie);
            request.getSession().setAttribute(ParameterName.BIDDER.getValue(), bidder);
            request.getSession().setAttribute(ParameterName.WRONG_PASSWORD.getValue(), 0);

        } catch (ServiceException e) {
            request.getSession().setAttribute(ParameterName.WRONG_PASSWORD.getValue(), 1);
            return JspPath.USER_ACCOUNT.getPath();
        }
        return JspPath.USER_ACCOUNT.getPath();
    }
}
