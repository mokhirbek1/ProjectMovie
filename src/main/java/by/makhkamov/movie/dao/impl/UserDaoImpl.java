package by.makhkamov.movie.dao.impl;


import by.makhkamov.movie.dao.UserDao;
import by.makhkamov.movie.dao.converter.impl.ConvertToUser;
import by.makhkamov.movie.entity.type.Status;
import by.makhkamov.movie.exception.DaoException;
import by.makhkamov.movie.entity.User;
import by.makhkamov.movie.entity.type.UserRole;
import by.makhkamov.movie.pool.ConnectionPool;
import by.makhkamov.movie.util.PasswordCoding;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private final ConvertToUser convertToUser = new ConvertToUser();
    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String ROLE = "role";
    private static final String STATUS = "status";
    private static final String INSERT_USER_QUERY = "INSERT INTO movieproject.users (email,role,first_name,last_name,user_name,password,status) VALUES(?,?,?,?,?,?,?)";
    private static final String CHANGE_ROLE = "UPDATE movieproject.users SET role = ? WHERE user_id = ?";
    private static final String CHECK_FOR_LOGIN = "SELECT password FROM movieproject.users WHERE user_name=?";

    private static final String CHECK_LOGIN = "SELECT first_name FROM movieproject.users WHERE users.user_name=?";
    private static final String CHECK_EMAIL = "SELECT first_name FROM movieproject.users WHERE users.email=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM movieproject.users WHERE user_id = ?";
    private static final String SELECT_BY_LOGIN = "SELECT user_id,email,role,first_name,last_name,user_name,password,status FROM movieproject.users WHERE user_name = ?";
    private static final String FIND_USER_ROLE_BY_LOGIN = "SELECT role FROM movieproject.users WHERE user_name = ?";
    private static final String FIND_USER_STATUS_BY_LOGIN = "SELECT status FROM movieproject.users WHERE user_name = ?";
    private static final String FIND_BY_ID = "SELECT user_id,email,role,first_name,last_name,user_name,password,status FROM movieproject.users WHERE user_id = ?";
    private static final String FIND_BY_ROLE = "SELECT user_id,email,role,first_name,last_name,user_name,password,status FROM movieproject.users WHERE role = ?";
    private static final String UPDATE_PASSWORD_BY_EMAIL = "UPDATE movieproject.users SET password = ? WHERE email = ?";

    private static final String BLOCK_USER = "UPDATE movieproject.users SET status = 'BLOCKED' WHERE user_id = ?";
    private static final String ACTIVATE_USER = "UPDATE movieproject.users SET status = 'ACTIVE' WHERE user_id = ?";
    private static final String GET_INFO_BY_EMAIL = "SELECT first_name, last_name FROM movieproject.users WHERE email=?";

    public static UserDaoImpl getInstance() {
        return instance;
    }

    private UserDaoImpl() {

    }

    @Override
    public List<String> getInfoByEmail(String email) throws DaoException {
        List<String> userInfo = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_INFO_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userInfo.add(resultSet.getString("first_name"));
                userInfo.add(resultSet.getString("last_name"));
                logger.log(Level.INFO, "Firstname is: " + userInfo.get(0));
                logger.log(Level.INFO, "Lastname is: " + userInfo.get(1));
            } else {
                userInfo = null;
            }
            return userInfo;
        } catch (SQLException e) {
            logger.error("Failed checking user email: " + e);
            throw new DaoException("Failed checking user email: " + e);
        }
    }

    @Override
    public boolean loginSuitable(String login) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            returnValue = !resultSet.next();
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed checking user login: " + e);
            throw new DaoException("Failed checking user login: " + e);
        }
    }

    @Override
    public boolean emailSuitable(String email) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            returnValue = !resultSet.next();
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed checking user email: " + e);
            throw new DaoException("Failed checking user email: " + e);
        }
    }

    @Override
    public boolean insert(User user) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_QUERY)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getRole().toString());
            statement.setString(3, user.getFirstname());
            statement.setString(4, user.getLastname());
            statement.setString(5, user.getUserName());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getStatus().toString());
            int result = statement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed inserting user: " + e);
            throw new DaoException("Failed inserting user: " + e);
        }
    }

    @Override
    public boolean changeRole(int user_id, UserRole role) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ROLE)) {
            preparedStatement.setString(1, role.toString());
            preparedStatement.setInt(2, user_id);
            int result = preparedStatement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed changing user role: " + e);
            throw new DaoException("Failed changing user role: " + e);
        }
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        boolean returnValue;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID)) {
            statement.setLong(1, id);
            int result = statement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException e) {
            logger.error("Failed deleting user: " + e);
            throw new DaoException("Failed deleting user: " + e);
        }
    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean returnValue;
        String newPassword = user.getPassword();
        String email = user.getEmail();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_BY_EMAIL)) {
            String hashedPassword = PasswordCoding.hashPassword(newPassword);
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setString(2, email);
            int result = preparedStatement.executeUpdate();
            returnValue = result > 0 ? true : false;
            return returnValue;
        } catch (SQLException sqlException) {
            logger.error("Failed in updating user password: " + email);
            throw new DaoException("Failed in updating user password: " + newPassword + " " + sqlException);
        }
    }

    @Override
    public Optional<User> findById(Long id) throws DaoException {
        Optional<User> user = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = convertToUser.convert(resultSet);
            }
            return user;
        } catch (SQLException e) {
            logger.error("Failed finding user by id: " + e);
            throw new DaoException("Failed finding user by id: " + e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws DaoException {
        Optional<User> user = Optional.empty();
        ;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = convertToUser.convert(resultSet);
            }
            return user;
        } catch (SQLException e) {
            logger.error("Failed finding user by login: " + e);
            throw new DaoException("Failed finding user by login: " + e);
        }
    }

    @Override
    public UserRole getUserRole(String login) throws DaoException {
        UserRole userRole = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_ROLE_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userRole = UserRole.valueOf(resultSet.getString(ROLE).toUpperCase());
            }
            return userRole;
        } catch (SQLException e) {
            logger.error("Failed finding user by role: " + e);
            throw new DaoException("Failed finding user by role: " + e);
        }
    }

    @Override
    public Status getUserStatus(String login) throws DaoException {
        Status status = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_STATUS_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    status = Status.valueOf(resultSet.getString(STATUS).toUpperCase());
                }
            return status;
        } catch (SQLException e) {
            logger.error("Failed finding user status: " + e);
            throw new DaoException("Failed finding user status: " + e);
        }
    }

    @Override
    public List<User> getUsersByRole(UserRole userRole) throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ROLE)) {
            statement.setString(1, userRole.toString().toUpperCase());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Optional<User> user = convertToUser.convert(resultSet);
                user.ifPresent(users::add);
            }
            return users;
        } catch (SQLException e) {
            logger.error("Failed finding user by role: " + e);
            throw new DaoException("Failed finding user by role: " + e);
        }
    }


    @Override
    public boolean blockUserById(int user_id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(BLOCK_USER)) {
            statement.setInt(1, user_id);
            int count = statement.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            logger.error("Failed blocking user by id: " + e);
            throw new DaoException("Failed bocking user by id: " + e);
        }
    }

    @Override
    public boolean activateUserById(int user_id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ACTIVATE_USER)) {
            statement.setInt(1, user_id);
            int count = statement.executeUpdate();
            return count == 1;
        } catch (SQLException e) {
            logger.error("Failed activating user by id: " + e);
            throw new DaoException("Failed activating user by id: " + e);
        }
    }


    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        if (login.isEmpty() || password.isEmpty()) {
            return false;
        }
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_FOR_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String passwordFromDb;
                if (resultSet.next()) {
                    passwordFromDb = resultSet.getString("password");
                    return PasswordCoding.checkPassword(password, passwordFromDb);
                }
            }
        } catch (SQLException e) {
            logger.error("Failed authenticating user: " + e);
            throw new DaoException("Failed authenticating user: " + e);
        }
        return false;
    }
}