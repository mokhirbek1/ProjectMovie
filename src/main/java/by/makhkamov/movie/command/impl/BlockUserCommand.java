package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = null;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String returnPage = request.getParameter("page");
        String user_id = request.getParameter("id");
        logger.info("User id: " + user_id);

        try {
            if (userService.blockUserById(Integer.parseInt(user_id))) {
               if (returnPage.equals("user_page")){
                   router = new FindUsersCommand().execute(request);
                   logger.info("User blocked, with id: "+user_id);
               }else {
                   logger.info("Admin blocked, with id: "+user_id);
                   router = new FindAdminsCommand().execute(request);
               }
            }else {
                logger.info("Didn't blocked user id: "+user_id);
            }
        }catch (ServiceException serviceException) {
            logger.error("Failed blocking user, with id" + user_id + serviceException);
            throw new CommandException(serviceException);
        }
        logger.info("Router: " + router);
        return router;
    }
}
