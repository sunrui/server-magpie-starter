package com.honeysense.magpie.medium.service;

import com.honeysense.magpie.medium.entity.MediumAddress;

import java.io.IOException;
import java.util.List;

public interface MediumAddressService {
    void parseData(String areaJson) throws IOException;

    MediumAddress.AreaObject findById(long id);

    Long findIdByAreaObject(MediumAddress.AreaObject areaObject);

    List<MediumAddress.Country> findAll();
}
