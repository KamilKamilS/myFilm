package com.myfilm.service;

import com.myfilm.model.Genre;
import com.myfilm.model.Movie;
import com.myfilm.repository.MovieRepository;
import com.myfilm.service.exception.MovieAlreadyExistsInCatalogueException;
import com.myfilm.service.exception.MovieNotFoundInCatalogueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovieServiceTest {


    @Autowired
    MovieService movieService;

    @MockBean
    MovieRepository movieRepository;

    Movie movie;

    @Test
    public void shouldAddNewMovieToCatalogue() throws MovieAlreadyExistsInCatalogueException {
        // given
        movie = new Movie();
        movie.setTitle("Some title");
        movie.setGenre(Genre.ACTION);
        movie.setDescription("description");
        when(movieRepository.saveAndFlush(movie)).thenReturn(movie);

        // when
        Movie movieAdded = movieService.addMovieToCatalogue(movie);

        // then
        assertThat(movieAdded).isEqualTo(movie);
    }

    @Test
    public void shouldThrowExceptionWhenAddExistingMovieToCatalogue() throws MovieAlreadyExistsInCatalogueException {
        // given
        movie = new Movie();
        movie.setTitle("Some title");
        movie.setGenre(Genre.ACTION);
        movie.setDescription("description");
        when(movieRepository.findMovieByTitle(movie.getTitle())).thenReturn(Optional.of(movie));

        // when

        // then
        assertThatExceptionOfType(MovieAlreadyExistsInCatalogueException.class)
                .isThrownBy(() -> movieService.addMovieToCatalogue(movie));
    }

    @Test
    public void shouldGetMovieByTitle() throws MovieNotFoundInCatalogueException {
        // given
        movie = new Movie();
        movie.setTitle("Some title");
        movie.setGenre(Genre.DRAMA);
        movie.setDescription("description");
        when(movieRepository.findMovieByTitle(movie.getTitle())).thenReturn(Optional.of(movie));

        // when
        Movie getMovie = movieService.getMovieByTitle("Some title");

        // when
        assertThat(getMovie).isEqualTo(movie);
    }

    @Test
    public void shouldThrowExceptionWhenCantFindMovie() {
        // given
        String movieTitle = "Test movie title";
        when(movieRepository.findMovieByTitle(movieTitle)).thenReturn(Optional.empty());
        // when

        // then
        assertThatExceptionOfType(MovieNotFoundInCatalogueException.class)
                .isThrownBy(() ->movieService.getMovieByTitle(movieTitle));
    }
}