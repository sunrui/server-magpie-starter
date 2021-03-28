package com.honeysense.magpie.sms.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.Date;

@ApiModel(value = "实例 - 验证码")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SmsCode extends MagpieEntity {
    public static final Integer MAX_SEND_PER_DAY = 5;

    @ApiModelProperty(value="渠道 ID", example = "001", required = true)
    @NotNull
    private Long channelId;
    @ApiModelProperty(value="短信类型", example = "LOGIN", required = true)
    @NotNull
    @Enumerated(EnumType.STRING)
    private SmsCodeType type;
    @ApiModelProperty(value="手机号", example = "15012341234", required = true)
    @NotBlank
    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;
    @ApiModelProperty(value="验证码", example = "123456", required = true)
    @NotNull
    @Length(min = 6, max = 6)
    private String code;
    @ApiModelProperty(value="天数", example = "20200101", required = true)
    @NotNull
    @Min(20200101)
    @Max(20990101)
    private Integer day;
    @ApiModelProperty(value="验证次数", example = "1", required = true)
    @NotNull
    @Min(0)
    private Integer verifyTimes;
    @ApiModelProperty(value="过期时间", required = true)
    @NotNull
    private Date expiredAt;
    @ApiModelProperty(value="IP 地址", example = "127.0.0.1", required = true)
    @NotBlank
    private String ip;
    @ApiModelProperty(value="UA 代理", required = true)
    @NotBlank
    private String userAgent;
}
