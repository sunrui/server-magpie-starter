package com.honeysense.magpie.framework.saas.entity;

import com.honeysense.magpie.framework.object.MagpieEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@ApiModel(value = "实例 - 开发者一 - 基础")
@Getter
@Setter
@MappedSuperclass
public class MagpieAppOneEntity extends MagpieEntity {
    @NotNull
    @Column(unique = true)
    private Long appId;
}
