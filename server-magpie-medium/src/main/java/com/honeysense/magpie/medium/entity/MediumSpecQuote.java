package com.honeysense.magpie.medium.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "广告规格 - 投放期数 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumSpecQuote extends MagpieEntity {
    public enum Status {
        /**
         * 上架中
         */
        ONLINE,
        /**
         * 下架中
         */
        OFFLINE
    }

    @ApiModelProperty(value = "开发者 ID", required = true)
    @NotNull
    private Long appId;
    @ApiModelProperty(value = "投放地域", required = true)
    @NotNull
    private Long areaId;
    @ApiModelProperty(value = "报价活动名称", required = true)
    @NotBlank
    private String name;
    @ApiModelProperty(value="包含的报价类型", required = true)
    @OneToMany
    private List<MediumSpecBasic> mediumSpecBasics;
    @ApiModelProperty(value="价格（以分为单位)", required = true)
    @NotNull
    @Min(0)
    private Integer price;
    @ApiModelProperty(value="投放天数", required = true)
    @NotNull
    @Min(0)
    private Integer day;
    @ApiModelProperty(value = "备注", required = true)
    private String remark;
    @ApiModelProperty(value = "状态", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
    @ApiModelProperty(value = "引用次数", required = true)
    @NotNull
    @Min(0)
    private Integer referCount;
}
