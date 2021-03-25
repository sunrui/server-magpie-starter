package com.honeysense.media.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeysense.magpie.entity.MagpieException;
import com.honeysense.magpie.utils.MagpieValidator;
import com.honeysense.media.entity.Address;
import com.honeysense.media.service.AddressService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {
    private List<Address.Country> countries;
    private Map<Long, Address.AreaObject> areaObjectMap;

    @Override
    public void parseData(String areaJson) throws IOException {
        countries = new ObjectMapper().readValue(areaJson, new TypeReference<>() {
        });

        areaObjectMap = new HashMap<>();

        for (Address.Country country : countries) {
            areaObjectMap.put(country.getId(), Address.AreaObject.builder()
                    .id(country.getId())
                    .country(country.getName())
                    .build());

            for (Address.Province province : country.getProvinces()) {
                areaObjectMap.put(province.getId(), Address.AreaObject.builder()
                        .id(province.getId())
                        .country(country.getName())
                        .province(province.getName())
                        .build());

                if (province.getCities() == null) {
                    continue;
                }

                for (Address.City city : province.getCities()) {
                    areaObjectMap.put(city.getId(), Address.AreaObject.builder()
                            .id(city.getId())
                            .country(country.getName())
                            .province(province.getName())
                            .city(city.getName())
                            .build());

                    if (city.getAreas() == null) {
                        continue;
                    }

                    for (Address.Area area : city.getAreas()) {
                        areaObjectMap.put(area.getId(), Address.AreaObject.builder()
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
    public Address.AreaObject findById(long id) {
        return areaObjectMap.get(id);
    }

    @Override
    public Long findIdByAreaObject(Address.AreaObject areaObject) {
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

        for (Address.Country country : countries) {

            for (Address.Province province : country.getProvinces()) {

                if (province.getCities() == null) {
                    continue;
                }

                for (Address.City city : province.getCities()) {

                    if (city.getAreas() == null) {
                        continue;
                    }

                    for (Address.Area area : city.getAreas()) {
                    }
                }
            }
        }

        return null;
    }

    @Override
    public List<Address.Country> findAll() {
        if (countries == null) {
            throw new MagpieException(MagpieException.Type.BAD_LOGIC);
        }

        return countries;
    }
}
