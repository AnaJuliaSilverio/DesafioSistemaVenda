package com.desafiobd.sistemaVendas.expectionHandler;

import com.desafiobd.sistemaVendas.dtos.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handlingOfDuplicateValues(ConstraintViolationException constraintViolationException){
        ExceptionDTO exceptionResponse = new ExceptionDTO(new Date(),"Dados inserido incorretamente "+constraintViolationException.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handlingOfInvalidArgument(MethodArgumentNotValidException argumentNotValidException){
        ExceptionDTO exceptionResponse = new ExceptionDTO(new Date(),"Dados inserido incorretamente: "+argumentNotValidException.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handlingOf404(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity handlingOfIncorrectDates(DateTimeException exception){
        ExceptionDTO exceptionResponse = new ExceptionDTO(new Date(),exception.getMessage());
        return ResponseEntity.badRequest().body(exceptionResponse);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handlingOfInvalidQuantity(IllegalArgumentException illegalArgumentException){
        ExceptionDTO exceptionDTO = new ExceptionDTO(new Date(),illegalArgumentException.getMessage());
        return ResponseEntity.status(401).body(exceptionDTO);
    }
}