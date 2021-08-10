package com.training.ums.exception.global;
import java.util.NoSuchElementException;
import com.training.ums.exception.custom.exception.EmptyInputException;
import com.training.ums.exception.custom.exception.EmptyListException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String>handleEmptyInputException(EmptyInputException emptyInputException){
        return new ResponseEntity<String>("Some Of Your Input Fields Are Empty.Rechech It", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException noSuchElementException){
        return new ResponseEntity<String>("No Value is Present In DB,Please Recheck", HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
        HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("Method Not Allowed.Please Change Your HTTP Method Type", HttpStatus.METHOD_NOT_ALLOWED);
    }
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
            return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<String>handleEmptyListException(EmptyListException emptyListException){
        return new ResponseEntity<String>("Your Search Data Is Empty",HttpStatus.BAD_REQUEST);
    }
   

    
}
