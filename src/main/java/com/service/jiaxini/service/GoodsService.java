package com.service.jiaxini.service;

import com.service.jiaxini.expand.dto.LoginDTO;
import com.service.jiaxini.expand.param.SaveGoodsParam;
import com.service.jiaxini.po.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
public interface GoodsService extends IService<Goods> {

    /** 保存草稿 */
    Long saveTempory(SaveGoodsParam param, LoginDTO loginDTO);

}
