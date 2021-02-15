package com.myfilm.service;

import com.myfilm.model.Movie;
import com.myfilm.model.Rate;
import com.myfilm.repository.MovieRepository;
import com.myfilm.repository.RateRepository;
import com.myfilm.service.exception.MovieNotFoundInCatalogueException;
import com.myfilm.service.exception.RateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RateService {

    private final MovieRepository movieRepository;
    private final RateRepository rateRepository;

    public Rate addNewRate(Long movieId, Rate rate) throws MovieNotFoundInCatalogueException {
        Optional<Movie> movieInCatalogue = movieRepository.findById(movieId);
        if (movieInCatalogue.isEmpty()) {
            throw new MovieNotFoundInCatalogueException(" ");
        } else {
//            Movie movieFromCatalogue = movieInCatalogue.get();
            List<Rate> rateList = movieInCatalogue.get().getRates();
            rateList.add(rate);
            movieInCatalogue.get().setRates(rateList);
            movieRepository.saveAndFlush(movieInCatalogue.get());
            return rate;
        }
    }

    public List<Rate> getAllRatesOfMovie(Long movieId) throws MovieNotFoundInCatalogueException {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            throw new MovieNotFoundInCatalogueException(" ");
        } else {
            List<Rate> rateList = movie.get().getRates();
            return rateList;
        }
    }
    // TODO-????

    public Optional<Rate> getRateById(Long rateId) throws RateNotFoundException {
        Optional<Rate> rate = rateRepository.findById(rateId);
        if (rate.isEmpty()) {
            throw new RateNotFoundException();
        } else {
            return rate;
        }
    }
}
