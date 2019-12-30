package com.service.jiaxini.util;

import lombok.Getter;

/**
 * @description: 常用枚举
 * @author: ZengGuangfu
 */
public class SysEnum {

    public enum SeasonStatus{
        ZHENGFU(1),
        GEJU(2),
        LUANSHI(3),
        LIULANG(4),;

        private Integer code;

        SeasonStatus(Integer code){
            this.code = code;
        }

        public Integer getCode(){
            return code;
        }
    }

    public enum TeamRelationship{
        FRIENDLY(0),
        HOSTILE(1),;

        private int code;

        private TeamRelationship(Integer code){
            this.code = code;
        }

        public int getCode(){
            return code;
        }
    }

    public enum Result{
        SUCCESS,
        FAILD,;
    }

    public enum LimitType{
        IP,
        SPECIAL,;
    }

    public enum BornQuery{
        YANGZHOU(1,"扬州"),
        YIZHOU(2,"益州"),
        JINGZHOU(3,"荆州"),
        LIANGZHOU(4,"凉州"),
        BINGZHOU(5,"并州"),
        XUZHOU(6,"徐州"),
        QINGZHOU(7,"青州"),
        JIZHOU(8,"冀州"),
        YOUZHOU(9,"幽州")
        ;
        @Getter
        private Integer code;

        @Getter
        private String bornName;

        BornQuery(Integer code, String bornName){
            this.code = code;
            this.bornName = bornName;
        }

    }
}
