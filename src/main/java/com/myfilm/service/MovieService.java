package com.myfilm.service;

import com.myfilm.model.Movie;
import com.myfilm.repository.MovieRepository;
import com.myfilm.service.exception.MovieAlreadyExistsInCatalogueException;
import com.myfilm.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MovieService {

    MovieRepository movieRepository;

    public Movie addMovieToCatalogue(Movie movie) throws MovieAlreadyExistsInCatalogueException {
        if (movieRepository.findMovieByTitle(movie.getTitle()).isEmpty()) {
            return movieRepository.saveAndFlush(movie);
        }
        throw new MovieAlreadyExistsInCatalogueException(movie.getTitle());
    }

    public List<Movie> readAllMoviesFromCatalogue() {
        return movieRepository.findAll();
    }
    
    public Movie getMovieByTitle(String title) throws MovieNotFoundInCatalogueException {
        return movieRepository.findMovieByTitle(title)
                .orElseThrow(() -> new MovieNotFoundInCatalogueException(title));
        
    }
}
