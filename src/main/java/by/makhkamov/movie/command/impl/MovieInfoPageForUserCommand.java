package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.Comment;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.entity.Rating;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.MovieServiceImpl;
import by.makhkamov.movie.service.impl.CommentServiceImpl;
import by.makhkamov.movie.service.impl.RatingServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MovieInfoPageForUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String RATING_INFO = "rating_info";
    private static final String MOVIE_INFO = "movie_info";
    private static final String COMMENT_LIST = "comment_list";
    private static final String CURRENT_PAGE = "current_page";
    private static final String MOVIE_INFO_USER_PAGE = "/pages/main/admin/movies/movie_info_user_page.jsp";
    public static final String MOVIE_ID = "id";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MovieServiceImpl movieService = MovieServiceImpl.getInstance();
        RatingServiceImpl ratingService = RatingServiceImpl.getInstance();
        CommentServiceImpl commentService = CommentServiceImpl.getInstance();
        request.setAttribute("user_id", session.getAttribute("user_id"));
        Router router;
        long id = Long.parseLong(request.getParameter(MOVIE_ID));
        try {
            Optional<Movie> optionalMovie = movieService.getById(id);
            Optional<Rating> optionalRating = ratingService.getRatingByMovieId(id);
            List<Comment> commentList = commentService.findAllCommentByMovieId(id);
            if (optionalMovie.isEmpty()) {
                throw new CommandException("Not found movie with id: " + id);
            }
            if (commentList.isEmpty()) {
                logger.info("No comment added yet");
            }
            if (optionalRating.isEmpty()) {
                logger.info("Rating not yet added");
                Rating rating = new Rating(0,0,0);
                request.setAttribute(RATING_INFO, rating);
            }else {
                Rating rating = optionalRating.get();
                request.setAttribute(RATING_INFO, rating);
            }
            Movie movie = optionalMovie.get();
            request.setAttribute(MOVIE_INFO, movie);
            request.setAttribute(COMMENT_LIST, commentList);
            session.setAttribute(CURRENT_PAGE, MOVIE_INFO_USER_PAGE);
            router = new Router(MOVIE_INFO_USER_PAGE, Router.Type.FORWARD);
            return router;
        }catch (ServiceException serviceException) {
            logger.error("Failed in finding the movie, by id: "+id + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
