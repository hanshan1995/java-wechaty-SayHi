package com.sq.game.handler.urls;

/**
 * @program: sayhi
 * @description:
 * @author: zxw_
 * @create: 2020-11-26 09:28
 */
public enum TYPE_URL {

    OP_USER_CREATE(1,"/sys/opUser/create","加入游戏",false),
    ROLE_CREATE(1,"/sys/role/create","创建角色",true),
    ROLE_INFO(1,"/sys/role/info","我",false),
    BACKPACK_INFO(1,"/sys/backpack/BPContent","背包",false),
    BACKPACK_WEAR(1,"/sys/backpack/wear","穿戴",true);


    /** 1 post, 2 get */
    private Integer type;

    /** url */
    private String url;

    /** 匹配字符 */
    private String matchValue;

    /** 是否添加额外参数 */
    private boolean isParam;


    TYPE_URL(Integer type,String url,String matchValue,boolean isParam) {
        this.type = type;
        this.url = url;
        this.matchValue = matchValue;
        this.isParam = isParam;
    }

    public Integer getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getMatchValue() {
        return matchValue;
    }

    public boolean isParam() {
        return isParam;
    }
}
