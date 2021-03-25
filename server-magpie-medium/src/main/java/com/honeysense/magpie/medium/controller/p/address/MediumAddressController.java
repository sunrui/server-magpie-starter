package com.honeysense.magpie.medium.controller.p.address;

import com.honeysense.magpie.framework.utils.MagpieValidator;
import com.honeysense.magpie.medium.controller.p.address.res.GetMediumAddressAreaRes;
import com.honeysense.magpie.medium.entity.MediumAddress;
import com.honeysense.magpie.medium.service.MediumAddressService;
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
public class MediumAddressController {
    @Autowired
    private MediumAddressService mediumAddressService;

    @ApiOperation(value = "获取所有的省市", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping()
    @ResponseBody
    List<MediumAddress.Country> getAll() {
        return mediumAddressService.findAll();
    }

    @ApiOperation(value = "获取指定的地域", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("id/{id}")
    @ResponseBody
    MediumAddress.AreaObject getById(@ApiParam(value = "传入参数", required = true)
                               @PathVariable("id") Long id) {
        return mediumAddressService.findById(id);
    }

    @ApiOperation(value = "更新司机的基本信息", produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("query")
    @ResponseBody
    public GetMediumAddressAreaRes getQuery(@ApiParam(value = "国家")
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

        MediumAddress.AreaObject areaObject = MediumAddress.AreaObject.builder()
                .country(country)
                .province(province)
                .city(city)
                .area(area)
                .build();

        Long areaId = mediumAddressService.findIdByAreaObject(areaObject);
        if (areaId != null) {
            return GetMediumAddressAreaRes.builder().areaId(areaId).build();
        }

        return GetMediumAddressAreaRes.builder().areaIdNotExists(true).build();
    }
}
