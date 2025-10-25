package com.devsu.xp.bank.ms_accounts.web.errors;

import com.devsu.xp.bank.shared_kernel.error.ApiError;
import com.devsu.xp.bank.shared_kernel.error.BusinessException;
import com.devsu.xp.bank.shared_kernel.error.InsufficientBalanceException;
import com.devsu.xp.bank.shared_kernel.error.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ApiError> notFound(NotFoundException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
      ApiError.builder()
        .code("NOT_FOUND")
        .message(ex.getMessage())
        .timestamp(OffsetDateTime.now())
        .path(req.getRequestURI())
        .build()
    );
  }

  @ExceptionHandler({ BusinessException.class, InsufficientBalanceException.class, IllegalStateException.class })
  public ResponseEntity<ApiError> business(RuntimeException ex, HttpServletRequest req) {
    String code = ex instanceof BusinessException ? ((BusinessException) ex).getCode() : "BUSINESS_ERROR";
    return ResponseEntity.status(HttpStatus.CONFLICT).body(
      ApiError.builder()
        .code(code)
        .message(ex.getMessage())
        .timestamp(OffsetDateTime.now())
        .path(req.getRequestURI())
        .build()
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> validation(MethodArgumentNotValidException ex, HttpServletRequest req) {
    String msg = ex.getBindingResult().getAllErrors().stream()
      .findFirst().map(e -> e.getDefaultMessage()).orElse("Validation error");
    return ResponseEntity.badRequest().body(
      ApiError.builder()
        .code("VALIDATION_ERROR")
        .message(msg)
        .timestamp(OffsetDateTime.now())
        .path(req.getRequestURI())
        .build()
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> unexpected(Exception ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
      ApiError.builder()
        .code("UNEXPECTED_ERROR")
        .message(ex.getMessage())
        .timestamp(OffsetDateTime.now())
        .path(req.getRequestURI())
        .build()
    );
  }
}
