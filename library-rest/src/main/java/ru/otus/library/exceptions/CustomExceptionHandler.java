package ru.otus.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler({ConstraintViolationException.class, MethodArgumentNotValidException.class})
  public ResponseEntity<?> handleBadRequest() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(Map.of("success", false, "status", HttpStatus.BAD_REQUEST.value()));
  }

}
