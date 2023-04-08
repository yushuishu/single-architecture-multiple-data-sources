package com.shuishu.demo.multiple.db.controller;


import com.shuishu.demo.multiple.db.common.domain.ApiResponse;
import com.shuishu.demo.multiple.db.common.domain.PageDTO;
import com.shuishu.demo.multiple.db.entity.po.DataSourceInfo;
import com.shuishu.demo.multiple.db.service.DataSourceManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：谁书-ss
 * @date ：2023-04-07 12:58
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @description ：数据源管理
 * <p></p>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("test")
public class DataSourceManageController {
    private final DataSourceManageService dataSourceManageService;


    @PostMapping("add")
    public ApiResponse<String> add(DataSourceInfo dataSourceInfo) {
        dataSourceManageService.add(dataSourceInfo);
        return ApiResponse.success("添加成功", "");
    }

    @GetMapping("page")
    public ApiResponse<Page<DataSourceInfo>> page(String name, String type, PageDTO pageDTO) {
        return ApiResponse.of(dataSourceManageService.page(name, type, pageDTO));
    }

    @GetMapping("details")
    public ApiResponse<DataSourceInfo> details(String name) {
        return ApiResponse.of(dataSourceManageService.details(name));
    }

    @PostMapping("update")
    public ApiResponse<String> update(DataSourceInfo dataSourceInfo) {
        dataSourceManageService.update(dataSourceInfo);
        return ApiResponse.success("修改成功", "");
    }

    @PostMapping("delete")
    public ApiResponse<String> delete(String name) {
        dataSourceManageService.delete(name);
        return ApiResponse.success("删除成功", "");
    }

    @GetMapping("enable")
    public ApiResponse<String> enable(String name) {
        dataSourceManageService.enable(name);
        return ApiResponse.success("开启关闭成功", "");
    }

}
