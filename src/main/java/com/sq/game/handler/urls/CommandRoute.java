package com.sq.game.handler.urls;

import java.util.HashMap;
import java.util.Map;

public enum CommandRoute {


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

    private Integer id;
    private String desc;
    private String baseUrl;
    private boolean isShow;
    private CommandEnum[] arr;

    public static final Map<String,CommandEnum> map = new HashMap();
    public static final String baseLink = "http://127.0.0.1:8123";
    static {
        initMap();
    }

    CommandRoute(Integer pid, String desc, String baseUrl, boolean isShow, CommandEnum[] arr) {
        this.id = pid;
        this.desc = desc;
        this.baseUrl = baseUrl;
        this.isShow = isShow;
        this.arr = arr;

    }

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getBaseUrl() {
        return  baseLink + baseUrl;
    }

    public boolean isShow() {
        return isShow;
    }

    public CommandEnum[] getArr() {
        return arr;
    }

    static void initMap() {
        CommandRoute[] values = CommandRoute.values();
        for (CommandRoute value : values) {
            CommandEnum[] arr = value.getArr();
            for (CommandEnum node : arr) {
                String fullUrl = value.getBaseUrl() + node.getUrl() ;
                node.setUrl(fullUrl);
                map.put( node.getMatchValue(),node);
            }
        }
    }

    public static CommandEnum getByMatchValue( String matchV  ) {
        return map.get(matchV);
    }

}