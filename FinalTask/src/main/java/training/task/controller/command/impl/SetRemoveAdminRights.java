package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.bean.Bidder;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.BidderService;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class {@code SetRemoveAdminRights} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class SetRemoveAdminRights implements Command {
    private static Logger logger = Logger.getLogger(LockUnlockBidder.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Setting/removing admin rights.");
        try {
            int idBidder = Integer.parseInt(request.getParameter(ParameterName.ID_BIDDER.getValue()));
            boolean isAdmin = Boolean.parseBoolean(request.getParameter(ParameterName.IS_ADMIN.getValue()));
            BidderService bidderService = ServiceFactory.getInstance().getBidderService();
            bidderService.setRemoveAdminRights(idBidder, isAdmin);
            int pageForBidders = Integer.parseInt(request.getSession().getAttribute(ParameterName.CURRENT_PAGE_FOR_ADMIN_PAGE_BIDDERS.getValue()).toString());
            List<Bidder> bidders = bidderService.getAllBiddersPage(pageForBidders);
            request.getSession().setAttribute(ParameterName.BIDDERS.getValue(), bidders);
        } catch (ServiceException e) {
            logger.info("Can't set/remove rights and get updated list of bidders.");
            return JspPath.ERROR_PAGE.getPath();
        }
        logger.info("Set/removed admin rights successfully and got updated list of bidders.");
        return JspPath.ADMIN_PAGE.getPath();
    }
}
