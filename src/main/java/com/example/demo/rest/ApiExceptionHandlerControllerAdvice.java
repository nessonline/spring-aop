package com.example.demo.rest;

import com.example.demo.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice(basePackages = "com.example.demo.rest")
public class ApiExceptionHandlerControllerAdvice {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<String> throwableExceptionHandler(Throwable throwable) {
        return buildResponse(throwable, "ApdiExceptionHandler", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<String> buildResponse(Throwable exception, String handler, HttpStatus httpStatus) {
        if (handler == null) handler = "ApiExceptionHandler";
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        exception.printStackTrace(pw);
        pw.flush();

        log.info(String.format("%s обрабатывает ошибку: %s", handler, exception.getMessage()));

        /*log.info(handler+" обрабатывает ошибку: {}; \nStacktrace: {}",
                exception.getMessage(), sw.toString());*/

        //обрежем очень длинный стектрейс
        if (sw.getBuffer().length() > 65536) {
            sw.flush();
            sw.getBuffer().delete(65524, sw.getBuffer().length());
            sw.append("<truncated>"); // 11 символов
            sw.flush();
        }

        Map<String, Object> er = new HashMap<>();
        er.put("code", httpStatus != null ? httpStatus.value() : 500);
        er.put("message", exception.getMessage());
        er.put("dateTime", LocalDateTime.now());
        //er.put("description", sw.toString());

        return buildResponse(exception, httpStatus, er);
    }

    protected ResponseEntity<String> buildResponse(Throwable t, HttpStatus httpStatus, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity(ObjectUtils.toJson(body), headers, httpStatus);
    }
}
