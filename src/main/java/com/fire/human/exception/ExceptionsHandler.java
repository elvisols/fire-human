package com.fire.human.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handlePersonNotFoundException(PersonNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false), HttpStatus.MULTI_STATUS.value());
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

    }

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ErrorDetails> handleGlobalError(Exception ex, WebRequest request){
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true), HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Getter @Setter
    @AllArgsConstructor
    private class ErrorDetails {
        private Date time;
        private String message;
        private String description;
        private Integer status;
    }

}
