package com.honeysense.magpie.paymax.controller.a.app.req;

import com.honeysense.magpie.framework.object.MagpieObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostAppConfigPayStackReq extends MagpieObject {
    @NotBlank
    @Column(unique = true)
    private String name;
    @NotBlank
    private String publicKey;
    @NotBlank
    private String secretKey;
    private String email;
    private String password;
    private String comment;
}
