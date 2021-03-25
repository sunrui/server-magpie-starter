package com.honeysense.magpie.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

@Slf4j
public class MagpieResource {
    /**
     * 获取资源的 JSON 数据
     *
     * @param resourceFile 资源文件
     * @return JSON 数据
     */
    public String getResourceValue(String resourceFile, String key) {
        ClassPathResource classPathResource = new ClassPathResource(resourceFile);
        BufferedReader bufferedReader = null;

        try {
            File file = classPathResource.getFile();
            bufferedReader = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceFile);
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            }
        }

        if (bufferedReader == null) {
            throw new RuntimeException(resourceFile + " cannot be found.");
        }

        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith(key + "=")) {
                    String value = line.substring(key.length() + 1);
                    log.info(resourceFile + " -> key: " + key + ", value: " + value);
                    return value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(resourceFile + "->" + e.getMessage());
        }

        throw new RuntimeException(resourceFile + " -> key: " + key + " cannot be found.");
    }
}
