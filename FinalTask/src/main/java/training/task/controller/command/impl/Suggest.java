package training.task.controller.command.impl;

import org.apache.log4j.Logger;
import training.task.controller.JspPath;
import training.task.controller.ParameterName;
import training.task.controller.command.api.Command;
import training.task.model.api.ServiceFactory;
import training.task.model.api.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Class {@code Suggest} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class Suggest implements Command {
    private static Logger logger = Logger.getLogger(Suggest.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("Controller suggestion.");

        try {
            int year = 0;
            Part image = null;
            String name = request.getParameter(ParameterName.NAME.getValue());
            String category = request.getParameter(ParameterName.CATEGORY.getValue());
            String author = request.getParameter(ParameterName.AUTHOR.getValue());
            if (!request.getParameter(ParameterName.YEAR.getValue()).equals("")) {
                year = Integer.parseInt(request.getParameter(ParameterName.YEAR.getValue()));
            }
            String description = request.getParameter(ParameterName.DESCRIPTION.getValue());
            double startPrice = Double.parseDouble(request.getParameter(ParameterName.START_PRICE.getValue()));
            long hoursBeforeClose = Long.parseLong(request.getParameter(ParameterName.HOURS.getValue()));
            if (request.getPart(ParameterName.IMAGE.getValue()) != null) {
                image = request.getPart(ParameterName.IMAGE.getValue());
            }
            int ownerBidder = Integer.parseInt(request.getParameter(ParameterName.ID_BIDDER.getValue()));

            ServiceFactory.getInstance().getLotService().suggestLot(name, category, author, year, description, startPrice,
                    hoursBeforeClose, image, ownerBidder);


        } catch (ServletException | IOException | ServiceException e) {
            logger.error("Can't suggest the item.");
            return JspPath.ERROR_PAGE.getPath();
        }
        return JspPath.PUT_PAGE.getPath();
    }
}
