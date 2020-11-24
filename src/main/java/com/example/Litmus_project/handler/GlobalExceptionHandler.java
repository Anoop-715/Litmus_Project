package com.example.Litmus_project.handler;

import com.example.Litmus_project.customException.DuplicateEmailException;
import com.example.Litmus_project.customException.ProfileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.Litmus_project.util.ErrorMessage.SOMETHING_WENT_WRONG;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException e){
        return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getAllErrors().stream().map(m -> m.getDefaultMessage()).collect(Collectors.toList());
        String stringOfErrors = String.join(",", errors);
        return new ResponseEntity(stringOfErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ProfileNotFoundException.class})
    public ResponseEntity<String> profileNotFoundException(ProfileNotFoundException e){
        return  new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
