package com.myfilm.service.exception;

public class MovieAlreadyExistsInCatalogueException extends Exception {

    public MovieAlreadyExistsInCatalogueException(String message) {
        super("Movie already in catalogue : " + message);
    }
}
