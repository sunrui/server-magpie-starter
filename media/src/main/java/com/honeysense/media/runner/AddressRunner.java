package com.honeysense.media.runner;

import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.utils.convert.MagpieJsonConvert;
import com.honeysense.media.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AddressRunner implements ApplicationRunner {
    @Autowired
    private AddressService addressService;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        final String cityPath = "area/area.json";
        Resource resource = new ClassPathResource(cityPath);

        if (!resource.isReadable()) {
            throw new MagpieException(MagpieException.Type.NOT_FUND, cityPath);
        }

        addressService.parseData(new String(resource.getInputStream().readAllBytes()));
    }
}
