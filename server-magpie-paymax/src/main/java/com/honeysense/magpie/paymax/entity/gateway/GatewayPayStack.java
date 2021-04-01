package com.honeysense.magpie.paymax.entity.gateway;

import com.honeysense.magpie.framework.saas.entity.MagpieAppUserManyEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class GatewayPayStack extends MagpieAppUserManyEntity {
    private String email;
    private String password;
    @NotBlank
    private String publicKey;
    @NotBlank
    private String secretKey;
}
