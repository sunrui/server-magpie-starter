package com.honeysense.magpie.medium.controller.c.driver.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "更新司机信息 - 返回")
@Getter
@Setter
@Builder
public class PutMediumDriverInfoRes extends MagpieObject {
}
