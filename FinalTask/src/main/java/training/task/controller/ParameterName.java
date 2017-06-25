package training.task.controller;

/**
 * This enum contains of parameter names values.
 * @author Repin Pavel
 * @version 1.0
 */
public enum ParameterName {
    ID_BIDDER("idBidder"),
    ID_LOT("idLot"),
    LOGIN("login"),
    PASSWORD("password"),
    EMAIL("email"),
    AGE("age"),
    LOCAL("local"),
    SEARCH("search"),
    PAGE("page"),
    NAME("name"),
    LAST_NAME("lastName"),
    NEW_PASSWORD("newPassword"),
    NEW_PASSWORD1("newPassword1"),
    MONEY("money"),
    DOC_NAME("docName"),
    COUNTRY("country"),
    FULL_ADDRESS("fullAddress"),
    CATEGORY("category"),
    AUTHOR("author"),
    YEAR("year"),
    START_PRICE("startPrice"),
    IMAGE("image"),
    DESCRIPTION("description"),
    OWNER_BIDDER("ownerBidder"),
    SELLER_BIDDER("sellerBidder"),
    LOTS("lots"),
    WRONG_PASSWORD("wrongPassword"),
    WRONG_LOGIN("wrongLogin"),
    PAGINATION("pagination"),
    PAGINATION_SUGGESTIONS("paginationSuggestions"),
    PAGINATION_BIDDERS("paginationBidders"),
    PAGES_FOR_LOTS("pages"),
    CURRENT_PAGE_FOR_LOTS_PAGE("currentPage"),
    WELCOME_LOCAL("welcomeLocal"),
    BIDDER("bidder"),
    HOURS("hours"),
    CURRENT_PAGE_FOR_ADMIN_PAGE_BIDDERS("currentPageBidders"),
    CURRENT_PAGE_FOR_ADMIN_PAGE_SUGGESTIONS("currentPageSuggestions"),
    BIDDERS("bidders"),
    SUGGESTIONS("suggestions"),
    PAGES_FOR_SUGGESTIONS("pagesForSuggestions"),
    PAGES_FOR_BIDDERS("pagesForBidders"),
    IS_BLOCKED("isBlocked"),
    IS_ADMIN("isAdmin"),
    ACCEPT_REJECT("acceptReject"),

    ;
    String value;

    ParameterName(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
