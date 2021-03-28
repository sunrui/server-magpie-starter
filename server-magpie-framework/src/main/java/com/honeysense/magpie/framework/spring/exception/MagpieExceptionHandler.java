package com.honeysense.magpie.framework.spring.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.UnexpectedTypeException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@ControllerAdvice
@RestControllerAdvice
class MagpieExceptionHandler {
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public MagpieException handleNoHandlerFoundException(NoHandlerFoundException e) {
        return new MagpieException("NoHandlerFound", e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public MagpieException handleException(Exception e) {
        e.printStackTrace();

        Object detail;

        if (e instanceof MethodArgumentTypeMismatchException) {
            Map<String, String> map = new HashMap<>();

            map.put("name", ((MethodArgumentTypeMismatchException) e).getName());
            map.put("message", e.getMessage());

            detail = map;
        } else if (e instanceof MissingServletRequestParameterException) {
            Map<String, String> map = new HashMap<>();

            map.put("parameterName", ((MissingServletRequestParameterException) e).getParameterName());
            map.put("parameterType", ((MissingServletRequestParameterException) e).getParameterType());

            detail = map;
        } else if (e instanceof MethodArgumentNotValidException) {
            List<MagpieValidator.BindError> errorList = new ArrayList<>();

            for (ObjectError objectError : ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors()) {
                DefaultMessageSourceResolvable defaultMessageSourceResolvable = (DefaultMessageSourceResolvable) Objects.requireNonNull(objectError.getArguments())[0];
                errorList.add(new MagpieValidator.BindError(defaultMessageSourceResolvable.getCode(),
                        objectError.getDefaultMessage()));
            }

            detail = errorList;
        } else if (e instanceof BindException) {
            detail = ((BindException) e).getAllErrors().get(0).getDefaultMessage();
        } else if (e instanceof TransactionSystemException) {
            detail = e.getCause().getCause().getMessage();
        } else if (e instanceof DataIntegrityViolationException) {
            detail = e.getCause().getCause().getMessage();
        } else if (e instanceof MagpieException) {
            return (MagpieException) e;
        } else {
            detail = e.getMessage();
        }

        return new MagpieException(e.getClass().getSimpleName(), detail);
    }
}
