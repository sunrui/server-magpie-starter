package com.honeysense.magpie.utils.convert;

import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

public class MagpieSerializeConvert {
    public static byte[] serialize(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out;

        try {
            out = new ObjectOutputStream(os);
            out.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException(e.getMessage());
        }

        return os.toByteArray();
    }

    public static Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        ObjectInputStream in;

        try {
            in = new ObjectInputStream(is);
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new SerializationException(e.getMessage());
        }
    }
}
