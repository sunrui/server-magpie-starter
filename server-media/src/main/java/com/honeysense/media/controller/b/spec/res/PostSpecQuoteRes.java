package com.honeysense.media.controller.b.spec.res;

import com.honeysense.magpie.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "创建广告规格投放期数 - 返回")
@Getter
@Setter
@Builder
public class PostSpecQuoteRes extends MagpieObject {
}
