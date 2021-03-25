package com.honeysense.magpie.medium.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@ApiModel(value = "事件通知 - 实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MediumNotifyEvent extends MagpieEntity {
    /**
     * 事件通知类型
     */
    public enum NotifyEventType {
        /**
         * 投放广告订单状态变化
         */
        SERVING_ORDER_STATUS_CHANGE,

    }

    @ApiModelProperty(value="渠道 ID", required = true)
    @NotNull
    private Long channelId;
    @ApiModelProperty(value="通知类型", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private NotifyEventType type;
    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value="事件关联 ID", required = true)
    @NotNull
    private Long eventId;
    @ApiModelProperty(value="额外的数据 0")
    private String extra0;
    @ApiModelProperty(value="额外的数据 1")
    private String extra1;
    @ApiModelProperty(value="额外的数据 2")
    private String extra2;
}
