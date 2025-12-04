package com.lufthansa.subscriptions.exception;

import com.lufthansa.subscriptions.dto.general.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidationAdvice {

 /*   @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorMessage = fieldErrors.get(0).getDefaultMessage();

        return ResponseEntity.badRequest().body(new ResponseError(errorMessage));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadable(HttpMessageNotReadableException ex) {
        log.error(ex.toString());
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error(ex.toString());
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.error(ex.toString());
        return ResponseEntity.badRequest().body(new ResponseError(ex.getMessage()));
    }
*/
    @ExceptionHandler({AccessDeniedException.class, AuthorizationDeniedException.class})
    public ResponseEntity<?> accessDeniedException(Exception ex) {
        log.error(ex.toString());
        String message = ex.getMessage() != null && !ex.getMessage().isEmpty() ? ex.getMessage() : "Access Denied!!";
        return new ResponseEntity<>(new ResponseError(message), FORBIDDEN);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<?> notFoundException(NotFoundException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<?> badRequestException(BadRequestException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<?> conflictException(ConflictException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> internalServerError(Exception ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError("Internal Server Error!"), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    public ResponseEntity<?> unauthorizedException(UnauthorizedException ex) {
        log.error(ex.toString());
        return new ResponseEntity<>(new ResponseError(ex.getMessage()), UNAUTHORIZED);
    }
}