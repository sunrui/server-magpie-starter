package com.honeysense.magpie.medium.controller.p.address.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "查询指定的地理位置 ID - 请求")
@Getter
@Setter
@Builder
public class GetMediumAddressAreaRes extends MagpieObject {
    private Boolean areaIdNotExists;
    private Long areaId;
}
