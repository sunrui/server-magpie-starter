package com.honeysense.magpie.framework.utils;

import com.honeysense.magpie.framework.entity.MagpieException;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class MagpieValidator {
    public static <T> void object(T clazz) {
        if (clazz == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER);
        }

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(clazz);
        if (violations.size() > 0) {
            List<BindError> errorList = new ArrayList<>();
            for (ConstraintViolation<T> violation : violations) {
                errorList.add(new MagpieValidator.BindError(violation.getPropertyPath().toString(), violation.getMessage()));
            }

            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, errorList);
        }
    }

    public static boolean string(String str) {
        return StringUtils.hasText(str);
    }

    public static boolean uuid32(String id) {
        return StringUtils.hasText(id) && id.length() == 32;
    }

    public static boolean longId(Long id) {
        return id != null && id > 1000000000000000000L;
    }

    public static boolean enId(String enId) {
        if (!StringUtils.hasText(enId)) {
            return false;
        }

        final Pattern pattern = Pattern.compile("[a-zA-Z0-9_]{1,15}$");
        return pattern.matcher(enId).matches();
    }

    public static boolean phone(String phone) {
        if (!StringUtils.hasText(phone)) {
            return false;
        }

        final Pattern pattern = Pattern.compile("^[1][34578][0-9]{9}$");
        return pattern.matcher(phone).matches();
    }

    public static boolean smsCode(String code) {
        if (!StringUtils.hasText(code)) {
            return false;
        }

        final Pattern pattern = Pattern.compile("^\\d{6}$");
        return pattern.matcher(code).matches();
    }

    public static boolean password(String password) {
        if (!StringUtils.hasText(password)) {
            return false;
        }

        return password.length() >= 6 && password.length() <= 32;
    }

    public static boolean ip(String ip) {
        if (!StringUtils.hasText(ip)) {
            return false;
        }

        Pattern ipv4 = Pattern.compile("^(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}$");
        Pattern ipv6 = Pattern.compile("^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}(:[0-9A-Fa-f]{1,4}){1,2})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){1,3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){1,4})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){1,5})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){1,6})|(:(:[0-9A-Fa-f]{1,4}){1,7})|(([0-9A-Fa-f]{1,4}:){6}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|([0-9A-Fa-f]{1,4}:(:[0-9A-Fa-f]{1,4}){0,4}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})|(:(:[0-9A-Fa-f]{1,4}){0,5}:(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}))$");

        return ipv4.matcher(ip).matches() || ipv6.matcher(ip).matches();
    }

    @Data
    public static class BindError {
        private final String field;
        private final String message;
    }
}
