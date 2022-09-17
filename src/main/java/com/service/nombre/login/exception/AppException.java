package com.service.nombre.login.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppException {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidationException(MethodArgumentNotValidException e){
        Map<String, String> errores = new HashMap<>();
        errores.put("Fecha ", Calendar.getInstance().getTime().toString());
        e.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> handlerMethodException(HttpRequestMethodNotSupportedException e){
        Map<String, String> errores = new HashMap<>();
        errores.put("Fecha ", Calendar.getInstance().getTime().toString());
        errores.put("Mensaje","Este metodo no esta permitido para esta peticion");
        return ResponseEntity.badRequest().body(errores);
    }
}
