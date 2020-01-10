package com.service.jiaxini.service.impl;

import com.service.jiaxini.common.enums.JudgeEnum;
import com.service.jiaxini.common.response.CodeMsg;
import com.service.jiaxini.common.response.GlobalException;
import com.service.jiaxini.expand.dto.GoodsNewDTO;
import com.service.jiaxini.expand.dto.LoginDTO;
import com.service.jiaxini.expand.param.SaveGoodsParam;
import com.service.jiaxini.po.Goods;
import com.service.jiaxini.dao.GoodsMapper;
import com.service.jiaxini.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    @SuppressWarnings("all")
    private GoodsMapper goodsMapper;

    /**
     * 保存
     * @return
     */
    @Override
    public Long saveTempory(SaveGoodsParam param, LoginDTO loginDTO) {
        Goods goods = new Goods(param);
        goods.setCreateLoginName(loginDTO.getLoginName());

        // 验证商品名或商品编号不能重复
        GoodsNewDTO goodsNewDTO = GoodsNewDTO.builder().createLoginName(loginDTO.getLoginName())
                .name(param.getName())
                .productNo(param.getProductNo())
                .id(goods.getId())
                .build();
        List<Long> judgeGoodsIdList = goodsMapper.judgeRepetByNoOrName(goodsNewDTO);
        if (!CollectionUtils.isEmpty(judgeGoodsIdList)){
            throw new GlobalException(CodeMsg.GOODS_REPET);
        }

        if (Objects.isNull(goods.getId())){
            goods.setIsOnSale(JudgeEnum.NO.getCode());
            goods.setIssale(JudgeEnum.NO.getCode());
            goods.setViewNum(BigDecimal.ZERO);
            goods.setAppraiseNum(BigDecimal.ZERO);
            goods.setSaleNumber(BigDecimal.ZERO);
            goods.setCreateTime(new Date());
            goodsMapper.insert(goods);
        }else {
            goods.setUpdateTime(new Date());
            goodsMapper.updateById(goods);
        }
        return goods.getId();
    }

}
