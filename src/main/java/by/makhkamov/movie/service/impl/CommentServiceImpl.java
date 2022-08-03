package by.makhkamov.movie.service.impl;

import by.makhkamov.movie.dao.impl.CommentDaoImpl;
import by.makhkamov.movie.entity.Comment;
import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger();
    private CommentDaoImpl commentDao = CommentDaoImpl.getInstance();
    private static CommentServiceImpl instance = new CommentServiceImpl();

    public static CommentServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Comment comment) throws ServiceException {
        try {
            return commentDao.insert(comment);
        }catch (DaoException e) {
            logger.error("Failed inserting comment: " + e);
            throw new ServiceException("Failed inserting comment: " + e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return commentDao.delete(id);
        }catch (DaoException e) {
            logger.error("Failed deleting comment: " + e);
            throw new ServiceException("Failed deleting comment: " + e);
        }
    }

    @Override
    public List<Comment> findAllCommentByMovieId(long id) throws ServiceException {
        try {
            return commentDao.findAllCommentByMovieId(id);
        }catch (DaoException e) {
            logger.error("Failed finding all comment by movie id: " + e);
            throw new ServiceException("Failed finding all comment by movie id: " + e);
        }
    }

    @Override
    public boolean update(Comment comment) throws ServiceException {
        try {
            return commentDao.update(comment);
        }catch (DaoException e) {
            logger.error("Failed updating comment: " + e);
            throw new ServiceException("Failed updating comment: " + e);
        }
    }
}
