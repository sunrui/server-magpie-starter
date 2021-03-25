package com.honeysense.media.controller.p.address.res;

import com.honeysense.magpie.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "查询指定的地理位置 ID - 请求")
@Getter
@Setter
@Builder
public class GetAddressAreaRes extends MagpieObject {
    private Boolean areaIdNotExists;
    private Long areaId;
}
