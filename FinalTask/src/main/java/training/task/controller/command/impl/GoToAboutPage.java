package training.task.controller.command.impl;

import training.task.controller.JspPath;
import training.task.controller.command.api.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class {@code GoToAboutPage} redirects to AboutPage.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class GoToAboutPage implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return JspPath.ABOUT_PAGE.getPath();
    }
}
