package by.makhkamov.movie.service;

import by.makhkamov.movie.exception.ServiceException;

public interface BaseService<T> {
    boolean insert(T t) throws ServiceException;
    boolean delete(Long id) throws ServiceException;
    boolean update(T t) throws ServiceException;

}
