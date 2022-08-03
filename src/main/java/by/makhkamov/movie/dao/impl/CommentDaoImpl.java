package by.makhkamov.movie.dao.impl;

import by.makhkamov.movie.dao.CommentDao;
import by.makhkamov.movie.dao.converter.impl.ConvertToComment;
import by.makhkamov.movie.entity.Comment;
import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentDaoImpl implements CommentDao {
    private static final CommentDaoImpl instance = new CommentDaoImpl();
    private final ConvertToComment convertToComment = new ConvertToComment();
    private static final Logger logger = LogManager.getLogger(CommentDaoImpl.class);
    private static final String SELECT_ALL_COMMENT = "SELECT comment_id,comment_text,user_id,movie_id, user_name FROM comments";
    private static final String INSERT_COMMENT = "INSERT INTO comments (comment_text, movie_id, user_id, username) VALUES(?,?,?,?)";
    private static final String DELETE_COMMENT_BY_ID = "DELETE FROM comments WHERE comment_id=?";
    private static final String FIND_COMMENT_BY_MOVIE_ID = "SELECT comment_id, comment_text, movie_id, user_id, username FROM comments WHERE movie_id = ?";
    public static final String UPDATE_COMMENT = "UPDATE comments SET comment_text=? WHERE comment_id=?";

    private CommentDaoImpl() {

    }

    public static CommentDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COMMENT_BY_ID)) {
            preparedStatement.setLong(1, id);
            int result = preparedStatement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed deleting comment: " + e);
            throw new DaoException("Failed deleting comment: " + e);
        }
    }

    @Override
    public boolean insert(Comment comment) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT)) {
            preparedStatement.setString(1, comment.getComment_text());
            preparedStatement.setInt(2, comment.getMovie_id());
            preparedStatement.setInt(3, comment.getUser_id());
            preparedStatement.setString(4, comment.getUsername());
            int result = preparedStatement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed inserting comment: " + e);
            throw new DaoException("Failed inserting comment: " + e);
        }
    }


    @Override
    public List<Comment> findAllCommentByMovieId(long id) throws DaoException {
        List<Comment> commentList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMMENT_BY_MOVIE_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Optional<Comment> optionalComment = convertToComment.convert(resultSet);
                optionalComment.ifPresent(commentList::add);
            }
            return commentList;
        } catch (SQLException e) {
            logger.error("Failed finding all comment: " + e);
            throw new DaoException("Failed finding all comment: " + e);
        }
    }

    @Override
    public boolean update(Comment comment) throws DaoException {
        boolean returnValue;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)){
            preparedStatement.setString(1,comment.getComment_text());
            preparedStatement.setInt(2,comment.getId());
            int result = preparedStatement.executeUpdate();
            returnValue = result>0?true:false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed updating comment: " + e);
            throw new DaoException("Failed updating comment: " + e);
        }
    }
}
