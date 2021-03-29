package com.honeysense.magpie.paymax.entity.gateway;

import com.honeysense.magpie.framework.object.MagpieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class GatewayPayStack extends MagpieEntity {
    private String email;
    private String password;
    @NotBlank
    private String publicKey;
    @NotBlank
    private String secretKey;
}
