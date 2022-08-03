package by.makhkamov.movie.dao;

import by.makhkamov.movie.entity.Comment;
import by.makhkamov.movie.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface CommentDao extends BaseDao<Comment>{
    List<Comment> findAllCommentByMovieId(long id) throws DaoException;
}
