package by.makhkamov.movie.dao;

import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.entity.AbstractEntity;

import java.util.List;

public interface BaseDao<T extends AbstractEntity> {
    boolean delete(Long id) throws DaoException;
    boolean insert(T t) throws DaoException;
    boolean update(T t) throws DaoException;

}
