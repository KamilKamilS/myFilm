package com.myfilm.repository;

import com.myfilm.model.Movie;
import com.myfilm.service.exception.MovieNotFoundInCatalogueException;

import java.util.Map;

public interface CartService {

    void addMovieToCart(Movie movie) throws MovieNotFoundInCatalogueException;

    Map<Movie, Integer> getSessionCart();
}
