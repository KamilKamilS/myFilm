package com.myfilm.service;

import com.myfilm.model.Copy;
import com.myfilm.model.Movie;
import com.myfilm.repository.CopyRepository;
import com.myfilm.repository.MovieRepository;
import com.myfilm.service.exception.MovieNotFoundInCatalogueException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CopyService {

    MovieRepository movieRepository;
    CopyRepository copyRepository;

    public Copy createNewCopy(Movie movie) throws MovieNotFoundInCatalogueException {
        if(movieRepository.findById(movie.getMovieId()).isEmpty()) {
            throw new MovieNotFoundInCatalogueException(movie.getTitle());
        } else {
            Copy copy = new Copy();
            copy.setMovie(movie);
            return copyRepository.saveAndFlush(copy);
        }
    }
}
