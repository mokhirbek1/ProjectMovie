package by.makhkamov.movie.service;

import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface MovieService extends BaseService<Movie> {
    Optional<Integer> getIdByName(String name) throws ServiceException;
    List<Movie> findByCategory(String name) throws ServiceException;
    Optional<Movie> getById(Long id) throws ServiceException;
    boolean movieNameSuitable(String movieName) throws ServiceException;
    List<Movie> findAll() throws ServiceException;

}
