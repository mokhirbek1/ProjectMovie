package by.makhkamov.movie.service;

import by.makhkamov.movie.entity.type.Status;
import by.makhkamov.movie.entity.type.UserRole;
import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User> {
    boolean blockUserById(int user_id) throws ServiceException;
    boolean activateUserById(int user_id) throws ServiceException;
    List<User> findUsersByRole(UserRole userRole) throws ServiceException;
    boolean loginSuitable(String login) throws ServiceException;
    boolean emailSuitable(String email) throws ServiceException;
    boolean loginAuthenticate(String login, String password) throws ServiceException;
    Optional<User> findByLogin(String login) throws ServiceException;
    UserRole findUserRole(String login) throws ServiceException;
    Status findUserStatus(String login) throws ServiceException;
    boolean changeRole(int user_id, UserRole role) throws ServiceException;
    List<String> checkEmail(String email) throws ServiceException;
    Optional<User> findById(Long id) throws ServiceException;
}
