package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = null;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String user_id = request.getParameter("id");
        logger.info("User id: " + user_id);

        try {
            if (userService.delete(Long.valueOf(user_id))) {
                router = new FindUsersCommand().execute(request);
                logger.info("User deleted, with id: "+user_id);
                logger.info("Router is " + router);

            }else {
                logger.info("Didn't deleted user id: "+user_id);
            }
        }catch (ServiceException serviceException) {
            logger.error("Failed deleting user with id" + user_id + serviceException);
            throw new CommandException(serviceException);
        }
        logger.info("Router: " + router);
        return router;
    }
}