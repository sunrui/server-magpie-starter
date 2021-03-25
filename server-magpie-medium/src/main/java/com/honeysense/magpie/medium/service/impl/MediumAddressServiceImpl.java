package com.honeysense.magpie.medium.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeysense.magpie.framework.object.MagpieException;
import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.medium.entity.MediumAddress;
import com.honeysense.magpie.medium.service.MediumAddressService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MediumAddressServiceImpl implements MediumAddressService {
    private List<MediumAddress.Country> countries;
    private Map<Long, MediumAddress.AreaObject> areaObjectMap;

    @Override
    public void parseData(String areaJson) throws IOException {
        countries = new ObjectMapper().readValue(areaJson, new TypeReference<>() {
        });

        areaObjectMap = new HashMap<>();

        for (MediumAddress.Country country : countries) {
            areaObjectMap.put(country.getId(), MediumAddress.AreaObject.builder()
                    .id(country.getId())
                    .country(country.getName())
                    .build());

            for (MediumAddress.Province province : country.getProvinces()) {
                areaObjectMap.put(province.getId(), MediumAddress.AreaObject.builder()
                        .id(province.getId())
                        .country(country.getName())
                        .province(province.getName())
                        .build());

                if (province.getCities() == null) {
                    continue;
                }

                for (MediumAddress.City city : province.getCities()) {
                    areaObjectMap.put(city.getId(), MediumAddress.AreaObject.builder()
                            .id(city.getId())
                            .country(country.getName())
                            .province(province.getName())
                            .city(city.getName())
                            .build());

                    if (city.getAreas() == null) {
                        continue;
                    }

                    for (MediumAddress.Area area : city.getAreas()) {
                        areaObjectMap.put(area.getId(), MediumAddress.AreaObject.builder()
                                .id(area.getId())
                                .country(country.getName())
                                .province(province.getName())
                                .city(city.getName())
                                .area(area.getName())
                                .build());
                    }
                }
            }
        }
    }

    @Override
    public MediumAddress.AreaObject findById(long id) {
        return areaObjectMap.get(id);
    }

    @Override
    public Long findIdByAreaObject(MediumAddress.AreaObject areaObject) {
        if (areaObject == null) {
            throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, "areaObject");
        }

        if (MagpieValidator.string(areaObject.getArea())) {
            if (!MagpieValidator.string(areaObject.getCity())) {
                throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, areaObject);
            }

            if (!MagpieValidator.string(areaObject.getProvince())) {
                throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, areaObject);
            }

            if (!MagpieValidator.string(areaObject.getCountry())) {
                throw new MagpieException(MagpieException.Type.INVALID_PARAMETER, areaObject);
            }
        }

        for (MediumAddress.Country country : countries) {

            for (MediumAddress.Province province : country.getProvinces()) {

                if (province.getCities() == null) {
                    continue;
                }

                for (MediumAddress.City city : province.getCities()) {

                    if (city.getAreas() == null) {
                        continue;
                    }

                    for (MediumAddress.Area area : city.getAreas()) {
                    }
                }
            }
        }

        return null;
    }

    @Override
    public List<MediumAddress.Country> findAll() {
        if (countries == null) {
            throw new MagpieException(MagpieException.Type.BAD_LOGIC);
        }

        return countries;
    }
}
