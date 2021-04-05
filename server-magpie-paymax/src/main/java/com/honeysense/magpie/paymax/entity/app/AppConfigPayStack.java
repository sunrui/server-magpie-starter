package com.honeysense.magpie.paymax.entity.app;

import com.honeysense.magpie.framework.object.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class AppConfigPayStack extends MagpieEntity {
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private String publicKey;
    @NotBlank
    private String secretKey;
    @NotNull
    private Integer reference;
    private String email;
    private String password;
    private String comment;
}
