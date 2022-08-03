package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultCommand implements Command {
    private static final String CURRENT_PAGE = "current_page";
    private static final String INDEX_PAGE = "/index.jsp";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            Router router = new Router(INDEX_PAGE, Router.Type.FORWARD);
            logger.info("Had got router with value: " + router);
            session.setAttribute(CURRENT_PAGE, INDEX_PAGE);
            return router;
        } catch (IllegalArgumentException illegalArgumentException) {
            logger.error("Default exception " + illegalArgumentException);
            throw new CommandException(illegalArgumentException);
        }

    }
}
