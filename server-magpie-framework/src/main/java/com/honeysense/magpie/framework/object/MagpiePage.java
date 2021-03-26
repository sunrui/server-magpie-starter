package com.honeysense.magpie.framework.object;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "对象 - 分页")
@Getter
@Setter
public class MagpiePage<T> extends MagpieObject {
    @ApiModelProperty(value = "元素")
    private List<T> elements = new ArrayList<>();
    @ApiModelProperty(value = "当前页")
    private int currentPage;
    @ApiModelProperty(value = "总页")
    private int totalPages;
    @ApiModelProperty(value = "每页大小")
    private int everyPageSize;
    @ApiModelProperty(value = "当前页大小")
    private int currentPageSize;
    @ApiModelProperty(value = "总大小")
    private long totalSize;

    public MagpiePage(Page<T> page) {
        if (page == null || page.getNumberOfElements() == 0) {
            currentPage = 0;
            totalPages = 0;
            everyPageSize = 0;
            currentPageSize = 0;
            totalSize = 0;
            return;
        }

        for (T element : page) {
            elements.add(element);
        }

        currentPage = page.getNumber();
        totalPages = page.getTotalPages();
        everyPageSize = page.getSize();
        currentPageSize = page.getNumberOfElements();
        totalSize = page.getTotalElements();
    }
}
