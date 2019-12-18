package com.service.jiaxini.service.impl;

import com.service.jiaxini.po.Order;
import com.service.jiaxini.dao.OrderMapper;
import com.service.jiaxini.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
