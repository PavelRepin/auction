package training.task.controller.command.impl;

import training.task.controller.JspPath;
import training.task.controller.command.api.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Help implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        /*Save message in DB and show that it was sent successfully.*/
        return JspPath.HELPPAGE.getPath();
    }
}
