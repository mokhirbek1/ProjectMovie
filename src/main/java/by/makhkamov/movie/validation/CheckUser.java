package by.makhkamov.movie.validation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUser {
    private static final Logger logger = LogManager.getLogger();
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z].*[A-Z])(?=.*[!@#$&*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z]).{8,}$";
    private static final String USERNAME_REGEX = "^[a-z0-9_-]{3,16}$";
    private static final String EMAIL_REGEX = "^(\\w\\.?)+@[\\w\\.-]+\\.\\w{2,4}";
    private static final String NAME_REGEX = "^[A-Za-z]{5,10}$";

    public static boolean passwordSuitable(String password) {
        Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = passwordPattern.matcher(password);
        boolean result = matcher.matches();
        if (result) {
            logger.log(Level.INFO, "Password suitable");
        } else {
            logger.log(Level.INFO, "Password not suitable");
        }
        return result;
    }

    public static boolean loginSuitable(String username) {
        Pattern loginPattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = loginPattern.matcher(username);
        boolean result = matcher.matches();
        if (result) {
            logger.log(Level.INFO, "Login suitable");
        } else {
            logger.log(Level.INFO, "Login not suitable");
        }
        return result;
    }

    public static boolean emailSuitable(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = emailPattern.matcher(email);
        boolean result = matcher.matches();
        if (result) {
            logger.log(Level.INFO, "Email suitable");
        } else {
            logger.log(Level.INFO, "Email not suitable");
        }
        return result;
    }

    public static boolean nameSuitable(String firstname) {
        Pattern firstnamePattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = firstnamePattern.matcher(firstname);
        boolean result = matcher.matches();
        if (result) {
            logger.log(Level.INFO, "Name suitable");
        } else {
            logger.log(Level.INFO, "Name not suitable");
        }
        return result;
    }
}
