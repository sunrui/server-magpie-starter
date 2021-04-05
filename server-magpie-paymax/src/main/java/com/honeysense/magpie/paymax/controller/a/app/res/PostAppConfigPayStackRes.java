package com.honeysense.magpie.paymax.controller.a.app.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostAppConfigPayStackRes extends MagpieObject {
    private Boolean nameExists;
    private Long appConfigPayStackId;
}
