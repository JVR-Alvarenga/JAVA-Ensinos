package com.reserva.salas.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity exception404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception400(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest()
            .body(
                    ex.getFieldErrors().stream().map(DataExceptionValidation::new).toList()
            );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity exceptionInvalidBody() {
        return ResponseEntity.badRequest()
            .body(new DataExceptionMessage("Request body is missing or invalid"));
    }

    private record DataExceptionValidation(String campo, String mensagem) {
        public DataExceptionValidation(FieldError ex) {
            this(ex.getField(), ex.getDefaultMessage());
        }
    }

    private record DataExceptionMessage(String mensagem) { }
}