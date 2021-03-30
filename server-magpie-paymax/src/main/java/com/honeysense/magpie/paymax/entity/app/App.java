package com.honeysense.magpie.paymax.entity.app;

import com.honeysense.magpie.framework.object.MagpieEntity;
import com.honeysense.magpie.paymax.entity.gateway.GatewayPayStack;
import com.honeysense.magpie.paymax.entity.gateway.GatewayType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class App extends MagpieEntity {
    @NotBlank
    private String userId;
    @NotBlank
    private String name;
    @NotBlank
    private String secret;
    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayType gatewayType;
    @NotNull
    private Boolean enable;
    @ManyToOne
    private GatewayPayStack gatewayPayStack;
}
