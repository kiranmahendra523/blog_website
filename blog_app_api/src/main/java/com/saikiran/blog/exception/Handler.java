package com.saikiran.blog.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;
import com.saikiran.blog.payloads.ApiResponse;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex
               ) {
		String message = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message,false); 
    	return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handelMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String feildName = ((FieldError)error).getField();
			String message = error.getDefaultMessage();
			resp.put(feildName, message);
		});
    	return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }
}
