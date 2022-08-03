package by.makhkamov.movie.dao.converter.impl;

import by.makhkamov.movie.dao.converter.BaseConverter;
import by.makhkamov.movie.entity.User;
import by.makhkamov.movie.entity.type.Status;
import by.makhkamov.movie.entity.type.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ConvertToUser implements BaseConverter<User> {
    private static final Logger logger = LogManager.getLogger();
    private static final String USER_ID = "user_id";
    private static final String EMAIL = "email";
    private static final String ROLE = "role";
    public static final String STATUS = "status";
    private static final String FIRSTNAME = "first_name";
    private static final String LASTNAME = "last_name";
    private static final String USERNAME = "user_name";
    @Override
    public Optional<User> convert(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt(USER_ID));
            user.setUserName(resultSet.getString(USERNAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setFirstname(resultSet.getString(FIRSTNAME));
            user.setLastname(resultSet.getString(LASTNAME));
            user.setRole(UserRole.valueOf(resultSet.getString(ROLE)));
            user.setStatus(Status.valueOf(resultSet.getString(STATUS)));
            return Optional.of(user);
        } catch (SQLException e) {
            logger.error("Failed in converting user values to user object");
            return Optional.empty();
        }
    }
}
