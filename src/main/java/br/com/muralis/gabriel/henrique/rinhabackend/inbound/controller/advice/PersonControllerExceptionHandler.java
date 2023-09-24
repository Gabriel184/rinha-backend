package br.com.muralis.gabriel.henrique.rinhabackend.inbound.controller.advice;

import br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions.AbstractDomainException;
import br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestControllerAdvice
@Slf4j
public class PersonControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleDomainException(AbstractDomainException exception) {
        log.info("Chamou a restControllerAdvice handleDomainException");
        return new ResponseEntity<>(new ErrorMessage(exception.getStatus(), exception.getMessage()), HttpStatusCode.valueOf(exception.getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.info("Chamou a restControllerAdvice handleHttpMessageNotReadableException");
        if(exception.getMessage().contains("nome") ||
                exception.getMessage().contains("apelido") ||
                exception.getMessage().contains("nascimento") ||
                exception.getMessage().contains("stack")) {
            log.error(exception.getMessage());
            return new ResponseEntity<>(new ErrorMessage(400, exception.getMessage()), HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(new ErrorMessage(400, exception.getMessage()), HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handlePSQLException(PSQLException exception) {
        log.error("Chamou a restControllerAdvice handlePSQLException", exception);
        return new ResponseEntity<>(new ErrorMessage(422, exception.getMessage()), HttpStatusCode.valueOf(422));
    }
}
