package com.honeysense.magpie.framework.object;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ApiModel(value = "对象 - 令牌")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MagpieToken extends MagpieObject {
    public enum MagpieTokenType {
        PASSWORD, PHONE, WECHAT
    }

    @ApiModelProperty(value = "用户 ID", required = true, hidden = true)
    @NotNull
    private Long userId;
    @ApiModelProperty(value = "IP 地址", required = true, hidden = true)
    @NotBlank
    private String ip;
    @ApiModelProperty(value = "令牌类型", required = true, hidden = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private MagpieTokenType type;
    @ApiModelProperty(value = "创建时间", required = true, hidden = true)
    @NotNull
    private Date createdAt;
    @ApiModelProperty(value = "过期时间", required = true, hidden = true)
    @NotNull
    private Date expiredAt;
}
