package by.makhkamov.movie.command;

public class Router {
    private static final String INDEX_PAGE = "/index.jsp";

    private String page;
    private Type actionType;
    public enum Type {
        FORWARD,
        REDIRECT;
    }

    public Router() {
        this.actionType = Type.FORWARD;
        this.page = INDEX_PAGE;
    }

    public Router(String page, Type actionType) {
        this.page = page;
        this.actionType = actionType;
    }

    public Router(String page) {
        this.page = page;
        this.actionType = Type.FORWARD;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Type getActionType() {
        return actionType;
    }

    public void setActionType(Type actionType) {
        this.actionType = actionType;
    }
}
