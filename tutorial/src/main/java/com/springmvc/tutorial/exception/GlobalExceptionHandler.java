package com.springmvc.tutorial.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TemplateInputException.class)
    public ModelAndView handleTemplateException(TemplateInputException ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("message", "Có lỗi xảy ra khi render template.");
        return model;
    }
}
