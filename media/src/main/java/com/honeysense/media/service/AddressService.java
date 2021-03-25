package com.honeysense.media.service;

import com.honeysense.media.entity.Address;

import java.io.IOException;
import java.util.List;

public interface AddressService {
    void parseData(String areaJson) throws IOException;

    Address.AreaObject findById(long id);

    Long findIdByAreaObject(Address.AreaObject areaObject);

    List<Address.Country> findAll();
}
