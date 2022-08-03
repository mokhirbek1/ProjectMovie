package by.makhkamov.movie.controller.listener;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class SessionAttributeListenerImpl implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {
    static Logger logger = LogManager.getLogger();

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        logger.log(Level.INFO, "+++<<<<<----------> attributeAdded: "+sbe.getSession().getAttribute("username"));
        logger.log(Level.INFO, "+++<<<<<----------> attributeAdded: "+sbe.getSession().getAttribute("current_page"));
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        logger.log(Level.INFO, "###<<<<<-------------> attributeReplaced: "+sbe.getSession().getAttribute("username"));
        logger.log(Level.INFO, "###<<<<<-------------> attributeReplaced: "+sbe.getSession().getAttribute("user_name"));
    }
}
