package by.makhkamov.movie.dao.impl;

import by.makhkamov.movie.dao.RatingDao;
import by.makhkamov.movie.dao.converter.impl.ConvertToRating;
import by.makhkamov.movie.entity.Rating;
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

public class RatingDaoImpl implements RatingDao {
    private static final Logger logger = LogManager.getLogger();
    private ConvertToRating convertToRating = new ConvertToRating();
    private static RatingDaoImpl instance = new RatingDaoImpl();
    private static final String UPDATE_MOVIE_RATING = "UPDATE movies SET rating_value=? WHERE movie_id=?";
    private static final String INSERT_RATING_QUERY = "INSERT INTO ratings (movie_id,rating_value) VALUES(?,?)";
    private static final String DELETE_RATING_BY_ID = "DELETE FROM ratings WHERE rating_id=?";
    private static final String DELETE_RATING_BY_MOVIE_ID = "DELETE FROM ratings WHERE movie_id=?";
    private static final String SELECT_ALL_RATING = "SELECT rating_id,movie_id, rating_value FROM ratings";
    private static final String FIND_BY_ID = "SELECT rating_id,movie_id, rating_value FROM ratings WHERE rating_id = ?";
    private static final String FIND_RATING_BY_MOVIE_ID = "SELECT rating_id,movie_id, rating_value FROM ratings WHERE movie_id = ?";
    private static final String UPDATE_VALUE = "UPDATE ratings SET rating_value=? WHERE movie_id=?";

    public static RatingDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Rating rating) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_RATING_QUERY)) {
            statement.setInt(1, rating.getMovie_id());
            statement.setInt(2, rating.getValue());
            int result = statement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed inserting rating: " + e);
            throw new DaoException("Failed inserting rating: " + e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_RATING_BY_ID)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed deleting rating: " + e);
            throw new DaoException("Failed deleting rating: " + e);
        }
    }

    @Override
    public boolean update(Rating rating) throws DaoException {
        int value = rating.getValue();
        int movie_id = rating.getMovie_id();
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement ratingStatement = connection.prepareStatement(UPDATE_VALUE)) {
            ratingStatement.setInt(1, value);
            ratingStatement.setInt(2, movie_id);
            int result = ratingStatement.executeUpdate();
            if (result > 0) {
                PreparedStatement movieStatement = connection.prepareStatement(UPDATE_MOVIE_RATING);
                movieStatement.setDouble(1, value);
                movieStatement.setLong(2, movie_id);
                int movieUpdate = movieStatement.executeUpdate();
                returnValue = movieUpdate>0?true:false;
                return returnValue;
            } else {
                logger.error("Failed in updating rating value");
                return false;
            }
        } catch (SQLException e) {
            logger.error("Failed updating rating value: " + e);
            throw new DaoException("Failed updating rating value: " + e);
        }
    }

    @Override
    public Optional<Rating> getRatingByMovieId(Long id) throws DaoException {
        Optional<Rating> optionalRating = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_RATING_BY_MOVIE_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    optionalRating = convertToRating.convert(resultSet);
                }
                return optionalRating;
        } catch (SQLException e) {
            logger.error("Failed updating rating value: " + e);
            throw new DaoException("Failed updating rating value: " + e);
        }
    }

}
