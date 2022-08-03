package by.makhkamov.movie.entity;

import by.makhkamov.movie.entity.type.Category;
import by.makhkamov.movie.entity.type.Language;

import java.util.Objects;

public class Movie extends AbstractEntity {
    private int id;
    private String name;
    private String country;
    private int created_year;
    private Category category;
    private Language language;
    private int age_limit;
    private String description;
    private String image_path;
    private double rating_value;

    public Movie() {
    }

    public Movie(int id, String name, String country, int createdYear, String category, String language, int age_limit, String description, String image_path) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.created_year = createdYear;
        this.category = Category.valueOf(category);
        this.language = Language.valueOf(language);
        this.age_limit = age_limit;
        this.description = description;
        this.image_path = image_path;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCreated_year(int created_year) {
        this.created_year = created_year;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setAge_limit(int age_limit) {
        this.age_limit = age_limit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public void setRating_value(double rating_value) {
        this.rating_value = rating_value;
    }

    public int getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public Language getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public int getCreated_year() {
        return created_year;
    }

    public Category getCategory() {
        return category;
    }

    public double getRating_value() {
        return rating_value;
    }

    public int getAge_limit() {
        return age_limit;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_path() {
        return image_path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && created_year == movie.created_year && age_limit == movie.age_limit && Double.compare(movie.rating_value, rating_value) == 0 && Objects.equals(name, movie.name) && Objects.equals(country, movie.country) && Objects.equals(category, movie.category) && Objects.equals(description, movie.description) && Objects.equals(image_path, movie.image_path) && Objects.equals(language, movie.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, country, created_year, category, age_limit, description, image_path, rating_value, language);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Movie{ ")
                .append("id=" + id)
                .append(", name=" + name)
                .append(", country=" + country)
                .append(", created_year=" + created_year)
                .append(", category=" + category)
                .append(", age_limit=" + age_limit)
                .append(", description=" + description)
                .append(", image_path=" + image_path)
                .append(", rating_value=" + rating_value)
                .append(", language=" + language);
        return builder.toString();
    }
}


