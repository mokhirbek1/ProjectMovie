package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.User;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdatePasswordByEmailCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NEW_PASSWORD_PAGE = "/pages/main/admin/movies/new_password.jsp";
    private static final String LOGIN_PAGE = "/pages/authenticate/login.jsp";
    private static final String EMAIL = "email";
    private static final String MESSAGE = "message";
    private static final String NEW_PASSWORD = "new_password";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String new_password = (String) request.getAttribute(NEW_PASSWORD);
        String email = (String)request.getAttribute(EMAIL);
        try {
            User user = new User();
            user.setEmail(email);
            user.setPassword(new_password);
            if (userService.update(user)){
                request.setAttribute(MESSAGE, "resetSuccess");
                return new Router(LOGIN_PAGE, Router.Type.FORWARD);
            }else{
                request.setAttribute(EMAIL, "resetFailed");
                return new Router(NEW_PASSWORD_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException serviceException) {
            logger.error("Error in updating password "+serviceException);
            throw new CommandException(serviceException);
        }
    }
}
