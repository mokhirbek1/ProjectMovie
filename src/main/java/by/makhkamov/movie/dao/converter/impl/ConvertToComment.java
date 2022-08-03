package by.makhkamov.movie.dao.converter.impl;

import by.makhkamov.movie.dao.converter.BaseConverter;
import by.makhkamov.movie.entity.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConvertToComment implements BaseConverter<Comment> {
    private static final Logger logger = LogManager.getLogger();
    private static final String COMMENT_ID = "comment_id";
    private static final String USER_ID = "user_id";
    private static final String COMMENT_TEXT = "comment_text";
    private static final String MOVIE_ID = "movie_id";
    private static final String USERNAME = "username";
    @Override
    public Optional<Comment> convert(ResultSet resultSet) {
        Comment comment = new Comment();
        try {
            comment.setId(resultSet.getInt(COMMENT_ID));
            comment.setComment_text(resultSet.getString(COMMENT_TEXT));
            comment.setMovie_id(resultSet.getInt(MOVIE_ID));
            comment.setUsername(resultSet.getString(USERNAME));
            comment.setUser_id(resultSet.getInt(USER_ID));
            return Optional.of(comment);
        } catch (SQLException e) {
            logger.error("Failed in converting comment values to comment object");
            return Optional.empty();
        }
    }
}
