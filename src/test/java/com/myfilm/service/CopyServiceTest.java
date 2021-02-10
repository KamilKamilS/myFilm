package com.myfilm.service;

import com.myfilm.model.Copy;
import com.myfilm.model.Genre;
import com.myfilm.model.Movie;
import com.myfilm.repository.CopyRepository;
import com.myfilm.repository.MovieRepository;
import com.myfilm.service.exception.MovieNotFoundInCatalogueException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class CopyServiceTest {

    @Autowired
    CopyService copyService;

    @MockBean
    MovieRepository movieRepository;

    @MockBean
    CopyRepository copyRepository;

    @Test
    public void shouldCreateNewCopyOfMovie() throws MovieNotFoundInCatalogueException {
        // given
        Movie movie = new Movie();
        movie.setTitle("AAA");
        movie.setDescription("BBB");
        movie.setGenre(Genre.HORROR);

        Copy testCopy = new Copy();
        testCopy.setMovie(movie);

        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));
        when(copyRepository.saveAndFlush(any())).thenReturn(testCopy);

        // when
        Copy newCopy = copyService.createNewCopy(movie);

        // then
        Assertions.assertThat(newCopy.getMovie()).isEqualTo(testCopy.getMovie());
    }
}