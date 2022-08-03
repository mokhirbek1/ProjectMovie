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

public class FindMoviesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String CURRENT_PAGE = "current_page";
    private static final String MOVIE_LIST = "movie_list";
    private static final String MOVIES_PAGE = "/pages/main/admin/movies/movies.jsp";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MovieServiceImpl movieService = MovieServiceImpl.getInstance();
        HttpSession session = request.getSession();
        Router router;
        try {
            List<Movie> movieList = movieService.findAll();
            session.setAttribute(CURRENT_PAGE, MOVIES_PAGE);
            request.setAttribute(MOVIE_LIST,movieList);
            router = new Router(MOVIES_PAGE,Router.Type.FORWARD);
            logger.info("Successfully got movies list from DB: "+movieList);
            return router;
        }catch (ServiceException serviceException) {
            logger.error("Failed in getting movies from DB" + serviceException);
            throw new CommandException("Failed in getting movies from DB"+serviceException);
        }
    }
}
