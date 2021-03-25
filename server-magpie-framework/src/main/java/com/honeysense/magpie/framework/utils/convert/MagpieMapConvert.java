package com.honeysense.magpie.framework.utils.convert;

import com.honeysense.magpie.framework.object.MagpieException;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class MagpieMapConvert {
    public static java.util.Map<String, String> toMap(Object obj) {
        java.util.Map<String, String> map = new HashMap<>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();

                if (key == null || key.equals("class")) {
                    continue;
                }

                Method getter = property.getReadMethod();
                if (getter != null && getter.invoke(obj) != null) {
                    map.put(key, (String) getter.invoke(obj));
                }
            }
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();

            throw new MagpieException(MagpieException.Type.PARSE_FAILED, e.getMessage());
        }

        return map;
    }

    public static <T> T toObject(java.util.Map<String, String> map, Class<T> beanClass) {
        try {
            T obj = beanClass.getDeclaredConstructor().newInstance();

            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
                Method setter = property.getWriteMethod();
                if (setter != null) {
                    setter.invoke(obj, map.get(property.getName()));
                }
            }

            return obj;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | IntrospectionException e) {
            e.printStackTrace();

            throw new MagpieException(MagpieException.Type.PARSE_FAILED, e.getMessage());
        }
    }
}
