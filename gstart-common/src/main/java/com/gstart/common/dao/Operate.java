package com.gstart.common.dao;

/**
 * @author yangguangye
 * @Create by gzpykj
 * @Date 2018-05-01 15:15
 */
public enum Operate {
    EQ(Base.class.toString()),NE(Base.class.toString())/*,NE("!=")
    ,NE("!="),NE("!="),NE("!=")
    ,NE("!="),NE("!="),NE("!=")*/;
    private String type;

    Operate() {
        type = "default";
    }
    Operate(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
class Base{

}
class Mysql extends Base{

}
