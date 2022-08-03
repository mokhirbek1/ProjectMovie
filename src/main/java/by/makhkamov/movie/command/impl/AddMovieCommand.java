package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.entity.Rating;
import by.makhkamov.movie.entity.type.Category;
import by.makhkamov.movie.entity.type.Language;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.MovieServiceImpl;
import by.makhkamov.movie.service.impl.RatingServiceImpl;
import by.makhkamov.movie.validation.CheckMovie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AddMovieCommand implements Command {
    private static final String MOVIE_NAME = "movieName";
    private static final String COUNTRY_NAME = "countryName";
    private static final String CREATED_YEAR = "createdYear";
    private static final String CATEGORY = "category";
    private static final String LANGUAGE = "language";
    private static final String AGE_LIMIT = "ageLimit";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE_PATH = "imagePath";
    private static final String CURRENT_PAGE = "current_page";
    private static final String ADD_PAGE = "/pages/main/admin/movies/movie_action/add.jsp";




    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        Router router;
        MovieServiceImpl movieService = MovieServiceImpl.getInstance();
        RatingServiceImpl ratingService = RatingServiceImpl.getInstance();
        String movieName = request.getParameter(MOVIE_NAME);
        String country = request.getParameter(COUNTRY_NAME);
        int createdYear = Integer.parseInt(request.getParameter(CREATED_YEAR));
        Category category = Category.valueOf(request.getParameter(CATEGORY).toUpperCase());
        Language language = Language.valueOf(request.getParameter(LANGUAGE).toUpperCase());
        int ageLimit = Integer.parseInt(request.getParameter(AGE_LIMIT));
        String description = request.getParameter(DESCRIPTION);
        String image_url = request.getParameter(IMAGE_PATH);



        logger.log(Level.INFO,"Movie name: " + movieName);
        logger.log(Level.INFO,"Country of movie:  " + country);
        logger.log(Level.INFO,"Created year: " + createdYear);
        logger.log(Level.INFO,"Category of movie: " + category);
        logger.log(Level.INFO,"Age limit : " + ageLimit);
        logger.log(Level.INFO,"Description of movie: " + description);
        logger.log(Level.INFO,"Url to picture of movie " + image_url);

        logger.log(Level.INFO,"Current page: " + session.getAttribute(CURRENT_PAGE));
        if (!CheckMovie.checkMovieValue(movieName, country, createdYear, category.toString(), language.toString(), ageLimit, description, image_url)) {
            logger.error("Invalid input");
            router = new Router(ADD_PAGE, Router.Type.FORWARD);
            return router;
        }
        boolean isMatched = CheckMovie.checkMovieValue(movieName, country, createdYear, category.toString(), language.toString(), ageLimit, description, image_url);

        try {
            if (isMatched) {
                if (movieService.movieNameSuitable(movieName)) {
                    Movie movie = new Movie();
                    movie.setName(movieName);
                    movie.setCountry(country);
                    movie.setCreated_year(createdYear);
                    movie.setCategory(category);
                    movie.setLanguage(language);
                    movie.setAge_limit(ageLimit);
                    movie.setDescription(description);
                    movie.setImage_path(image_url);
                    if (movieService.insert(movie)) {
                        Optional<Integer> optionalIdOfMovie = movieService.getIdByName(movie.getName());
                        if (optionalIdOfMovie.isEmpty()) {
                            logger.error("optionalIdOfMovie is don't have anything");
                            throw new CommandException("optionalIdOfMovie is don't have anything");
                        }
                        Rating rating = new Rating();
                        int movieId = optionalIdOfMovie.get();
                        rating.setMovie_id(movieId);
                        ratingService.insert(rating);
                        return new FindMoviesCommand().execute(request);
                    } else {
                        logger.error("Movie is not saved to Database ");
                        return new Router(ADD_PAGE, Router.Type.FORWARD);
                    }
                } else {
                    logger.error("Movie is already in Database ");
                    return new FindMoviesCommand().execute(request);
                }

            } else {
                logger.error("Movie is not validated ");
                return new Router(ADD_PAGE, Router.Type.FORWARD);
            }
        } catch (ServiceException serviceException) {
            logger.error("Error in registering movie " + serviceException);
            throw new CommandException(serviceException);
        }
    }
}
