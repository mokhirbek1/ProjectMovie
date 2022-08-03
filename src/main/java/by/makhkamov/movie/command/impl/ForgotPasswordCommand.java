package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class ForgotPasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String FORGOT_PASSWORD_PAGE = "/pages/authenticate/test/forgotPassword.jsp";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        UserServiceImpl userService = UserServiceImpl.getInstance();
        String userEmail = request.getParameter("email");
        if (userEmail !=null || !userEmail.equals("")){
            try {
                List<String> userInfo = userService.checkEmail(userEmail);
                if (userInfo.isEmpty()){
                    logger.log(Level.INFO, "User with email: "+userEmail+" not exist in DB");
                    return new Router(FORGOT_PASSWORD_PAGE, Router.Type.FORWARD);
                }else {
                    logger.log(Level.INFO, "User with email: "+userEmail+" "+userInfo+" is exist in DB");
                    request.setAttribute("email", userEmail);
                    request.setAttribute("firstname", userInfo.get(0));
                    request.setAttribute("lastname", userInfo.get(1));
                    return new SendConfirmPasswordCommand().execute(request);
                }
            } catch (ServiceException e) {
                logger.log(Level.ERROR, "Error in checking user email: "+userEmail);
                throw new CommandException(e);
            }
        }else {
            logger.log(Level.DEBUG,"email is Empty");
            return new Router(FORGOT_PASSWORD_PAGE, Router.Type.FORWARD);
        }
    }
}
