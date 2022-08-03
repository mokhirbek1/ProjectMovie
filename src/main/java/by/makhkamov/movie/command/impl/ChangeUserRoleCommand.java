package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.type.UserRole;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeUserRoleCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String returnPage = request.getParameter("page");
        String user_id = request.getParameter("user_id");
        UserRole role = UserRole.valueOf(String.valueOf(request.getParameter("role")).toUpperCase());
        String message = "";
        Router router;
        try {
            if (userService.changeRole(Integer.parseInt(user_id), role)) {
                if (returnPage.equals("user_page")) {
                    logger.log(Level.INFO, "User with id: " + user_id + "added to admins list");
                    message = "User with id: " + user_id + " added to admins list";
                    request.setAttribute("message", message);
                    router = new FindUsersCommand().execute(request);
                } else {
                    logger.log(Level.INFO, "Admin with id: " + user_id + " changed role to USER from admins list");
                    message = "Admin with id: " + user_id + " changed role to USER from admins list";
                    request.setAttribute("message", message);
                    router = new FindAdminsCommand().execute(request);
                }
            } else {
                if (returnPage.equals("user_page")) {
                    logger.log(Level.DEBUG, "Didn't added user with id: " + user_id + " to admins list");
                    message = "User with id: " + user_id + " didn't added to admins list";
                    request.setAttribute("message", message);
                    router = new FindUsersCommand().execute(request);
                } else {
                    logger.log(Level.DEBUG, "Didn't changed admin role to USER with id: " + user_id + " from admins list");
                    message = "Admin with id: " + user_id + " didn't changed role to User from admins list";
                    request.setAttribute("message", message);
                    router = new FindAdminsCommand().execute(request);
                }
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed in adding admin " + e);
            throw new CommandException(e);
        }
        return router;
    }
}
