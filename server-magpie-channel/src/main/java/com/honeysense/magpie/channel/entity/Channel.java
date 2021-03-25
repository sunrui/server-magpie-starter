package com.honeysense.magpie.channel.entity;

import com.honeysense.magpie.framework.entity.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@ApiModel(value = "渠道实例")
@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Channel extends MagpieEntity {
    @ApiModelProperty(value="短号", example = "hz", required = true)
    @NotBlank
    private String shortId;
    @ApiModelProperty(value="名称", example = "杭州", required = true)
    @NotBlank
    private String name;
}
