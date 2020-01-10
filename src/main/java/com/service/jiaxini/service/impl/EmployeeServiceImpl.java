package com.service.jiaxini.service.impl;

import com.service.jiaxini.po.Employee;
import com.service.jiaxini.dao.EmployeeMapper;
import com.service.jiaxini.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.service.jiaxini.util.JudgeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    @SuppressWarnings("all")
    private EmployeeMapper employeeMapper;

    @Override
    public Employee selectById(Long id) {
        JudgeUtils.nonNull(id);
        return employeeMapper.selectById(id);
    }
}
