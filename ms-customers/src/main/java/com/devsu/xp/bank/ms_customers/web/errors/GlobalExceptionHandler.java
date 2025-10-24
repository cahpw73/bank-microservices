package com.devsu.xp.bank.ms_customers.web.errors;

import com.devsu.xp.bank.shared_kernel.error.ApiError;
import com.devsu.xp.bank.shared_kernel.error.BusinessException;
import com.devsu.xp.bank.shared_kernel.error.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiError> handleNotFound(NotFoundException ex, HttpServletRequest req) {
    ApiError body = ApiError.builder()
      .code("NOT_FOUND")
      .message(ex.getMessage())
      .timestamp(OffsetDateTime.now())
      .path(req.getRequestURI())
      .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiError> handleBusiness(BusinessException ex, HttpServletRequest req) {
    ApiError body = ApiError.builder()
      .code(ex.getCode())
      .message(ex.getMessage())
      .timestamp(OffsetDateTime.now())
      .path(req.getRequestURI())
      .build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {
    String msg = ex.getBindingResult().getAllErrors().stream()
      .findFirst().map(e -> e.getDefaultMessage()).orElse("Validation error");
    ApiError body = ApiError.builder()
      .code("VALIDATION_ERROR")
      .message(msg)
      .timestamp(OffsetDateTime.now())
      .path(req.getRequestURI())
      .build();
    return ResponseEntity.badRequest().body(body);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleOthers(Exception ex, HttpServletRequest req) {
    ApiError body = ApiError.builder()
      .code("UNEXPECTED_ERROR")
      .message(ex.getMessage())
      .timestamp(OffsetDateTime.now())
      .path(req.getRequestURI())
      .build();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
  }
}
