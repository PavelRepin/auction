package training.task.controller.command.api;

import org.apache.log4j.Logger;
import training.task.controller.ParameterName;
import training.task.controller.command.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * This class produces access to controller execution by command value..
 *
 * @author Repin Pavel
 * @version 1.0
 */
public class CommandProvider {
    private static Logger logger = Logger.getLogger(CommandProvider.class);
    private final static CommandProvider INSTANCE = new CommandProvider();
    private Map<String, Command> commandMatching = new HashMap<>();

    private CommandProvider() {
        commandMatching.put(CommandValue.SIGN_UP.getValue(), new SignUp());
        commandMatching.put(CommandValue.SIGN_IN.getValue(), new SignIn());
        commandMatching.put(CommandValue.ERROR.getValue(), new ErrorInRequest());
        commandMatching.put(CommandValue.SEARCH.getValue(), new Search());
        commandMatching.put(CommandValue.GO_TO_ABOUT_PAGE.getValue(), new GoToAboutPage());
        commandMatching.put(CommandValue.EDIT_PROFILE.getValue(), new EditProfile());
        commandMatching.put(CommandValue.GO_TO_HELP_PAGE.getValue(), new GoToHelpPage());
        commandMatching.put(CommandValue.GO_TO_CART_PAGE.getValue(), new GoToCartPage());
        commandMatching.put(CommandValue.GO_TO_LOTS_PAGE.getValue(), new GoToLotsPage());
        commandMatching.put(CommandValue.GO_TO_PUT_PAGE.getValue(), new GoToPutPage());
        commandMatching.put(CommandValue.SUGGEST.getValue(), new Suggest());
        commandMatching.put(CommandValue.GO_TO_USER_ACCOUNT_PAGE.getValue(), new GoToUserAccountPage());
        commandMatching.put(CommandValue.GO_TO_MAIN_PAGE.getValue(), new GoToMainPage());
        commandMatching.put(CommandValue.CANCEL_SUGGESTION.getValue(), new CancelSuggestion());
        commandMatching.put(CommandValue.SIGN_OUT.getValue(), new SignOut());
        commandMatching.put(CommandValue.BID.getValue(), new BidFromLotsPage());
        commandMatching.put(CommandValue.CHANGE_LOCAL.getValue(), new ChangeLocal());
        commandMatching.put(CommandValue.FORWARD.getValue(), new Forward());
        commandMatching.put(CommandValue.SUGGEST_FROM_CART.getValue(), new SuggestFromCart());
        commandMatching.put(CommandValue.GO_TO_ADMIN_PAGE.getValue(), new GoToAdminPage());
        commandMatching.put(CommandValue.ACCEPT_REJECT_LOT.getValue(), new AcceptRejectLot());
        commandMatching.put(CommandValue.LOCK_UNLOCK_BIDDER.getValue(), new LockUnlockBidder());
        commandMatching.put(CommandValue.SET_REMOVE_ADMIN_RIGHTS.getValue(), new SetRemoveAdminRights());
        commandMatching.put(CommandValue.BID_FROM_CART_PAGE.getValue(), new BidFromCartPage());
        commandMatching.put(CommandValue.BID_FROM_LOTS_PAGE.getValue(), new BidFromLotsPage());
    }

    public static CommandProvider getInstance() {
        return INSTANCE;
    }

    public Command getCommand(HttpServletRequest request) {
        Command command;
        String commandParameter = request.getParameter("command");
        try {
            command = commandMatching.get(commandParameter);
        } catch (IllegalArgumentException e) {
            command = commandMatching.get(CommandValue.ERROR.getValue());
            request.setAttribute("error", e.getMessage());
            logger.error((request.getSession().getAttribute(ParameterName.LOGIN.getValue())), e);
        }
        return command;
    }

}
