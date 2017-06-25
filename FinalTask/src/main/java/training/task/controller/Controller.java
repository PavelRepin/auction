package training.task.controller;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import training.task.controller.command.api.Command;
import training.task.controller.command.api.CommandProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Class {@code Controller} checks command value and redirects it to its execution.
 * @author Repin Pavel
 * @version 1.0
 */
@WebServlet(name = "Controller",
        urlPatterns = {"/login", ""},
        initParams = {@WebInitParam(name = "init_log4j", value = "/WEB-INF/log4j.properties")})
@MultipartConfig
public class Controller extends HttpServlet {

    private static final String LOG4J_PARAM = "init_log4j";

    public Controller() {
        super();
    }

    @Override
    public void init() throws ServletException {
        String prefix = getServletContext().getRealPath("");
        String filename = getInitParameter(LOG4J_PARAM);

        if (filename == null) {
            BasicConfigurator.configure();
        } else {
            PropertyConfigurator.configure(prefix + File.separator + filename);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandProvider.getInstance().getCommand(request);
        String page = command.execute(request, response);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
