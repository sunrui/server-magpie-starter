package com.honeysense.magpie.user.controller.a.res;

import com.honeysense.magpie.framework.object.MagpieObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostDestroyRes extends MagpieObject {
    private Boolean userIdNotExists;
    private Boolean success;
    private Boolean cannotDestroyYourself;
}
