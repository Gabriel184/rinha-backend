package br.com.muralis.gabriel.henrique.rinhabackend.inbound.controller.advice;

import br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions.AbstractDomainException;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PersonControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<?> handleDomainException(AbstractDomainException exception) {
        //log.info("Chamou a restControllerAdvice handleDomainException");
        return new ResponseEntity<>(HttpStatusCode.valueOf(exception.getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        //log.info("Chamou a restControllerAdvice handleHttpMessageNotReadableException");
        if(exception.getMessage().contains("nome") ||
                exception.getMessage().contains("apelido") ||
                exception.getMessage().contains("nascimento") ||
                exception.getMessage().contains("stack")) {
            //log.error(exception.getMessage());
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler
    public ResponseEntity<?> handlePSQLException(PSQLException exception) {
        //log.error("Chamou a restControllerAdvice handlePSQLException", exception);
        return new ResponseEntity<>(HttpStatusCode.valueOf(422));
    }
}
