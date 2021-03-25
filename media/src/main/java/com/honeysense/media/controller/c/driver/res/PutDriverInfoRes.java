package com.honeysense.media.controller.c.driver.res;

import com.honeysense.magpie.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "更新司机信息 - 返回")
@Getter
@Setter
@Builder
public class PutDriverInfoRes extends MagpieObject {
}
