package com.lxy.blogproject.controller;

import com.lxy.blogproject.entity.CategoryInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 分类信息控制器
 *
 * @author:lxy
 * @create:2018-12-13-下午 15:17
 */

@RestController
@RequestMapping("/api/catagory")
public class CategoryController {

    @ApiOperation("获取所有分类信息")
    @GetMapping("/list")
    public List<CategoryInfo> listAllCategoryInfo(){
        return null;
    }

    @ApiOperation("获取一条分类信息")
    @ApiImplicitParam(name="id",value = "分类ID",required = true,dataType = "Long")
    @GetMapping("/{id}")
    public CategoryInfo getCategoryInfoById(@PathVariable Long id){
        return null;
    }

}
