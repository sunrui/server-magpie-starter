package com.honeysense.magpie.framework.object;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(value = "分页对象")
@Getter
@Setter
@AllArgsConstructor
public class MagpiePageRequest extends MagpieObject {
    @ApiParam(value = "第几页")
    @NotNull
    @Min(0)
    private Integer page;
    @ApiParam(value = "页大小")
    @NotNull
    @Min(1)
    private Integer pageSize;

    public PageRequest of() {
        return PageRequest.of(page, pageSize);
    }
}
