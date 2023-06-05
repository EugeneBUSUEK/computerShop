package com.hh.computerShop.api.advice;

import com.hh.computerShop.support.mapper.ApiErrorMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {
        return ApiErrorMapper.errorToEntity(UNSUPPORTED_MEDIA_TYPE, ex.getMessage(), request);
    }

    @Override
    protected ResponseEntity handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return ApiErrorMapper.errorToEntity(BAD_REQUEST, ex.getMessage(), request);
    }

    @Override
    protected ResponseEntity handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {
        return ApiErrorMapper.errorToEntity(BAD_REQUEST, ex.getMessage(), request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(
            RuntimeException e, WebRequest request) {
        return ApiErrorMapper.errorToEntity(INTERNAL_SERVER_ERROR, e.getMessage(), request);
    }
}
