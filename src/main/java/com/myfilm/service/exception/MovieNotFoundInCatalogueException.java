package com.myfilm.service.exception;

public class MovieNotFoundInCatalogueException extends Throwable {
    public MovieNotFoundInCatalogueException(String title) {
        super(title);
    }
}
