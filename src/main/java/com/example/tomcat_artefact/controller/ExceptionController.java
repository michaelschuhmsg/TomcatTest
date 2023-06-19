package com.example.tomcat_artefact.controller;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        try {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        final ModelAndView modelAndView = new ModelAndView();
        final ModelAndView modelAndView1 = new ModelAndView();

        View view = (View) modelAndView1;
        modelAndView.setView(view);
        return modelAndView;

    }
}
