package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.entity.type.Category;
import by.makhkamov.movie.entity.type.Language;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.MovieServiceImpl;
import by.makhkamov.movie.validation.CheckMovie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.Optional;

public class EditMovieCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String MOVIE_ID = "id";
    private static final String MOVIE_NAME = "movieName";
    private static final String COUNTRY_NAME = "countryName";
    private static final String CREATED_YEAR = "createdYear";
    private static final String CATEGORY = "category";
    private static final String AGE_LIMIT = "ageLimit";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_PATH = "imagePath";
    private static final String EDIT_PAGE = "/pages/main/admin/movies/movie_action/edit.jsp";



    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        MovieServiceImpl movieService = MovieServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String returnPage = (String) session.getAttribute("return_page");
        Movie movieForUpdate;
        long id = (long) session.getAttribute(MOVIE_ID);
        logger.info("Movie is " + id);


        try {
            Optional<Movie> optionalMovie = movieService.getById(id);
            if (optionalMovie.isEmpty()) {
                throw new CommandException("Didn't find movie with id: " + id);
            }

            movieForUpdate = optionalMovie.get();
            logger.info("movie received from data base to update: " + movieForUpdate);
            Router router;
            String movieName = request.getParameter(MOVIE_NAME);
            String country = request.getParameter(COUNTRY_NAME);
            int createdYear = Integer.parseInt(request.getParameter(CREATED_YEAR));
            Category category = Category.valueOf(request.getParameter(CATEGORY).toUpperCase(Locale.ROOT));
            Language language = Language.valueOf(request.getParameter("language").toUpperCase());
            int ageLimit = Integer.parseInt(request.getParameter(AGE_LIMIT));
            String description = request.getParameter(DESCRIPTION);
            String imagePath = request.getParameter(IMAGE_PATH);


            logger.info("trying to update movie name to: " + movieName);
            logger.info("trying to update movie country to: " + country);
            logger.info("trying to update movie created year to: " + createdYear);
            logger.info("trying to update movie category to: " + category);
            logger.info("trying to update movie language to: " + language);
            logger.info("trying to update movie age limit to: " + ageLimit);
            logger.info("trying to update movie description to: " + description);
            logger.info("trying to update movie image url to: " + imagePath);
            if (!CheckMovie.checkMovieValue(movieName, country, createdYear, category.toString(), language.toString(), ageLimit, description, imagePath)) {
                logger.error("Invalid input");
                router = new Router(EDIT_PAGE, Router.Type.FORWARD);
                return router;
            }
            boolean isSuitable = CheckMovie.checkMovieValue(movieName, country, createdYear, category.toString(), language.toString(), ageLimit, description, imagePath);

            if (isSuitable) {
                movieForUpdate.setId((int) id);
                movieForUpdate.setName(movieName);
                movieForUpdate.setCountry(country);
                movieForUpdate.setCreated_year(createdYear);
                movieForUpdate.setCategory(category);
                movieForUpdate.setLanguage(language);
                movieForUpdate.setAge_limit(ageLimit);
                movieForUpdate.setDescription(description);
                movieForUpdate.setImage_path(imagePath);

                if (movieService.update(movieForUpdate)) {
                    if (returnPage.equals("admin_page")) {
                        router = new AdminPageCommand().execute(request);
                        logger.info("Router in MovieDeleteCommand: " + router);
                        return router;
                    } else{
                        router = new FindMoviesCommand().execute(request);
                        logger.info("Router in MovieDeleteCommand: " + router);
                        return router;
                    }
                } else {
                    logger.error("Movie is not edited to Database ");
                    return new Router(EDIT_PAGE, Router.Type.FORWARD);
                }
            } else {
                logger.error("Info of movie isn't suitable ");
                return new Router(EDIT_PAGE, Router.Type.FORWARD);
            }

        } catch (ServiceException serviceException) {
            logger.error("Failed in editing movie " + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
