package training.task.controller.command.api;

/**
 * This enum contains of command values.
 *
 * @author Repin Pavel
 * @version 1.0
 */
public enum CommandValue {
    SIGN_IN("signIn"), SIGN_UP("signUp"), SEARCH("search"),
    ERROR("error"), GO_TO_PUT_PAGE("goToPutPage"), HELP("help"),
    EDIT_PROFILE("editProfile"), GO_TO_ABOUT_PAGE("about"),
    GO_TO_USER_ACCOUNT_PAGE("goToUserAccountPage"), GO_TO_LOTS_PAGE("goToLotsPage"),
    GO_TO_MAIN_PAGE("goToMainPage"), GO_TO_HELP_PAGE("goToHelpPage"),
    GO_TO_CART_PAGE("goToCartPage"), SUGGEST("suggest"), SIGN_OUT("signOut"),
    CANCEL_SUGGESTION("cancelSuggestion"), BID("bid"), CHANGE_LOCAL("changeLocal"),
    FORWARD("forward"), SUGGEST_FROM_CART("suggestFromCart"), GO_TO_ADMIN_PAGE("goToAdminPage"),
    ACCEPT_REJECT_LOT("acceptRejectLot"), LOCK_UNLOCK_BIDDER("lockUnlockBidder"),
    SET_REMOVE_ADMIN_RIGHTS("setRemoveAdminRights"), BID_FROM_CART_PAGE("bidFromCartPage"),
    BID_FROM_LOTS_PAGE("bidFromLotsPage"),;
    private String value;

    CommandValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
