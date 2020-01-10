package com.service.jiaxini.dao;

import com.service.jiaxini.expand.dto.GoodsNewDTO;
import com.service.jiaxini.po.Goods;
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
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Long> judgeRepetByNoOrName(GoodsNewDTO goodsNewDTO);
}
