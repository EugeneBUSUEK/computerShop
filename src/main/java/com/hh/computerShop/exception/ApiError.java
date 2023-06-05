package com.hh.computerShop.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static com.hh.computerShop.support.constant.DateFormat.API_ERROR_FORMAT;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ApiError {
    @JsonFormat(shape = STRING, pattern = API_ERROR_FORMAT)
    private Date timestamp;
    private Integer status;
    private HttpStatus error;
    private String message;
    private String path;
}
