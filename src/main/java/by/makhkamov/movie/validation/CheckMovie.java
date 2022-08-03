package by.makhkamov.movie.validation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckMovie {
    private static final Logger logger = LogManager.getLogger();

    private CheckMovie() {
    }

    public static boolean checkMovieValue(String movieName, String country, int createdYear, String category, String language, int ageLimit, String description, String image_url) {
        if (movieName.isEmpty() || country.isEmpty() || createdYear==0 || category.isEmpty() || language.isEmpty() || ageLimit==0 || description.isEmpty() || image_url.isEmpty()) {
            return false;
        }
        if (movieName.length() < 4 || movieName.length() > 50) {
            logger.log(Level.DEBUG, "movieName: "+movieName+" not suitable\n");
            return false;
        }
        if (country.length() < 3 || country.length() > 45) {
            logger.log(Level.DEBUG, "country: "+country+" not suitable\n");
            return false;
        }
        if (createdYear < 1930 || createdYear > 2023) {
            logger.log(Level.DEBUG, "createdYear: "+createdYear+" not suitable\n");
            return false;
        }
        if (!category.equals("NEW") || !category.equals("BEST") || !category.equals("ADVENTURE") || !category.equals("SERIALS")  || !category.equals("CARTOON") || !category.equals("FILM") || !category.equals("COMEDY")){
            logger.log(Level.DEBUG, "category: "+createdYear+" not suitable\n");
            return false;
        }
        if(!language.equals("RUSSIAN") || !language.equals("ENGLISH")){
            logger.log(Level.DEBUG, "language: "+createdYear+" not suitable\n");
            return false;
        }
        if (ageLimit < 1 || ageLimit > 20) {
            logger.log(Level.DEBUG, "ageLimit: "+ageLimit+" not suitable\n");
            return false;
        }
        if (description.length() > 1500) {
            logger.log(Level.DEBUG, "description: "+description+" not suitable\n");
            return false;
        }
        if (image_url.length() < 10 || image_url.length() > 400) {
            logger.log(Level.DEBUG, "image_path: "+image_url+" not suitable\n");
            return false;
        }
        return true;
    }
}
