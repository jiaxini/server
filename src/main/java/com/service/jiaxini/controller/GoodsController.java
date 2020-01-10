package com.service.jiaxini.controller;


import com.service.jiaxini.common.response.CodeMsg;
import com.service.jiaxini.common.response.ResultBody;
import com.service.jiaxini.expand.param.SaveGoodsParam;
import com.service.jiaxini.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Controller
@RequestMapping("/goods")
@Api(tags = "商品管理")
public class GoodsController extends BaseController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("hold")
    @ApiOperation("【商品管理】保存")
    public ResultBody save(@Validated SaveGoodsParam param){
        Long goodsId = goodsService.saveTempory(param, getCurrentUser());
        return ResultBody.success(goodsId);
    }

    @DeleteMapping("detachment")
    @ApiOperation("【商品管理】移除")
    public ResultBody delete(Long goodsId){
        if (goodsService.removeById(goodsId))
            return ResultBody.success();
        else
            return ResultBody.error(CodeMsg.DELETE_GOODS_ERROR);
    }

}

