package com.honeysense.magpie.spring.exception;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.utils.MagpieValidator;
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

        if (e instanceof HttpMessageNotWritableException) {
            return new MagpieException("MessageNotWritable", e.getMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            return new MagpieException("MessageNotReadable", e.getMessage());
        } else if (e instanceof HttpMediaTypeNotAcceptableException) {
            return new MagpieException("MediaTypeNotAcceptable", e.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return new MagpieException("RequestMethodNotSupported", e.getMessage());
        } else if (e instanceof HttpMediaTypeNotSupportedException) {
            return new MagpieException("MediaTypeNotSupported", e.getMessage());
        } else if (e instanceof MethodArgumentTypeMismatchException) {
            Map<String, String> map = new HashMap<>();

            map.put("name", ((MethodArgumentTypeMismatchException) e).getName());
            map.put("message", e.getMessage());

            return new MagpieException("MethodArgumentTypeMismatch", map);
        } else if (e instanceof MissingServletRequestParameterException) {
            Map<String, String> map = new HashMap<>();

            map.put("parameterName", ((MissingServletRequestParameterException) e).getParameterName());
            map.put("parameterType", ((MissingServletRequestParameterException) e).getParameterType());

            return new MagpieException("MissingServletRequestParameter", map);
        } else if (e instanceof MethodArgumentNotValidException) {
            List<MagpieValidator.BindError> errorList = new ArrayList<>();

            for (ObjectError objectError : ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors()) {
                DefaultMessageSourceResolvable defaultMessageSourceResolvable = (DefaultMessageSourceResolvable) Objects.requireNonNull(objectError.getArguments())[0];
                errorList.add(new MagpieValidator.BindError(defaultMessageSourceResolvable.getCode(),
                        objectError.getDefaultMessage()));
            }

            return new MagpieException("MethodArgumentNotValid", errorList);
        } else if (e instanceof NoHandlerFoundException) {
            return new MagpieException("NoHandlerFound", e.getMessage());
        } else if (e instanceof IllegalArgumentException) {
            return new MagpieException("IllegalArgument", e.getMessage());
        } else if (e instanceof IllegalStateException) {
            return new MagpieException("IllegalState", e.getMessage());
        } else if (e instanceof BindException) {
            return new MagpieException("Bind", ((BindException) e).getAllErrors().get(0).getDefaultMessage());
        } else if (e instanceof TransactionSystemException) {
            return new MagpieException("TransactionSystem", e.getCause().getCause().getMessage());
        } else if (e instanceof DataIntegrityViolationException) {
            return new MagpieException("DataIntegrityViolation", e.getCause().getCause().getMessage());
        } else if (e instanceof UnexpectedTypeException) {
            return new MagpieException("UnexpectedTypeException", e.getMessage());
        } else if (e instanceof ServletRequestBindingException) {
            return new MagpieException("ServletRequestBindingException", e.getMessage());
        } else if (e instanceof MagpieException) {
            return (MagpieException) e;
        } else if (e instanceof UnrecognizedPropertyException) {
            return new MagpieException("UnrecognizedPropertyException", e.getMessage());
        } else {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return new MagpieException("Exception", sw.toString());
        }
    }
}
