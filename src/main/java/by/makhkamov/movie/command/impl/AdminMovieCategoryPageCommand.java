package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminMovieCategoryPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String CURRENT_PAGE = "current_page";
    private static final String ADMIN_PAGE_BY_CATEGORY_MOVIE = "/pages/main/admin/movies/admin_page_by_category_movie.jsp";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MovieServiceImpl movieService = MovieServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String movieCategory = request.getParameter("movie_category");
        try {
            List<Movie> movieList = movieService.findByCategory(movieCategory);
            request.setAttribute("movie_category_list", movieList);
            request.setAttribute("movie_category", movieCategory);
            session.setAttribute(CURRENT_PAGE, ADMIN_PAGE_BY_CATEGORY_MOVIE);
            return new Router(ADMIN_PAGE_BY_CATEGORY_MOVIE, Router.Type.FORWARD);
        } catch (ServiceException serviceException) {
            logger.error("Error in getting all the Movie from Database " + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
