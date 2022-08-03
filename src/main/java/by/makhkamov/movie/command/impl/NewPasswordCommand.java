package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String NEW_PASSWORD_PAGE = "/pages/main/admin/movies/new_password.jsp";
    private static final String NEW_PASSWORD = "new_password";
    private static final String EMAIL = "email";
    private static final String STATUS = "status";
    private static final String PASSWORD = "password";
    private static final String CONF_PASSWORD = "confPassword";
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute(EMAIL);
        String newPassword = request.getParameter(PASSWORD);
        String confPassword = request.getParameter(CONF_PASSWORD);
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            try {
                request.setAttribute(NEW_PASSWORD, newPassword);
                request.setAttribute(EMAIL, email);
                return new UpdatePasswordByEmailCommand().execute(request);
            } catch (CommandException commandException) {
                logger.error("Error in updating password to new password "+commandException);
                throw new CommandException("Error in updating password to new password "+commandException);
            }
        } else {
            request.setAttribute(STATUS, "notMatchPasswords");
            logger.error("Not matches passwords");
            return  new Router(NEW_PASSWORD_PAGE, Router.Type.FORWARD);
        }
    }
}
