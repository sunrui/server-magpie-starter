package com.honeysense.magpie.framework.utils.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.honeysense.magpie.framework.entity.MagpieException;
import com.honeysense.magpie.framework.utils.format.MagpieJsonFormat;

import java.io.IOException;

public class MagpieJsonConvert<T> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T toObject(String json, JavaType valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            e.printStackTrace();

            throw new MagpieException(MagpieException.Type.PARSE_FAILED, e.getMessage());
        }
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            e.printStackTrace();

            throw new MagpieException(MagpieException.Type.PARSE_FAILED, e.getMessage());
        }
    }

    public static <T> String toJson(T clazz, boolean format) {
        try {
            String json = objectMapper.writeValueAsString(clazz);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);

            if (format) {
                json = MagpieJsonFormat.format(json);
            }

            return json;
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            throw new MagpieException(MagpieException.Type.PARSE_FAILED, e.getMessage());
        }
    }
}
