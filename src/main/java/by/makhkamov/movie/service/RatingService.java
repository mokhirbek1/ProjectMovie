package by.makhkamov.movie.service;

import by.makhkamov.movie.entity.Rating;
import by.makhkamov.movie.exception.ServiceException;

import java.util.Optional;

public interface RatingService extends BaseService<Rating> {
    Optional<Rating> getRatingByMovieId(Long id) throws ServiceException;
}
