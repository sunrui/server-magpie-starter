package com.honeysense.media.controller.b.spec.req;

import com.honeysense.media.entity.SpecBasic;
import com.honeysense.media.entity.SpecQuote;
import com.honeysense.magpie.entity.MagpieObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "创建广告规格投放期数 - 请求")
@Getter
@Setter
public class PostSpecQuoteReq extends MagpieObject {
    @ApiModelProperty(value="渠道 ID", required = true)
    @NotNull
    private Long channelId;
    @ApiModelProperty(value="投放地域", required = true)
    @NotBlank
    private String area;
    @ApiModelProperty(value="报价活动名称", required = true)
    @NotBlank
    private String name;
    @ApiModelProperty(value="包含的报价类型", required = true)
    @OneToMany
    private List<SpecBasic> specBasics;
    @ApiModelProperty(value="价格（以分为单位)", required = true)
    @NotNull
    @Min(0)
    private Integer price;
    @ApiModelProperty(value="投放天数", required = true)
    @NotNull
    @Min(0)
    private Integer day;
    @ApiModelProperty(value="备注", required = true)
    private String remark;
    @ApiModelProperty(value="引用次数", required = true)
    @NotNull
    @Min(0)
    private Integer referCount;
}
