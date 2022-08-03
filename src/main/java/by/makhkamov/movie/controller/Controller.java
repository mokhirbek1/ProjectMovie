package by.makhkamov.movie.controller;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.CommandType;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.pool.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
@WebServlet(name = "Controller",urlPatterns = {"/controller", "*.do"})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void init() {
        ConnectionPool.getInstance();
        logger.log(Level.INFO,"++++++++++ Servlet - "+this.getServletName()+" Init: "+this.getServletInfo());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        realizeRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        realizeRequest(request,response);
    }
    private void realizeRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String commandStr = request.getParameter("command");
        logger.info("Command is: "+commandStr);
        Command command = CommandType.define(commandStr);
        try {
            Router router = command.execute(request);
            logger.info("Current page is: "+router.getPage());
//            session.setAttribute("current_page", router.getPage());
            if(router.getActionType().equals(Router.Type.FORWARD)) {
                request.getRequestDispatcher(router.getPage()).forward(request,response);
            }else {
                response.sendRedirect(request.getContextPath() + router.getPage());
            }
        } catch (CommandException e) {
            request.setAttribute("error_msg", e.getCause());
            request.getRequestDispatcher("pages/error/error_500.jsp").forward(request, response);
        }
    }
    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
        logger.log(Level.INFO,"---------- Servlet Destroyed: "+this.getServletName());
    }
}
