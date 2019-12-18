package com.service.jiaxini.conf.mybatis_plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 *
 * @description: 代码生成器
 * </p>
 * @author: ZengGuangfu
 * @since 2019/10/25
 */
public class MybatisPlusGenerator {

    public static void main(String[] args) {
        // 1.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("C:/data/jiaxini/src/main/java/")
                .setIdType(IdType.AUTO)                                         // 自增长主键
                .setBaseResultMap(true)                                         // 在xml中自动生成resultMap
                .setFileOverride(true)                                          // 重新生成使用覆盖的形式
                .setActiveRecord(true)                                          // 开始AR Model
                .setBaseColumnList(true)                                        // 在xml中生成自动的<sql>标签
                .setAuthor("zengguangfu")
                .setServiceName("%sService")                                    // 生成的Service不是I 字母开头的
                .setServiceImplName("%sServiceImpl")
                .setOpen(false)                                                 // 生成完成后不弹出文件框
        ;

        // 2. 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/jiaxini?characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai")
                .setUsername("root")
                .setPassword("413anan%")
        ;

        // 3. 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setTablePrefix("j_")
                .setNaming(NamingStrategy.underline_to_camel)    // 下划线转驼峰
                //.setVersionFieldName("")      // 没有引入乐观锁插件，不需要这个
                .setInclude("j_address","j_boss","j_brand","j_discuss","j_discuss_percent"
                        ,"j_employee","j_good_detail","j_good_type","j_goods","j_order","j_product_boss")           // TODO 写入要生成代码的表的名字
        ;

        // 4.包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.service.jiaxini")
                .setMapper("dao")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller")
                .setXml("dao.xml")
                .setEntity("po")
        ;

        // 5.整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);
        autoGenerator.execute();
    }
}
