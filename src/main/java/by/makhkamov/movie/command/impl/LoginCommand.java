package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.User;
import by.makhkamov.movie.entity.type.Status;
import by.makhkamov.movie.entity.type.UserRole;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String USERNAME = "username";
    public static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String CURRENT_PAGE = "current_page";
    private static final String INDEX_PAGE = "/index.jsp";
    private static final String USER_ROLE = "UserRole";
    private static final String USER_STATUS = "UserStatus";
    private static final String LOGIN_MESSAGE = "login_message";
    private static final String LOGIN_PAGE = "/pages/authenticate/login.jsp";
    private static final String ADMIN_PAGE = "/pages/main/admin/admin_page.jsp";
    private static final String HOME_PAGE = "/pages/main/admin/movies/user_home_page.jsp";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        boolean isMatched;
        String userName = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        HttpSession session = request.getSession();
        Router router;
        UserServiceImpl userService = UserServiceImpl.getInstance();
        session.setAttribute(CURRENT_PAGE, INDEX_PAGE);
        try {
                isMatched = userService.loginAuthenticate(userName, password);
                if (isMatched) {
                    UserRole userRole = userService.findUserRole(userName);
                    Status userStatus = userService.findUserStatus(userName);
                    Optional<User> optionalUser = userService.findByLogin(userName);
                    optionalUser.ifPresent(user -> session.setAttribute(USER, user));
                    optionalUser.ifPresent(user -> session.setAttribute("userid", user.getId()));
                    request.setAttribute(USERNAME, userName);
                    session.setAttribute(USERNAME, userName);
                    session.setAttribute(USER_ROLE, userRole);
                    session.setAttribute(USER_STATUS, userStatus);

                    if (session.getAttribute(USER_STATUS) == Status.BLOCKED) {
                        logger.error("User blocked, username: " + userName);
                        request.setAttribute(LOGIN_MESSAGE, "profile_blocked");
                        router = new Router(LOGIN_PAGE, Router.Type.FORWARD);
                    } else if (session.getAttribute(USER_ROLE) == UserRole.ADMIN) {
                        session.setAttribute(CURRENT_PAGE, ADMIN_PAGE);
                        logger.info("User is Admin, username: " + userName);
                        request.setAttribute(LOGIN_MESSAGE, "login_success");
                        router = new GetMoviesByCategoryCommand().execute(request);
                        router.setPage(ADMIN_PAGE);
                        router.setActionType(Router.Type.FORWARD);
                    } else {
                        session.setAttribute(CURRENT_PAGE, HOME_PAGE);
                        request.setAttribute(LOGIN_MESSAGE, "login_success");
                        router = new Router(HOME_PAGE, Router.Type.FORWARD);
                    }
                } else {
                    request.setAttribute(LOGIN_MESSAGE, "incorrect");
                    router = new Router(LOGIN_PAGE, Router.Type.FORWARD);
                }
            return router;
        } catch (ServiceException serviceException) {
            logger.info("Error in Login " + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
