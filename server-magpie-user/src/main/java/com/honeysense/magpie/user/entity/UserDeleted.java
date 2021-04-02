package com.honeysense.magpie.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.OrderBy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@ApiModel(value = "实例 - 用户")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDeleted extends MagpieEntity {
    @NotNull
    @Column(unique = true)
    private Long userId;
    @ApiModelProperty(value="用户登录号")
    @Pattern(regexp = "[a-zA-Z0-9_]{3,15}$")
    private String name;
    @ApiModelProperty(value="手机号")
    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;
    @ApiModelProperty(value="用户密码")
    @JsonIgnore
    private String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String comment;
}
