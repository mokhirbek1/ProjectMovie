package by.makhkamov.movie.entity;

import java.util.Objects;

public class Rating extends AbstractEntity {
    private int id;
    private int movie_id;
    private int value;

    public Rating() {
    }

    public Rating(int id, int movie_id, int value) {
        this.id = id;
        this.movie_id = movie_id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return id == rating.id && movie_id == rating.movie_id && Double.compare(rating.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, movie_id, value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rating{ ")
                .append("id=" + id)
                .append(", movie_id=" + movie_id)
                .append(", value=" + value)
                .append("}");
        return builder.toString();
    }
}
