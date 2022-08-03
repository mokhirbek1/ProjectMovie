package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActivateUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = null;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String adminPage = request.getParameter("page");
        String user_id = request.getParameter("id");
        logger.info("User id: " + user_id);
        try {
            if (userService.activateUserById(Integer.parseInt(user_id))) {
                if (adminPage.equals("user_page")){
                    router = new FindUsersCommand().execute(request);
                    logger.info("Activated user, with id: "+user_id);
                }else {
                    router = new FindAdminsCommand().execute(request);
                    logger.info("Admin activated, with id: "+user_id);
                }
            }else {
                logger.info("Didn't blocked user id: "+user_id);
            }
        }catch (ServiceException serviceException) {
            logger.error("Failed activating user with id" + user_id + serviceException);
            throw new CommandException(serviceException);
        }
        logger.info("Router: " + router);
        return router;
    }
}
