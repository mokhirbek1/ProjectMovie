package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.entity.Rating;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.MovieServiceImpl;
import by.makhkamov.movie.service.impl.RatingServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class    MovieRatingCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String MOVIE_INFO = "movie_info";
    private static final String RATING_INFO = "rating_info";
    private static final String CURRENT_PAGE = "current_page";
    private static final String MOVIE_INFO_PAGE = "/pages/main/admin/movies/movie_info_page.jsp";
    private static final String MOVIE_ID = "id";
    private static final String RATE = "rate";

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MovieServiceImpl movieService = MovieServiceImpl.getInstance();
        RatingServiceImpl ratingService = RatingServiceImpl.getInstance();
        Router router = new Router((String) session.getAttribute(CURRENT_PAGE),Router.Type.FORWARD);
        long id = Long.parseLong(request.getParameter(MOVIE_ID));
        double value = Double.parseDouble(request.getParameter(RATE));


        try {
            Optional<Movie> optionalMovie = movieService.getById(id);
            Rating rating = new Rating();
            rating.setValue((int) value);
            rating.setMovie_id((int) id);
            if (ratingService.update(rating)) {
                Movie movie = optionalMovie.get();
                request.setAttribute(MOVIE_INFO,movie);
                request.setAttribute(RATING_INFO,rating);
                session.setAttribute(CURRENT_PAGE, MOVIE_INFO_PAGE);
                return router;
            }else {
                logger.error("Didn't updated value of movie rating");
                throw new CommandException("Didn't updated value of movie rating");
            }
        }catch (ServiceException serviceException) {
            logger.error("Failed in Rating command " + serviceException);
            throw new CommandException(serviceException);
        }

    }
}
