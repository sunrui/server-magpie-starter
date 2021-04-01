package com.honeysense.magpie.medium.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(value = "广告主 - 投放期数 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumSponsorPublish extends MagpieEntity {
    /**
     * 投放状态
     */
    public enum Status {
        ONLINE, OFFLINE
    }

    @ApiModelProperty(value="开发者 ID", required = true)
    @NotNull
    private Long appId;
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="参与的广告投放", required = true)
    @NotNull
    @JoinColumn
    @ManyToOne
    private MediumSpecQuote mediumSpecQuote;
    @ApiModelProperty(value="上传的物料信息")
    @OneToMany
    private List<MediumSponsorPublishMaterials> mediumSponsorPublishMaterials;
    @ApiModelProperty(value="投放期数下的广告主", required = true)
    @NotNull
    @OneToOne
    private MediumSponsorPublish mediumSponsorPublish;
    @ApiModelProperty(value="对外投放的价格", required = true)
    @NotNull
    @Min(0)
    private Integer price;
    @ApiModelProperty(value="投放天数", required = true)
    @NotNull
    @Min(0)
    private Integer day;
    @ApiModelProperty(value="广告主发布状态", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
