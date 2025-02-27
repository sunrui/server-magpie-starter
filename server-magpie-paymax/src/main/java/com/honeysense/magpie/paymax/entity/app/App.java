package com.honeysense.magpie.paymax.entity.app;

import com.honeysense.magpie.framework.saas.entity.MagpieUserManyEntity;
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
public class App extends MagpieUserManyEntity {
    @NotBlank
    private String name;
    @NotBlank
    private String secret;
    @NotNull
    @Enumerated(EnumType.STRING)
    private AppConfigType appConfigType;
    @NotNull
    private Boolean enable;
    @ManyToOne
    private AppConfigPayStack appConfigPayStack;
}
