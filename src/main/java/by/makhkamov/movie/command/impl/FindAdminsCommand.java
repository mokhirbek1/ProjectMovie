package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.User;
import by.makhkamov.movie.entity.type.UserRole;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindAdminsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String ADMINS_PAGE = "/pages/main/admin/users/admins.jsp";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        int userid =  (int)session.getAttribute("userid");
        logger.log(Level.INFO, "Current admin id: "+userid);
        Router router;
        try {
            List<User> admins = userService.findUsersByRole(UserRole.ADMIN);
            request.setAttribute("admins", admins);
            request.setAttribute("userid", userid);
            router = new Router(ADMINS_PAGE,Router.Type.FORWARD);
            return router;
        }catch (ServiceException serviceException) {
            logger.error("Error in getting all the Admins from Database " + serviceException);
            throw new CommandException(serviceException);
        }


    }
}
