package com.honeysense.magpie.medium.entity;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "广告规格 - 投放基本规格 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumSpecBasic extends MagpieEntity {
    /**
     * 规格类型
     */
    public enum Type {
        /**
         * 车座副驾驶贴纸
         */
        CAR_FRONT_SEAT_PASTER,
        /**
         * 车座副驾驶亚克力
         */
        CAR_FRONT_SEAT_PMMA,
        /**
         * 车座椅头枕两个
         */
        CAR_DOUBLE_SEAT_HEADREST,
        /**
         * 车后窗贴纸
         */
        CAR_REAR_WINDOW_PASTER_BIG,
        /**
         * 车后窗贴纸
         */
        CAR_REAR_WINDOW_PASTER_SMALL,
        /**
         * 车双侧车门
         */
        CAR_BILATERAL_DOORS_PASTER
    }

    @ApiModelProperty(value="规格类型", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;
    @ApiModelProperty(value="宽度", required = true)
    @NotNull
    @Min(0)
    private Integer dimWidth;
    @ApiModelProperty(value="高度", required = true)
    @NotNull
    @Min(0)
    private Integer dimHeight;
    @ApiModelProperty(value="效果图", required = true)
    @NotBlank
    private String previewImage;
}
