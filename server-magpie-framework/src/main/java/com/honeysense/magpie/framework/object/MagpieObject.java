package com.honeysense.magpie.framework.object;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.honeysense.magpie.framework.utils.convert.MagpieJsonConvert;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@ApiModel(value = "对象")
@Slf4j
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MagpieObject implements Serializable {
    @Override
    public String toString() {
        return MagpieJsonConvert.toJson(this, true);
    }

    public void dump() {
        log.debug(toString());
    }
}
