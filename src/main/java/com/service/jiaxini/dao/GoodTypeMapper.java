package com.service.jiaxini.dao;

import com.service.jiaxini.expand.vo.GoodsTypeVO;
import com.service.jiaxini.po.GoodType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
public interface GoodTypeMapper extends BaseMapper<GoodType> {

    List<GoodsTypeVO> getTree();
}
