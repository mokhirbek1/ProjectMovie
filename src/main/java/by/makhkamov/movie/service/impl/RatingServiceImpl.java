package by.makhkamov.movie.service.impl;

import by.makhkamov.movie.dao.impl.RatingDaoImpl;
import by.makhkamov.movie.entity.Rating;
import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class RatingServiceImpl implements RatingService {
    private static final Logger logger = LogManager.getLogger(RatingServiceImpl.class);
    private RatingDaoImpl ratingDao = RatingDaoImpl.getInstance();
    private static RatingServiceImpl instance = new RatingServiceImpl();

    public static RatingServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Rating rating) throws ServiceException {
        boolean isSaved;
        try {
            isSaved = ratingDao.insert(rating);
        }catch (DaoException daoException) {
            logger.error("Error in registration rating " + daoException);
            throw new ServiceException(daoException);
        }
        return isSaved;
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return ratingDao.delete(id);
        }catch (DaoException daoException) {
            logger.error("Error in deleting rating " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public Optional<Rating> getRatingByMovieId(Long id) throws ServiceException {
        try {
            return ratingDao.getRatingByMovieId(id);
        }catch (DaoException daoException) {
            logger.error("Error in finding rating by movie  id " + daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean update(Rating rating) throws ServiceException {
        try {
            return ratingDao.update(rating);
        }catch (DaoException daoException) {
            logger.error("Error in updating rating value by movie  id " + daoException);
            throw new ServiceException(daoException);
        }
    }
}
