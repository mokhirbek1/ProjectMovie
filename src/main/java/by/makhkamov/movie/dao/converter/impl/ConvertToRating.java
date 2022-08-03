package by.makhkamov.movie.dao.converter.impl;

import by.makhkamov.movie.dao.converter.BaseConverter;
import by.makhkamov.movie.entity.Rating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConvertToRating implements BaseConverter<Rating> {
    private static final Logger logger = LogManager.getLogger(ConvertToRating.class);
    private static final String RATING_ID = "rating_id";
    private static final String MOVIE_ID = "movie_id";
    private static final String VALUE = "rating_value";
    @Override
    public Optional<Rating> convert(ResultSet resultSet) {
        Rating rating = new Rating();
        try {
            rating.setId(resultSet.getInt(RATING_ID));
            rating.setMovie_id(resultSet.getInt(MOVIE_ID));
            rating.setValue(resultSet.getInt(VALUE));
            return Optional.of(rating);
        } catch (SQLException e) {
            logger.error("Failed in converting rating values to rating object");
            return Optional.empty();
        }
    }
}
