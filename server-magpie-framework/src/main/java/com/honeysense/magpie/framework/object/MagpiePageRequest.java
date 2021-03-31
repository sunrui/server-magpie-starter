package com.honeysense.magpie.framework.object;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ApiModel(value = "对象 - 分页 - 请求")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MagpiePageRequest extends MagpieObject {
    @ApiParam(value = "第几页")
    @NotNull
    @Min(0)
    @Max(100000)
    private Integer page;
    @ApiParam(value = "页大小")
    @NotNull
    @Min(1)
    @Max(100)
    private Integer pageSize;

    public PageRequest of() {
        return PageRequest.of(page, pageSize);
    }
}
