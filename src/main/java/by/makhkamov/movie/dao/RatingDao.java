package by.makhkamov.movie.dao;

import by.makhkamov.movie.entity.Rating;
import by.makhkamov.movie.exception.DaoException;

import java.util.Optional;

public interface RatingDao extends BaseDao<Rating>{
    Optional<Rating> getRatingByMovieId(Long id) throws DaoException;
}
