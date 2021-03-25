package com.honeysense.magpie.spring.annotation.cache;

import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.spring.annotation.cache.util.MagpieReflectHelper;
import com.honeysense.magpie.spring.annotation.cache.util.MagpieRedisHelper;
import com.honeysense.magpie.utils.MagpieValidator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class MagpieAnnotationCacheEvictAspect {
    @Resource
    private MagpieRedisHelper magpieRedisHelper;

    @Pointcut("@annotation(com.honeysense.magpie.spring.annotation.cache.MagpieAnnotationCacheEvict)")
    private void pointcut() {
    }

    @Around("pointcut()")
    public Object save(ProceedingJoinPoint proceedingJoinPoint) {
        MagpieAnnotationCacheEvict annotation = (MagpieAnnotationCacheEvict) MagpieReflectHelper.getAnnotation(proceedingJoinPoint, MagpieAnnotationCacheEvict.class);
        Object result;

        String namespace = MagpieReflectHelper.parseText(annotation.namespace(), proceedingJoinPoint);
        String key = MagpieReflectHelper.parseText(annotation.key(), proceedingJoinPoint);

        if (!MagpieValidator.string(namespace)) {
            Map<String, String> map = new HashMap<>();
            map.put("namespace", namespace);

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, map);
        }

        if (MagpieValidator.string(key)) {
            magpieRedisHelper.delete(namespace, key);
        } else {
            magpieRedisHelper.delete(namespace);
        }

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

        return result;
    }
}
