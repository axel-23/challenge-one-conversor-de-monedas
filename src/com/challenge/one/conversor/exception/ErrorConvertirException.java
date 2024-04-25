package com.challenge.one.conversor.exception;

public class ErrorConvertirException extends RuntimeException {

    private String mensajeDeError;

    public ErrorConvertirException(String mensajeDeError) {
        this.mensajeDeError = mensajeDeError;
    }

    @Override
    public String getMessage() {
        return this.mensajeDeError;
    }
}