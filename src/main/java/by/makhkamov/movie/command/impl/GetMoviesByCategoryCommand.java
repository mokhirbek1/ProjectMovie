package by.makhkamov.movie.command.impl;

import by.makhkamov.movie.command.Command;
import by.makhkamov.movie.command.Router;
import by.makhkamov.movie.entity.Movie;
import by.makhkamov.movie.entity.type.Category;
import by.makhkamov.movie.exception.CommandException;
import by.makhkamov.movie.exception.ServiceException;
import by.makhkamov.movie.service.impl.MovieServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class GetMoviesByCategoryCommand implements Command {
    MovieServiceImpl movieService = MovieServiceImpl.getInstance();
    private static final String MOVIE_LIST = "movie_list";

    Logger logger = LogManager.getLogger();
    List<Movie> movieList = new ArrayList<>();
    List<Movie> newList = new ArrayList<>();
    List<Movie> filmList = new ArrayList<>();
    List<Movie> bestList = new ArrayList<>();
    List<Movie> adventureList = new ArrayList<>();
    List<Movie> serialList = new ArrayList<>();
    List<Movie> cartoonList = new ArrayList<>();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            movieList = movieService.findAll();
            logger.log(Level.INFO, "Successfully found all movie");
            for (Movie movie:movieList){
                if (movie.equals(Category.NEW)){
                    logger.log(Level.INFO, "New movie is: "+movie.getName());
                    newList.add(movie);
                }else if (movie.equals(Category.FILM)){
                    logger.log(Level.INFO, "Film movie is: "+movie.getName());
                    filmList.add(movie);
                }else if (movie.equals(Category.BEST)){
                    logger.log(Level.INFO, "Best movie is: "+movie.getName());
                    bestList.add(movie);
                }else if(movie.equals(Category.ADVENTURE)){
                    logger.log(Level.INFO, "Adventure movie is: "+movie.getName());
                    adventureList.add(movie);
                }else if (movie.equals(Category.SERIALS)){
                    logger.log(Level.INFO, "Serial movie is: "+movie.getName());
                    serialList.add(movie);
                }else if (movie.equals(Category.CARTOON)){
                    logger.log(Level.INFO, "Cartoon movie is: "+movie.getName());
                    cartoonList.add(movie);
                }
            }
            request.setAttribute("new_list", newList);
            request.setAttribute("best_list", bestList);
            request.setAttribute("adventure_list", adventureList);
            request.setAttribute("serial_list", serialList);
            request.setAttribute("cartoon_list", cartoonList);
            request.setAttribute("film_list", filmList);
            request.setAttribute(MOVIE_LIST, movieList);
            return new Router();
        } catch (ServiceException serviceException) {
            logger.error("Failed in getting movies by category"+serviceException);
            throw new CommandException(serviceException);
        }
    }
}
