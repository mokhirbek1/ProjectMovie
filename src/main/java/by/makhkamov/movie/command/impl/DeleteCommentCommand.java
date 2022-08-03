package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.CommentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteCommentCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        CommentServiceImpl commentService = new CommentServiceImpl();
        long comment_id = Long.parseLong(request.getParameter("comment_id"));
        String movie_id = request.getParameter("id");
        String comment_text = request.getParameter("comment_text");
        logger.log(Level.INFO, "Comment id: "+comment_id);
        logger.log(Level.INFO, "Comment text: "+comment_text);
        logger.log(Level.INFO, "Movie id: "+movie_id);
        try {
                if (commentService.delete(comment_id)){
                    logger.log(Level.INFO, "Comment deleted with id: "+comment_id);
                    request.setAttribute("id", movie_id);
                   return new MovieInfoPageCommand().execute(request);
                }else {
                    logger.log(Level.DEBUG, "Comment didn't deleted with id: "+comment_id);
                    request.setAttribute("id", movie_id);
                    return new MovieInfoPageCommand().execute(request);
                }
        } catch (ServiceException serviceException) {
            logger.error("Failed in deleting comment"+serviceException);
            throw new CommandException("Failed in deleting comment"+serviceException);
        }
    }
}
