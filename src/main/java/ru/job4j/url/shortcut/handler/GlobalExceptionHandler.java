package ru.job4j.url.shortcut.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public void handleException(Exception e, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(
                new HashMap<>() {
                    {
                        put("message", "Some of fields empty");
                        put("details", e.getMessage());
                    }
                }
        ));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<?> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(
                e.getFieldErrors().stream()
                        .map(f -> Map.of(
                                f.getField(),
                                String.format("%s. Actual value: %s. Object: %s. Rejected value %s",
                                        f.getDefaultMessage(),
                                        f.getRejectedValue(), f.getObjectName(),
                                        f.getRejectedValue())
                        ))
                        .collect(Collectors.toList())
        );
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<?> handle(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<?> handle(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(
                e.getConstraintViolations().stream().map(
                        v -> Map.of(
                                v.getPropertyPath(),
                                String.format("Invalid value for %s. Error: %s. Actual value: '%s'",
                                        v.getPropertyPath(),
                                        v.getMessage(),
                                        v.getInvalidValue())
                        )
                ).collect(Collectors.toList()));
    }
}
