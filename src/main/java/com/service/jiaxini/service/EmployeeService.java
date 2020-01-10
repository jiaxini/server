package com.service.jiaxini.service;

import com.service.jiaxini.po.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author zengguangfu
 * @since 2019-12-18
 */
public interface EmployeeService extends IService<Employee> {

    public Employee selectById(Long id);
}
