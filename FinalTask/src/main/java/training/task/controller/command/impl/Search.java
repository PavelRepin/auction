package training.task.controller.command.impl;

/*TODO: Переход на лоты страницу, но поиск происходит в модели когда подрублю дом*/

import training.task.controller.JspPath;
import training.task.controller.command.api.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code Search} parses parameters and attributes from {@link HttpServletRequest} and generates
 * or updates attributes in HttpSession or {@link HttpServletRequest}.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class Search implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        /*parse value of @search@ param and search in the name field in db lots table*/
        return JspPath.LOTS_PAGE.getPath();
    }
}
