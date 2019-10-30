package com.service.jiaxini.conf.redis.prefix;

public interface Prefix {

    public int expire();

    public void setExpire(int expire);

    public String getPrefix();
}
