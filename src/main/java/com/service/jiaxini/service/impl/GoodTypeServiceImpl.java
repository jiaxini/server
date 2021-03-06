package com.service.jiaxini.service.impl;

import com.service.jiaxini.expand.vo.GoodsTypeVO;
import com.service.jiaxini.po.GoodType;
import com.service.jiaxini.dao.GoodTypeMapper;
import com.service.jiaxini.service.GoodTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Service
public class GoodTypeServiceImpl extends ServiceImpl<GoodTypeMapper, GoodType> implements GoodTypeService {

    @Autowired
    @SuppressWarnings("all")
    private GoodTypeMapper goodTypeMapper;

    /**
     * 获取全部分类信息
     */
    @Override
    public List<GoodsTypeVO> getTree() {
        return goodTypeMapper.getTree();
    }
}
