package com.myfilm.service;

import com.myfilm.model.Movie;
import com.myfilm.repository.CartService;
import com.myfilm.repository.MovieRepository;
import com.myfilm.service.exception.MovieNotFoundInCatalogueException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionCartService implements CartService {

    private Map<Movie, Integer> movies;
    private Map<Movie, Integer> unavailableMovies;
    private MovieRepository movieRepository;

    @Override
    public void addMovieToCart(Movie movie) throws MovieNotFoundInCatalogueException {
        if (movieRepository.findMovieByTitle(movie.getTitle()).isEmpty()) {
            throw new MovieNotFoundInCatalogueException(movie.getTitle());
        } else {
            movies.put(movie, this.getQuantities(movie));
        }
    }

    public Movie removeOneCopyOfMovieFromCart(Movie movie) throws MovieNotFoundInSessionCart {
        if (movies.containsKey(movie)) {
            removeOneCopy(movie);
            return movie;
        } else {
            throw new MovieNotFoundInSessionCart(movie.getTitle());
        }
    }

    private void removeOneCopy(Movie movie) {
        int numberOfCopies = movies.get(movie);
        if (numberOfCopies > 1) {
            movies.replace(movie, --numberOfCopies);
        } else {
            movies.remove(movie);
        }
    }

    @Override
    public Map<Movie, Integer> getSessionCart() {
        return null;
    }

    private int getQuantities(Movie movie) {
        return movies.get(movie) == null ? 1 : movies.get(movie) + 1;
    }

}
