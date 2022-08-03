package by.makhkamov.movie.command.impl;


import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_PAGE = "/index.jsp";

    @Override
    public Router execute(HttpServletRequest request) {
        logger.log(Level.INFO, "User logged out");
        request.getSession().invalidate();
        return new Router(LOGIN_PAGE, Router.Type.REDIRECT);
    }
}
