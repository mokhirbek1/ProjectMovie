package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.type.Status;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.entity.User;
import by.makhkamov.movie.entity.type.UserRole;
import by.makhkamov.movie.service.UserService;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String EMAIL = "email";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String RE_PASSWORD = "re_password";
    private static final String USER = "user";
    private static final String REG_MESSAGE = "reg_message";
    private static final String REGISTER_PAGE = "/pages/authenticate/registration.jsp";
    private static final String LOGIN_PAGE = "/pages/authenticate/login.jsp";
    private final String ADMIN_USERNAME = "AdminGeneral";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserService userService = UserServiceImpl.getInstance();
        String email = request.getParameter(EMAIL);
        String firstname = request.getParameter(FIRSTNAME);
        String lastname = request.getParameter(LASTNAME);
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String repeated_password = request.getParameter(RE_PASSWORD);

        logger.log(Level.INFO, "Email: " + email);
        logger.log(Level.INFO, "Firstname: " + firstname);
        logger.log(Level.INFO, "Lastname: " + lastname);
        logger.log(Level.INFO, "Username: " + username);
        logger.log(Level.INFO, "Password: " + password);
        logger.log(Level.INFO, "Repeated password: " + password);

        if (!password.equals(repeated_password)) {
            logger.info("Don't matches passwords");
            request.setAttribute(REG_MESSAGE, "not_matches_passwords");
            return new Router(REGISTER_PAGE, Router.Type.FORWARD);
        }

            User user = new User();
            try {
                if (userService.loginSuitable(username)) {
                    user.setUserName(username);
                } else {
                    request.setAttribute(REG_MESSAGE, "exist_login");
                    logger.info("Wrote exist login");
                    return new Router(REGISTER_PAGE, Router.Type.FORWARD);
                }
                if (userService.emailSuitable(email)) {
                    user.setEmail(email);
                } else {
                    request.setAttribute(REG_MESSAGE, email + "exist_email");
                    logger.info("Wrote exist email");
                    return new Router(REGISTER_PAGE, Router.Type.FORWARD);
                }
                if (username.equals(ADMIN_USERNAME)) {
                    user.setRole(UserRole.ADMIN);
                } else {
                    user.setRole(UserRole.USER);
                }
                user.setFirstname(firstname);
                user.setLastname(lastname);
                user.setPassword(password);
                user.setStatus(Status.ACTIVE);
                request.setAttribute(USER, user);
                if (userService.insert(user)) {
                    if (username.equals(ADMIN_USERNAME)) {
                        request.setAttribute(REG_MESSAGE, "success");
                        return new Router(LOGIN_PAGE, Router.Type.FORWARD);
                    }
                    request.setAttribute(REG_MESSAGE, "success");
                    return new Router(LOGIN_PAGE, Router.Type.FORWARD);
                } else {
                    request.setAttribute(REG_MESSAGE, "error");
                    return new Router(REGISTER_PAGE, Router.Type.FORWARD);
                }

            } catch (ServiceException e) {
                logger.error("error in registering a new user", e);
                throw new CommandException(e);
            }
    }
}
