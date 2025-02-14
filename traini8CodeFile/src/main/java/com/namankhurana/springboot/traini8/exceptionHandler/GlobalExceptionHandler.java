package com.namankhurana.springboot.traini8.exceptionHandler;

import com.namankhurana.springboot.traini8.dto.ErrorResponseDTO;
import com.namankhurana.springboot.traini8.dto.ValidationErrorResponseDTO;
import com.namankhurana.springboot.traini8.exception.DuplicateCenterCodeException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ControllerAdvice
public class GlobalExceptionHandler {
    DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(DuplicateCenterCodeException.class)
    public ResponseEntity<ErrorResponseDTO> HandleDuplicateCenterCodeException(DuplicateCenterCodeException e, WebRequest request){
        ErrorResponseDTO response=new ErrorResponseDTO();
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setMessage(e.getMessage());
        response.setTimestamp(LocalDateTime.now().format(formatter));

        return new ResponseEntity<>(response,HttpStatus.CONFLICT);
    }

    // for request body validations
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> HandleValidationException(MethodArgumentNotValidException e, WebRequest request){
        ValidationErrorResponseDTO response=new ValidationErrorResponseDTO();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDateTime.now().format(formatter));

        // Extract validation errors
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())  // Field -> Error Message
        );

        response.setErrors(errors);
        response.setMessage("Validation failed. Please check input fields.");
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponseDTO> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
        ValidationErrorResponseDTO response = new ValidationErrorResponseDTO();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setTimestamp(LocalDateTime.now().format(formatter));

        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage())
        );

        response.setErrors(errors);
        response.setMessage("Validation failed. Please check input fields.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleHandlerNotFound(NoHandlerFoundException e,WebRequest request){
        ErrorResponseDTO response=new ErrorResponseDTO();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage("The requested endpoint does not exist.");
        response.setTimestamp(LocalDateTime.now().format(formatter));

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotSupported(HttpRequestMethodNotSupportedException e,WebRequest request){
        ErrorResponseDTO response=new ErrorResponseDTO();
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        response.setMessage("Request Method Not Supported: " + e.getMessage());
        response.setTimestamp(LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(response,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingParams(MissingServletRequestParameterException e,WebRequest request){
        ErrorResponseDTO response=new ErrorResponseDTO();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Missing required parameter: " + e.getParameterName());

        response.setTimestamp(LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidRequestBody(HttpMessageNotReadableException e,WebRequest request){

        String message = "Invalid request. Request body is missing or malformed.";
        //to handle Jackson errors deserialization failures
        if (e.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException) {
            message = "Invalid format in request body.";
        }


        ErrorResponseDTO response=new ErrorResponseDTO();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(message);
        response.setTimestamp(LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgumentException(IllegalArgumentException e,WebRequest request){
        ErrorResponseDTO response=new ErrorResponseDTO();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Illegal Argument : " + e.getMessage());
        response.setTimestamp(LocalDateTime.now().format(formatter));
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGlobalException(Exception e,WebRequest request){

        // Log error details
        logger.error("Unexpected error occurred" ,request.getDescription(false),  e);

        ErrorResponseDTO response=new ErrorResponseDTO();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessage("An unexpected error occurred. Please try again later.");
        response.setTimestamp(LocalDateTime.now().format(formatter));

        return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
