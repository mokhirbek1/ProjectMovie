package by.makhkamov.movie.service;

import by.makhkamov.movie.entity.Comment;
import by.makhkamov.movie.exception.ServiceException;

import java.util.List;

public interface CommentService extends BaseService<Comment> {
    List<Comment> findAllCommentByMovieId(long id) throws ServiceException;

}
