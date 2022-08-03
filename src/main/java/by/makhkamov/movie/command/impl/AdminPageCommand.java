package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ADMIN_PAGE = "/pages/main/admin/admin_page.jsp";
    private static final String CURRENT_PAGE = "current_page";




    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
                Router router = new GetMoviesByCategoryCommand().execute(request);
                session.setAttribute(CURRENT_PAGE, ADMIN_PAGE);
                router.setPage(ADMIN_PAGE);
                router.setActionType(Router.Type.FORWARD);
                logger.info("Router: "+router);
            return router;
        } catch (CommandException commandException) {
            logger.error("Failed in getting movies from Database " + commandException);
            throw new CommandException(commandException);
        }
    }
}
