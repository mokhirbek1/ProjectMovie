package by.makhkamov.movie.dao;

import by.makhkamov.movie.entity.type.Status;
import by.makhkamov.movie.entity.type.UserRole;
import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.entity.User;
import java.util.List;
import java.util.Optional;
public interface UserDao extends BaseDao<User>{
    Optional<User> findByLogin(String login) throws DaoException;
    UserRole getUserRole(String login) throws DaoException;
    Status getUserStatus(String login) throws DaoException;
    List<User> getUsersByRole(UserRole userRole) throws DaoException;
    boolean loginSuitable(String login) throws DaoException;
    boolean emailSuitable(String email) throws DaoException;
    boolean blockUserById(int user_id) throws DaoException;
    boolean activateUserById(int login) throws DaoException;
    boolean changeRole(int user_id, UserRole role) throws DaoException;
    List<String> getInfoByEmail(String email) throws DaoException;
    Optional<User> findById(Long id) throws DaoException;
    boolean authenticate(String login, String password) throws DaoException;

}
