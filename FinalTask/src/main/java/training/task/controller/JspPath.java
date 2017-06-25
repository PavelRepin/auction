package training.task.controller;

/**
 * This enum contains of JspPage paths values.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public enum JspPath {
    MAIN_PAGE("/jsp/mainPage.jsp"), ERROR_PAGE("/jsp/error.jsp"),
    USER_ACCOUNT("/jsp/userAccount.jsp"), ABOUT_PAGE("/jsp/about.jsp"),
    HELP_PAGE("/jsp/help.jsp"), LOTS_PAGE("/jsp/lots.jsp"), CART_PAGE("/jsp/cart.jsp"),
    PUT_PAGE("/jsp/suggestion.jsp"), ADMIN_PAGE("/jsp/adminPage.jsp"),;

    private String path;

    JspPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
