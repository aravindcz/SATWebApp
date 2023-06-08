package com.aravindcz.SATWebApp.exception;

import com.aravindcz.SATWebApp.dto.MenuDTO;
import com.aravindcz.SATWebApp.dto.ResponseDTO;
import jakarta.validation.ConstraintDefinitionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {ResultNotFoundException.class})
    public ResponseEntity<ResponseDTO> handleResultNotFoundException(){
        ResponseEntity<ResponseDTO> responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("failure",404,"Result not found",null), HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(value = {NameAlreadyExistsException.class})
    public ResponseEntity<ResponseDTO> handleNameAlreadyExistsException(){
        ResponseEntity<ResponseDTO> responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("failure",400,"Result has been already registered with this name",null), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(value = {ConstraintDefinitionException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDTO> handleConstraintDefinitionException(){
        ResponseEntity<ResponseDTO> responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("failure",400,"Make sure request is according to api specifications",null), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseDTO> handleException(Exception ex){
        log.error("Some exceptin",ex);
        ResponseEntity<ResponseDTO> responseEntity  = new ResponseEntity<ResponseDTO>(new ResponseDTO("failure",500,"Some kind of error occured",null), HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }


}
