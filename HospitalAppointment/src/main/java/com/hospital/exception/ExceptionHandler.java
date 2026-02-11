package com.hospital.exception;

import com.hospital.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException
                                                                    (IdNotFoundException e)
    {
        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());
        response.setData("Fail, Id not found");
        return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoRecordAvailableException.class)
    public ResponseEntity<ResponseStructure<String>> handleNoRecordAvailableException
            (NoRecordAvailableException e)
    {
        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setMessage(e.getMessage());
        response.setData("Fail, No record Available");
        return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
    }
}
