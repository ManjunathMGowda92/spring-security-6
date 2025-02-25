package org.fourstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler {

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ErrorDetails> handleException(UserAlreadyExistException exception, WebRequest request) {
    ErrorDetails errorDetails = ErrorDetails.builder()
            .errorMessage(exception.getMessage())
            .status(HttpStatus.BAD_REQUEST)
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .uriPath(request.getDescription(false))
            .timestamp(LocalDateTime.now())
            .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(errorDetails);
  }
}
