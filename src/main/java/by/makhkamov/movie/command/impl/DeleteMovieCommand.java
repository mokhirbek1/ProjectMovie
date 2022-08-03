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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

public class DeleteMovieCommand implements Command {
    private static final String MOVIE_ID = "id";
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MovieServiceImpl movieService = MovieServiceImpl.getInstance();
        RatingServiceImpl ratingService = RatingServiceImpl.getInstance();
        CommentServiceImpl commentService = CommentServiceImpl.getInstance();
        String adminPage = request.getParameter("page");
        long id = Long.parseLong(request.getParameter(MOVIE_ID));
        Router router;
        logger.info("Movie id: " + id);

        try {
            Optional<Movie> optionalMovie = movieService.getById(id);
            Optional<Rating> optionalRating = ratingService.getRatingByMovieId(id);
            List<Comment> commentList  = commentService.findAllCommentByMovieId(id);
            if (optionalMovie.isPresent()) {
                logger.info("Movie is exist with id: "+id);
            }else {
                logger.info("Movie don't exist with id: "+id);
            }
            if (optionalRating.isPresent()) {
                logger.info("Rating is exist with movie_id: "+id);
            }else {
                logger.info("Rating don't exist with movie_id: "+id);
            }
            if (!commentList.isEmpty()) {
                logger.info("Comments is exists with movie_id: "+id);
            }else {
                logger.info("Comments don't exists with movie_id: "+id);
            }
            if (movieService.delete(id)) {
                logger.info("Movie successfully deleted with id: " + id);
                if (adminPage.isEmpty()) {
                    router = new AdminPageCommand().execute(request);
                    logger.info("Router in MovieDeleteCommand: " + router);
                }else {
                    router = new FindMoviesCommand().execute(request);
                    logger.info("Router in MovieDeleteCommand: " + router);
                }
            } else {
                router = new FindMoviesCommand().execute(request);
                logger.info("Movie didn't deleted with id: " + id);
                logger.info("Comment row didn't deleted with movie_id: " + id);
                logger.info("Rating row didn't deleted with movie_id: " + id);
            }
        }catch (ServiceException serviceException) {
            logger.error("Failed in deleting movie " + serviceException);
            throw new CommandException(serviceException);
        }
        logger.info("Router in MovieDeleteCommand: " + router);
        return router;
    }
}
