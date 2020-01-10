package com.service.jiaxini.controller;


import com.service.jiaxini.common.response.ResultBody;
import com.service.jiaxini.expand.vo.GoodsTypeVO;
import com.service.jiaxini.service.GoodTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Controller
@RequestMapping("/goodType")
@Api(tags = "商品分类")
public class GoodTypeController {

    @Autowired
    private GoodTypeService goodTypeService;

    @GetMapping("tree")
    @ApiOperation("【商品分类】树结构")
    public ResultBody<List<GoodsTypeVO>> goodTypeTree(){
        return ResultBody.success(goodTypeService.getTree());
    }

}

