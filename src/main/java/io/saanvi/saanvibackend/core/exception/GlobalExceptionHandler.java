package io.saanvi.saanvibackend.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.saanvi.saanvibackend.core.exception.common.*;


@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleGenericException(
      Exception ex, WebRequest request) {

    logger.error("Unexpected error occurred: ", ex);

    logger.error("Caught in global: {}", ex.getClass().getSimpleName(), ex);

    ErrorResponseDto errorResponse = new ErrorResponseDto(
        "An unexpected error occurred",
        "INTERNAL_SERVER_ERROR",
        HttpStatus.INTERNAL_SERVER_ERROR.value());

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(
      UserNotFoundException ex, WebRequest request) {

    logger.warn("User not found: {}", ex.getMessage());

    ErrorResponseDto errorResponse = new ErrorResponseDto(
        ex.getMessage(),
        "USER_NOT_FOUND",
        HttpStatus.NOT_FOUND.value());

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(
      UserAlreadyExistsException ex, WebRequest request) {
    logger.warn("User already exists: {}", ex.getMessage());

    ErrorResponseDto errorResponse = new ErrorResponseDto(
        ex.getMessage(),
        "USER_ALREADY_EXISTS",
        HttpStatus.CONFLICT.value());

    return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
  }


  @ExceptionHandler(InvalidParamsProvidedException.class)
  public ResponseEntity<ErrorResponseDto> handleInValidParamsErrorException(InvalidParamsProvidedException ex,
                                                                           WebRequest request) {
    logger.warn("Invalid Email Provided : {}", ex.getMessage());

    ErrorResponseDto errorResponse = new ErrorResponseDto(
        ex.getMessage(),
        "INVALID_PARAMS",
        HttpStatus.UNAUTHORIZED.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
