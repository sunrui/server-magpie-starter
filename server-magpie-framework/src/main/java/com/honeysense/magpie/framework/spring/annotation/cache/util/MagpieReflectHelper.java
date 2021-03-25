package com.honeysense.magpie.framework.spring.annotation.cache.util;

import com.honeysense.magpie.framework.entity.MagpieException;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MagpieReflectHelper {
    private static String getMethod(ProceedingJoinPoint proceedingJoinPoint) {
        String key = getMethodName(proceedingJoinPoint);
        key += "(";

        Map<String, Object> parameters = getParameters(proceedingJoinPoint);
        StringBuilder keyBuilder = new StringBuilder(key);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            keyBuilder.append(entry.getValue());
            keyBuilder.append(", ");
        }

        key = keyBuilder.substring(0, keyBuilder.length() - 2);
        key += ")";

        return key;
    }

    private static String getMethodName(ProceedingJoinPoint proceedingJoinPoint) {
        Signature sig = proceedingJoinPoint.getSignature();
        if (!(sig instanceof MethodSignature)) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "not a method");
        }

        MethodSignature methodSignature = (MethodSignature) sig;
        Object target = proceedingJoinPoint.getTarget();

        try {
            Method method = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
            return method.getName();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static Object getAnnotation(JoinPoint joinPoint, Class clazz) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getAnnotation(clazz);
    }

    private static Map<String, Object> getParameters(JoinPoint joinPoint) {
        Map<String, Object> parameters = new HashMap<>();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ParameterNameDiscoverer pnd = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = pnd.getParameterNames(signature.getMethod());
        if (paramNames != null) {
            for (int i = 0; i < paramNames.length; i++) {
                parameters.put(paramNames[i], joinPoint.getArgs()[i]);
            }
        }

        return parameters;
    }

    public static String parseText(String text, ProceedingJoinPoint proceedingJoinPoint) {
        if (!MagpieValidator.string(text)) {
            return text;
        }

        final Map<String, Object> parameters = getParameters(proceedingJoinPoint);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            text = text.replace(String.format("${%s}", entry.getKey()), entry.getValue() + "");
        }

        if (text.contains("${")) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, text);
        }

        text = text.replace("$class", proceedingJoinPoint.getTarget().getClass().getName());
        text = text.replace("$method", getMethod(proceedingJoinPoint));

        return text;
    }
}
