package by.makhkamov.movie.entity;

import java.util.Objects;

public class Comment extends AbstractEntity {
    private int id;
    private String comment_text;
    private int movie_id;
    private int user_id;
    private String username;

    public Comment() {
    }

    public Comment(int id, String comment_text, int movie_id) {
        this.id = id;
        this.comment_text = comment_text;
        this.movie_id = movie_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && movie_id == comment.movie_id && user_id == comment.user_id && Objects.equals(comment_text, comment.comment_text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment_text, movie_id, user_id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Comment{ ")
                .append("id="+id)
                .append(", comment_text="+comment_text)
                .append(", movie_id="+movie_id)
                .append(", user_id="+user_id)
                .append("}");
                return builder.toString();
    }
}
