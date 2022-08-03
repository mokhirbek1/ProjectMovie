package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidatePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String CONFIRM_PASSWORD_PAGE = "/pages/main/admin/movies/confirm_password_page.jsp";
    private static final String NEW_PASSWORD_PAGE = "/pages/main/admin/movies/new_password.jsp";
    private static final String CURRENT_PAGE = "current_page";
    private static final String EMAIL = "email";
    private static final String STATUS = "email";
    private static final String MESSAGE = "message";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        int confirm_password = Integer.parseInt(request.getParameter("confirm_password"));
        HttpSession session = request.getSession();
        int sent_password = (int) session.getAttribute("sent_password");
        String user_email = (String) session.getAttribute("email");
        if (confirm_password==sent_password) {
            logger.log(Level.INFO, "Confirm password is match");
            request.setAttribute(EMAIL, user_email);
            request.setAttribute(STATUS, "success");
            session.setAttribute(CURRENT_PAGE, NEW_PASSWORD_PAGE);
            logger.log(Level.INFO, "Moved to new_password.jsp");
            return new Router(NEW_PASSWORD_PAGE, Router.Type.FORWARD);
        } else {
            logger.log(Level.INFO, "Confirm password isn't match");
            session.setAttribute(CURRENT_PAGE, CONFIRM_PASSWORD_PAGE);
            logger.log(Level.INFO, "Moved to confirm_password_page.jsp");
            request.setAttribute(MESSAGE, "Wrong confirm password");
            return new Router(CONFIRM_PASSWORD_PAGE, Router.Type.FORWARD);
        }
    }
}
