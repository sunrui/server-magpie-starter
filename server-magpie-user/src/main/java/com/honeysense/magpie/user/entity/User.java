package com.honeysense.magpie.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.OrderBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import java.util.Set;

@ApiModel(value = "实例 - 用户")
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends MagpieEntity {
    @ApiModelProperty(value="用户登录号")
    @Column(unique = true)
    @Pattern(regexp = "[a-zA-Z0-9_]{3,15}$")
    private String name;
    @ApiModelProperty(value="手机号")
    @Column(unique = true, length = 11)
    @Pattern(regexp = "^[1][3-9][0-9]{9}$")
    private String phone;
    @ApiModelProperty(value="用户密码")
    @JsonIgnore
    private String password;
    @ApiModelProperty(value="第三方授权登录")
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @OrderBy(clause = "id desc")
    private Set<UserOAuth> opens;
}
