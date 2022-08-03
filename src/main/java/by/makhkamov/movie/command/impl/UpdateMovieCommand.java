package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.MovieService;
import by.makhkamov.movie.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class UpdateMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String RETURN_PAGE = "return_page";
    private static final String MOVIE_INFO = "movie_info";
    private static final String MOVIE_ID = "id";
    private static final String EDIT_PAGE = "/pages/main/admin/movies/movie_action/edit.jsp";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MovieService movieService = MovieServiceImpl.getInstance();
        String returnPage = request.getParameter("page");
        session.setAttribute(RETURN_PAGE, returnPage);
        Movie forUpdate;
        long id = Long.parseLong(request.getParameter(MOVIE_ID));
        try {
            Optional<Movie> optionalMovie = movieService.getById(id);
            if (optionalMovie.isEmpty()) {
                throw new ServiceException("Didn't find movie, with id: " + id);
            }
            forUpdate = optionalMovie.get();
            session.setAttribute(MOVIE_INFO, forUpdate);
            session.setAttribute(MOVIE_ID, id);
            return new Router(EDIT_PAGE, Router.Type.FORWARD);
        } catch (ServiceException serviceException) {
            logger.error("Failed in searching movie, with id  + " + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
