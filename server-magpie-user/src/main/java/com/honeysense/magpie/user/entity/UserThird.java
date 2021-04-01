package com.honeysense.magpie.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "实例 - 用户 - 第三方登录")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserThird extends MagpieEntity {
    /**
     * 登录类型
     */
    public enum Type {
        WECHAT, ALIPAY
    }

    @ApiModelProperty(value="用户 ID", required = true)
    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private User user;
    @ApiModelProperty(value="登录类型", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;
    @ApiModelProperty(value="第三方 appId", required = true)
    @NotBlank
    private String appId;
    @ApiModelProperty(value="第三方 openId", required = true)
    @NotBlank
    @Column(unique = true)
    private String openId;
}
