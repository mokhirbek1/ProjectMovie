package by.makhkamov.movie.dao.impl;

import by.makhkamov.movie.dao.MovieDao;
import by.makhkamov.movie.dao.converter.impl.ConvertToMovie;
import by.makhkamov.movie.entity.Movie;
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

public class MovieDaoImpl implements MovieDao {
    private static final Logger logger = LogManager.getLogger();
    private final ConvertToMovie convertToMovie = new ConvertToMovie();
    private static final MovieDaoImpl instance = new MovieDaoImpl();
    private static final String SELECT_ALL_MOVIE = "SELECT movie_id,movie_name,movie_country,created_year,category,age_limit,description,image_path, rating_value, language FROM movieproject.movies";
    private static final String INSERT_MOVIE_QUERY = "INSERT INTO movies (movie_name,movie_country,created_year,category,age_limit, description,image_path, language) VALUES(?,?,?,?,?,?,?,?)";
    private static final String DELETE_MOVIE_BY_ID = "DELETE FROM movies WHERE movie_id=?";
    private static final String FIND_BY_ID = "SELECT movie_id,movie_name,movie_country,created_year,category,language, age_limit,description,image_path, rating_value FROM movies WHERE movie_id = ?";
    private static final String EDIT_MOVIE = "UPDATE movies SET movie_name=?,movie_country=?,created_year=?,category=?,language =?, age_limit=?,description=?,image_path=? WHERE movie_id=?";
    private static final String CHECK_NAME = "SELECT movie_name FROM movies WHERE movie_name=?";
    private static final String FIND_ID_BY_NAME = "SELECT movie_id from movies WHERE movie_name = ?";
    private static final String FIND_BY_CATEGORY = "SELECT * FROM movieproject.movies WHERE category=?";
    private static final String MOVIE_ID = "movie_id";

    private MovieDaoImpl() {

    }

    public static MovieDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Movie> findByCategory(String movieCategory) throws DaoException {
        List<Movie> movieList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_CATEGORY)) {
            preparedStatement.setString(1, movieCategory);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Optional<Movie> movieOptional = convertToMovie.convert(resultSet);
                movieOptional.ifPresent(movieList::add);
            }
            return movieList;
        } catch (SQLException e) {
            logger.error("Failed finding movie by category : " + e);
            throw new DaoException("Failed finding movie by category: " + e);
        }
    }

    @Override
    public boolean insert(Movie movie) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_MOVIE_QUERY)) {
            statement.setString(1, movie.getName());
            statement.setString(2, movie.getCountry());
            statement.setInt(3, movie.getCreated_year());
            statement.setString(4, String.valueOf(movie.getCategory()));
            statement.setInt(5, movie.getAge_limit());
            statement.setString(6, movie.getDescription());
            statement.setString(7, movie.getImage_path());
            statement.setString(8, String.valueOf(movie.getLanguage()));
            int result = statement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed inserting movie: " + e);
            throw new DaoException("Failed inserting movie: " + e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_MOVIE_BY_ID)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed deleting movie: " + e);
            throw new DaoException("Failed deleting movie: " + e);
        }
    }

    @Override
    public List<Movie> findAll() throws DaoException {
        List<Movie> movieList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MOVIE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Optional<Movie> optionalMovie = convertToMovie.convert(resultSet);
                optionalMovie.ifPresent(movieList::add);
            }
            return movieList;
        } catch (SQLException e) {
            logger.error("Failed finding all movie: " + e);
            throw new DaoException("Failed finding all movie: " + e);
        }
    }

    @Override
    public Optional<Movie> getById(Long id) throws DaoException {
        Optional<Movie> movie = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movie = convertToMovie.convert(resultSet);
            }
            return movie;
        } catch (SQLException e) {
            logger.error("Failed finding movie by id: " + e);
            throw new DaoException("Failed finding movie by id: " + e);
        }
    }


    @Override
    public boolean update(Movie movie) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(EDIT_MOVIE)) {
            statement.setString(1, movie.getName());
            statement.setString(2, movie.getCountry());
            statement.setInt(3, movie.getCreated_year());
            statement.setString(4, String.valueOf(movie.getCategory()));
            statement.setString(5, String.valueOf(movie.getLanguage()));
            statement.setInt(6, movie.getAge_limit());
            statement.setString(7, movie.getDescription());
            statement.setString(8, movie.getImage_path());
            statement.setLong(9, movie.getId());
            int result = statement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed editing movie: " + e);
            throw new DaoException("Failed editing movie: " + e);
        }
    }

    @Override
    public boolean movieNameSuitable(String movieName) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_NAME)) {
            preparedStatement.setString(1, movieName);
            ResultSet resultSet = preparedStatement.executeQuery();
            returnValue = resultSet.next() ? false : true;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed checking movie name to add: " + e);
            throw new DaoException("Failed checking movie name to add: " + e);
        }
    }

    @Override
    public Optional<Integer> getIdByName(String name) throws DaoException {
        Optional<Integer> movie_id = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ID_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                movie_id = Optional.of(resultSet.getInt(MOVIE_ID));
            }
            return movie_id;
        } catch (SQLException e) {
            logger.error("Failed finding movie id by name: " + e);
            throw new DaoException("Failed finding movie id by name: " + e);
        }
    }

}
