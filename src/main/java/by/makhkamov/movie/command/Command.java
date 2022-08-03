package by.makhkamov.movie.command;

import by.makhkamov.movie.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
