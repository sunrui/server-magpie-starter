package com.honeysense.media.controller.p.address;

import com.honeysense.magpie.utils.MagpieValidator;
import com.honeysense.media.controller.p.address.res.GetAddressAreaRes;
import com.honeysense.media.entity.Address;
import com.honeysense.media.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "通用 - 地址")
@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "获取所有的省市", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping()
    @ResponseBody
    List<Address.Country> getAll() {
        return addressService.findAll();
    }

    @ApiOperation(value = "获取指定的地域", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("id/{id}")
    @ResponseBody
    Address.AreaObject getById(@ApiParam(value = "传入参数", required = true)
                               @PathVariable("id") Long id) {
        return addressService.findById(id);
    }

    @ApiOperation(value = "更新司机的基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("query")
    @ResponseBody
    public GetAddressAreaRes getQuery(@ApiParam(value = "国家")
                                      @RequestParam(name = "country", required = false) String country,
                                      @ApiParam(value = "省")
                                      @RequestParam(name = "province", required = false) String province,
                                      @ApiParam(value = "市")
                                      @RequestParam(name = "city", required = false) String city,
                                      @ApiParam(value = "area")
                                      @RequestParam(name = "area", required = false) String area) {
        if (!MagpieValidator.string(country)) {
            country = "中国";
        }

        Address.AreaObject areaObject = Address.AreaObject.builder()
                .country(country)
                .province(province)
                .city(city)
                .area(area)
                .build();

        Long areaId = addressService.findIdByAreaObject(areaObject);
        if (areaId != null) {
            return GetAddressAreaRes.builder().areaId(areaId).build();
        }

        return GetAddressAreaRes.builder().areaIdNotExists(true).build();
    }
}
