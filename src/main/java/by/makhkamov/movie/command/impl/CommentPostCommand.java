package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.Comment;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.entity.Rating;
import by.makhkamov.movie.entity.User;
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

public class CommentPostCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMENT = "comment";
    private static final String CURRENT_PAGE = "current_page";
    private static final String MOVIE_ID = "id";
    private static final String MOVIE_INFO = "movie_info";
    private static final String RATING_INFO = "rating_info";
    private static final String COMMENT_LIST = "comment_list";
    private static final String MOVIE_INFO_USER_PAGE = "/pages/main/admin/movies/movie_info_user_page.jsp";


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        MovieServiceImpl movieService = MovieServiceImpl.getInstance();
        RatingServiceImpl ratingService = RatingServiceImpl.getInstance();
        CommentServiceImpl commentService = CommentServiceImpl.getInstance();
        String comment_text = request.getParameter(COMMENT);
        Router router = new Router((String) session.getAttribute(CURRENT_PAGE));
        int movie_id = Integer.parseInt(request.getParameter(MOVIE_ID));
        User user = (User) session.getAttribute("user");
        String username = user.getUserName();
        int user_id  = user.getId();
        try {
            Comment comment = new Comment();
            comment.setComment_text(comment_text);
            comment.setMovie_id(movie_id);
            comment.setUser_id(user_id);
            comment.setUsername(username);
            if (commentService.insert(comment)) {
                Optional<Movie> optionalMovie = movieService.getById((long) movie_id);
                Optional<Rating> optionalRating = ratingService.getRatingByMovieId((long) movie_id);
                List<Comment> commentList = commentService.findAllCommentByMovieId(movie_id);
                Movie movie = optionalMovie.get();
                Rating rating = optionalRating.get();
                request.setAttribute(MOVIE_INFO,movie);
                request.setAttribute(RATING_INFO,rating);
                request.setAttribute(COMMENT_LIST,commentList);
                session.setAttribute(CURRENT_PAGE, MOVIE_INFO_USER_PAGE);
                return router;
            }else {
                logger.error("Didn't registered comment to DB");
                throw new CommandException("Didn't registered comment to DB");
            }
        }catch (ServiceException serviceException) {
            logger.error("Failed in CommentPost command" + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
