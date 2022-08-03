package by.makhkamov.movie.command;

import by.makhkamov.movie.command.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandType {

    NEW_PASSWORD(new NewPasswordCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    VALIDATE_PASSWORD(new ValidatePasswordCommand()),
    UPDATE_PASSWORD_BY_EMAIL(new UpdatePasswordByEmailCommand()),
    CHANGE_USER_ROLE(new ChangeUserRoleCommand()),
    DELETE_USER(new DeleteUserCommand()),
    ACTIVATE_USER(new ActivateUserCommand()),
    UPDATE_MOVIE(new UpdateMovieCommand()),
    ADMIN_PAGE(new AdminPageCommand()),
    HOME_PAGE(new HomePageCommand()),
    MOVIE_INFO_PAGE_FOR_USER(new MovieInfoPageForUserCommand()),
    MOVIE_INFO_PAGE(new MovieInfoPageCommand()),
    DEFAULT_COMMAND(new DefaultCommand()),
    FIND_MOVIES(new FindMoviesCommand()),
    COMMENT_POST(new CommentPostCommand()),
    FIND_USERS(new FindUsersCommand()),
    FIND_ADMINS(new FindAdminsCommand()),
    ADD_MOVIE(new AddMovieCommand()),
    DELETE_MOVIE(new DeleteMovieCommand()),
    EDIT_MOVIE(new EditMovieCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    MOVIE_RATING(new MovieRatingCommand()),
    MOVIE_RATING_FOR_USER(new MovieRatingForUserCommand()),
    REGISTRATION(new RegistrationCommand()),
    BLOCK_USER(new BlockUserCommand()),
    MOVIE_CATEGORY_PAGE(new MovieCategoryPageCommand()),
    ADMIN_MOVIE_CATEGORY_PAGE(new AdminMovieCategoryPageCommand()),
    DELETE_COMMENT(new DeleteCommentCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }
    public static Command define(String commandStr){
        final Logger logger = LogManager.getLogger();
        CommandType currentType = CommandType.DEFAULT_COMMAND;
        if (commandStr == null && commandStr.isBlank()) {
            return currentType.command;
        }

        try {
            currentType = CommandType.valueOf(commandStr.toUpperCase());
            return currentType.command;
        } catch (IllegalArgumentException e) {
            logger.error("Default command: "+commandStr+" "+e);
            throw new RuntimeException(e);
        }
    }

    public Command getCommand() {
        return command;
    }
}
