package training.task.controller.command.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command operation.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
