package com.service.jiaxini.service;

import com.service.jiaxini.expand.vo.GoodsTypeVO;
import com.service.jiaxini.po.GoodType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
public interface GoodTypeService extends IService<GoodType> {

    List<GoodsTypeVO> getTree();

}
