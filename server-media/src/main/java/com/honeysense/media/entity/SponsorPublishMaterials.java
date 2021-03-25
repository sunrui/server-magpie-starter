package com.honeysense.media.entity;

import com.honeysense.magpie.entity.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "广告主 - 投放期数下的广告主提供的物料 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SponsorPublishMaterials extends MagpieEntity {
    @ApiModelProperty(value="渠道 ID", required = true)
    @NotNull
    private Long channelId;
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="广告投放基本规格", required = true)
    @NotNull
    @ManyToOne
    private SpecBasic specBasic;
    @ApiModelProperty(value="提供的物料图片", required = true)
    @NotBlank
    private String materialsImage;
    @ApiModelProperty(value="备注信息")
    private String remark;
}
