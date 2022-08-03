package by.makhkamov.movie.dao.converter.impl;

import by.makhkamov.movie.dao.converter.BaseConverter;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.entity.type.Category;
import by.makhkamov.movie.entity.type.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConvertToMovie implements BaseConverter<Movie> {
    private static final Logger logger = LogManager.getLogger();
    private static final String MOVIE_ID = "movie_id";
    private static final String MOVIE_NAME = "movie_name";
    private static final String COUNTRY = "movie_country";
    private static final String CREATED_YEAR = "created_year";
    private static final String CATEGORY = "category";
    private static final String LANGUAGE = "language";
    private static final String AGE_LIMIT = "age_limit";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_PATH = "image_path";
    private static final String RATING_VALUE = "rating_value";
    @Override
    public Optional<Movie> convert(ResultSet resultSet) {
        Movie movie = new Movie();
        try {
            movie.setId(resultSet.getInt(MOVIE_ID));
            logger.info("movie_id="+resultSet.getInt(MOVIE_ID)+"\n");
            movie.setName(resultSet.getString(MOVIE_NAME));
            logger.info("movie_name="+resultSet.getString(MOVIE_NAME)+"\n");
            movie.setCategory(Category.valueOf(resultSet.getString(CATEGORY)));
            logger.info("category="+resultSet.getString(CATEGORY)+"\n");
            movie.setLanguage(Language.valueOf(resultSet.getString(LANGUAGE)));
            logger.info("language="+resultSet.getString(LANGUAGE)+"\n");
            movie.setCountry(resultSet.getString(resultSet.getString(COUNTRY)));
            logger.info("movie_country="+resultSet.getString(COUNTRY)+"\n");
            movie.setDescription(resultSet.getString(DESCRIPTION));
            logger.info("description="+resultSet.getString(DESCRIPTION)+"\n");
            movie.setAge_limit(resultSet.getInt(AGE_LIMIT));
            logger.info("age_limit="+resultSet.getInt(AGE_LIMIT)+"\n");
            movie.setImage_path(resultSet.getString(IMAGE_PATH));
            logger.info("image_path="+resultSet.getString(IMAGE_PATH)+"\n");
            movie.setId(resultSet.getInt(RATING_VALUE));
            logger.info("rating_value="+resultSet.getInt(RATING_VALUE)+"\n");
            movie.setCreated_year(resultSet.getInt(CREATED_YEAR));
            logger.info("created_year="+resultSet.getInt(CREATED_YEAR)+"\n");
            return Optional.of(movie);
        } catch (SQLException e) {
            logger.error("Failed in converting movie values to movie object");
            return Optional.empty();
        }
    }
}
