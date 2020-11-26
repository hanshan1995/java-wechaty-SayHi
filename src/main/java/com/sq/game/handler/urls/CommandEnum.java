package com.sq.game.handler.urls;

public enum CommandEnum {

    OP_USER_CREATE(1,"/create","加入游戏",false),
    ROLE_CREATE(1,"/create","创建角色",true),
    ROLE_INFO(1,"/info","我",false),
    BACKPACK_INFO(1,"/BPContent","背包",false),
    BACKPACK_WEAR(1,"/wear","穿戴",true),
    BACKPACK_AUTO_WEAR(1,"/autoWear","一键穿戴",false),
    BACKPACK_GIVING(1,"/giving","赠送",true),
    SKILLS_LEARNING(1,"/learning","学习技能",true),
    SKILLS_LIST(1,"/list","门派技能",false),
    SKILLS_MY_LIST(1,"/myList","我的技能",false),
    ARSENAL_LIST(2,"/list","武器",false),
    ARSENAL_COURSE(1,"/course","合成路线",true),
    ARSENAL_SYNTHETIC(1,"/synthetic","合成武器",true),
    ARSENAL_ALIAS(1,"/alias","武器更名",true),
    MATERIAL_LIST(2,"/list","材料",false),
    MATERIAL_COURSE(2,"/course","材料合成",false),
    MATERIAL_SYNTHETIC(1,"/synthetic","合成材料",true),
    MONSTER_LIST(2,"/list","野怪",false),
    TITLE_LIST(2,"/list","称号",false),
    TITLE_WEAR(1,"/wear","称号佩戴",true),
    MAP_LIST(2,"/list","地图",false),
    MAP_MOVE(1,"/move","移动",true),
    UNITED_LIST(2,"/list","门派",false),
    UNITED_INFO(1,"/info","门派信息",true),
    UNITED_JOIN(1,"/join","加入门派",true),
    UNITED_LEAVE(1,"/leave","离开门派",true),
    COLLECT(1,"/collect","采集",true),
    DUEL(1,"/duel","决斗",true);


    /** 1 post, 2 get */
    private Integer type;

    /** url */
    private String url;

    /** 匹配字符 */
    private String matchValue;

    /** 是否添加额外参数 */
    private boolean isParam;


    CommandEnum(Integer type,String url,String matchValue,boolean isParam) {
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

enum CommandParent{
    OpUser(1,"用户","/sys/opUser",false,new CommandEnum[]{
            CommandEnum.OP_USER_CREATE
    }),
    Role(2,"角色","/sys/role",true,new CommandEnum[]{
            CommandEnum.ROLE_CREATE,
            CommandEnum.ROLE_INFO
    }),
    BackPack(3,"背包","/sys/backpack",true,new CommandEnum[]{
            CommandEnum.BACKPACK_INFO,
            CommandEnum.BACKPACK_WEAR,
            CommandEnum.BACKPACK_AUTO_WEAR,
            CommandEnum.BACKPACK_GIVING
    }),
    Skills(4,"技能","/sys/skills",true,new CommandEnum[]{
            CommandEnum.SKILLS_LEARNING,
            CommandEnum.SKILLS_LIST,
            CommandEnum.SKILLS_MY_LIST
    }),
    Arsenal(5,"武器","/sys/arsenal",true,new CommandEnum[]{
            CommandEnum.ARSENAL_LIST,
            CommandEnum.ARSENAL_COURSE,
            CommandEnum.ARSENAL_SYNTHETIC,
            CommandEnum.ARSENAL_ALIAS,
    }),
    Material(6,"材料","/sys/material",true,new CommandEnum[]{
            CommandEnum.MATERIAL_LIST,
            CommandEnum.MATERIAL_COURSE,
            CommandEnum.MATERIAL_SYNTHETIC
    }),
    Monster(7,"野怪","/sys/monster",true,new CommandEnum[]{
            CommandEnum.MONSTER_LIST
    }),
    Title(8,"称号","/sys/title",true,new CommandEnum[]{
            CommandEnum.TITLE_LIST,
            CommandEnum.TITLE_WEAR
    }),
    PtMap(9,"地图","/sys/platMap",true,new CommandEnum[]{
            CommandEnum.MAP_LIST,
            CommandEnum.MAP_MOVE
    }),
    United(10,"门派","/sys/united",true,new CommandEnum[]{
            CommandEnum.UNITED_LIST,
            CommandEnum.UNITED_INFO,
            CommandEnum.UNITED_JOIN,
            CommandEnum.UNITED_LEAVE
    }),
    Collect(11,"采集","/mbg/collect",true,new CommandEnum[]{
            CommandEnum.COLLECT
    }),
    Fighting(12,"战斗","/mbg/fighting",true,new CommandEnum[]{
            CommandEnum.DUEL
    });

    private Integer pid;
    private String desc;
    private String baseUrl;
    private boolean isShow;
    private CommandEnum[] arr;

    CommandParent(Integer pid,String desc,String baseUrl,boolean isShow,CommandEnum[] arr) {
        this.pid = pid;
        this.desc = desc;
        this.baseUrl = baseUrl;
        this.isShow = isShow;
        this.arr = arr;
    }

    public Integer getPid() {
        return pid;
    }

    public String getDesc() {
        return desc;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean isShow() {
        return isShow;
    }

    public CommandEnum[] getArr() {
        return arr;
    }
}