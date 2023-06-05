package com.hh.computerShop.support.mapper;

import com.hh.computerShop.exception.ApiError;
import com.hh.computerShop.support.helper.DateHelper;
import com.hh.computerShop.support.helper.ServletPathHelper;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ApiErrorMapper {
    public static ResponseEntity errorToEntity(HttpStatus status, String message, WebRequest request) {
        ApiError apiError = new ApiError()
                .setTimestamp(DateHelper.getCurrentUtilDate())
                .setStatus(status.value())
                .setError(status)
                .setMessage(message)
                .setPath(ServletPathHelper.getServletPath(request));

        return new ResponseEntity(apiError, status);
    }
}
