package by.makhkamov.movie.service.impl;

import by.makhkamov.movie.dao.impl.MovieDaoImpl;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.MovieService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class MovieServiceImpl implements MovieService {
    private static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
    private static MovieDaoImpl movieDao = MovieDaoImpl.getInstance();
    private static MovieServiceImpl instance = new MovieServiceImpl();

    private MovieServiceImpl() {

    }

    public static MovieServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Movie> findByCategory(String movieCategory) throws ServiceException {
        try {
            return movieDao.findByCategory(movieCategory);
        } catch (DaoException e) {
            logger.error("Failed finding movie by category: " + e);
            throw new ServiceException("Failed finding movie by category: " + e);
        }
    }

    @Override
    public Optional<Movie> getById(Long id) throws ServiceException {
        try {
            return movieDao.getById(id);
        } catch (DaoException e) {
            logger.error("Failed finding movie by id: " + e);
            throw new ServiceException("Failed finding movie by id: " + e);
        }
    }

    @Override
    public boolean update(Movie movie) throws ServiceException {
        try {
            return movieDao.update(movie);
        } catch (DaoException e) {
            logger.error("Failed updating movie: " + e);
            throw new ServiceException("Failed updating movie: " + e);
        }
    }

    @Override
    public boolean movieNameSuitable(String movieName) throws ServiceException {
        try {
            return movieDao.movieNameSuitable(movieName);
        } catch (DaoException e) {
            logger.error("Failed checking movie name: " + e);
            throw new ServiceException("Failed checking movie name: " + e);
        }
    }

    @Override
    public Optional<Integer> getIdByName(String name) throws ServiceException {
        try {
            return movieDao.getIdByName(name);
        } catch (DaoException e) {
            logger.error("Failed finding id by movie name: " + e);
            throw new ServiceException("Failed finding id by movie name: " + e);
        }
    }

    @Override
    public boolean insert(Movie movie) throws ServiceException {
        try {
            return movieDao.insert(movie);
        } catch (DaoException e) {
            logger.error("Failed inserting movie: " + e);
            throw new ServiceException("Failed inserting movie: " + e);
        }
    }

    @Override
    public boolean delete(Long id) throws ServiceException {
        try {
            return movieDao.delete(id);
        } catch (DaoException e) {
            logger.error("Failed deleting movie: " + e);
            throw new ServiceException("Failed deleting movie: " + e);
        }
    }

    @Override
    public List<Movie> findAll() throws ServiceException {
        try {
            return movieDao.findAll();
        } catch (DaoException e) {
            logger.error("Failed finding all movie: " + e);
            throw new ServiceException("Failed finding all movie: " + e);
        }
    }
}
