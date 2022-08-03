package by.makhkamov.movie.service.impl;

import by.makhkamov.movie.entity.type.Status;
import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.dao.impl.UserDaoImpl;
import by.makhkamov.movie.entity.User;
import by.makhkamov.movie.entity.type.UserRole;
import by.makhkamov.movie.service.UserService;
import by.makhkamov.movie.util.PasswordCoding;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;


public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final UserDaoImpl userDao=UserDaoImpl.getInstance();
    private static final UserServiceImpl instance = new UserServiceImpl();
    private UserServiceImpl(){

    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<String> checkEmail(String email) throws ServiceException {
        try {
            return userDao.getInfoByEmail(email);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error in checking user email: "+email+"  "+e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeRole(int user_id, UserRole role) throws ServiceException {
        try {
            return userDao.changeRole(user_id, role);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error in changing user status "+e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        }catch (DaoException e) {
            logger.error("Error in finding User By Login " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public UserRole findUserRole(String login) throws ServiceException {
        try {
            return userDao.getUserRole(login);
        }catch (DaoException e) {
            logger.error("Error in find User Role By Login " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Status findUserStatus(String login) throws ServiceException {
        try {
            return userDao.getUserStatus(login);
        }catch (DaoException e) {
            logger.error("Error in find User Role By Login");
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByRole(UserRole userRole) throws ServiceException {
        try {
            return userDao.getUsersByRole(userRole);
        }catch (DaoException e) {
            logger.error("Error in finding Users By UserRole " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean blockUserById(int user_id) throws ServiceException {
        try {
            return userDao.blockUserById(user_id);
        }catch (DaoException e) {
            logger.error("Error in change status to BLOCKED " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean activateUserById(int user_id) throws ServiceException {
        try {
            return userDao.activateUserById(user_id);
        }catch (DaoException e) {
            logger.error("Error in change status to ACTIVATED");
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean loginSuitable(String login) throws ServiceException {
        try {
            return userDao.loginSuitable(login);
        }catch (DaoException daoException) {
            logger.error("error in finding out whether login is available or not", daoException);
            throw new ServiceException(daoException);
        }
    }
    @Override
    public boolean emailSuitable(String email) throws ServiceException {
        try {
            return userDao.emailSuitable(email);
        } catch (DaoException daoException) {
            logger.error("error in finding out whether email is available or not", daoException);
            throw new ServiceException(daoException);
        }
    }

    @Override
    public boolean loginAuthenticate(String login, String password) throws ServiceException{
        try {
            return userDao.authenticate(login,password);
        } catch (DaoException e) {
            logger.error("Error in Authentication " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean insert(User user) throws ServiceException{
        boolean isSaved;
        try {
            user.setPassword(PasswordCoding.hashPassword(user.getPassword()));
            isSaved = userDao.insert(user);
        } catch (DaoException e) {
            logger.error("Error in Registration " + e);
            throw new ServiceException(e);
        }
        return isSaved;
    }

    @Override
    public boolean delete(Long id) throws ServiceException{
        try {
            return userDao.delete(id);
        } catch (DaoException e) {
            logger.error("Error in deleting User By Id " + e);
            throw new ServiceException(e);
        }

    }

    @Override
    public Optional<User> findById(Long id) throws ServiceException{
        try {
            return userDao.findById(id);
        }catch (DaoException e) {
            logger.error("Error in Finding User By Id " + e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(User user) throws ServiceException {
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error in updating password to new password by email: "+e);
            throw new ServiceException(e);
        }
    }
}
