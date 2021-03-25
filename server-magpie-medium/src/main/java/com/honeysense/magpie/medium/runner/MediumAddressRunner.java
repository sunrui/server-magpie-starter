package com.honeysense.magpie.medium.runner;

import com.honeysense.magpie.framework.entity.MagpieException;
import com.honeysense.magpie.medium.service.MediumAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MediumAddressRunner implements ApplicationRunner {
    @Autowired
    private MediumAddressService mediumAddressService;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        final String cityPath = "area/area.json";
        Resource resource = new ClassPathResource(cityPath);

        if (!resource.isReadable()) {
            throw new MagpieException(MagpieException.Type.NOT_FUND, cityPath);
        }

        mediumAddressService.parseData(new String(resource.getInputStream().readAllBytes()));
    }
}
