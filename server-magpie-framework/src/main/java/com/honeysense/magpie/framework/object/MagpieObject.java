package com.honeysense.magpie.framework.object;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.honeysense.magpie.framework.utils.convert.MagpieJsonConvert;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

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
