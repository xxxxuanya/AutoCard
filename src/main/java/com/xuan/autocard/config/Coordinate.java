package com.xuan.autocard.config;

import static com.xuan.autocard.config.config.EXEC_CLICK;

//x and y
public enum Coordinate {
    FIRM_INDEX("530","1850","企业主页坐标"),
    FIRM_CLOCK_IN("145", "1050","打卡页面坐标"),
    DONT_UPDATE("646", "1150","不更新按钮位置"),
    UP_CLICK("565", "760","不更新按钮位置"),
    DOWN_CLICK("520", "1245","不更新按钮位置"),
    ;
    private String x;
    private String y;
    private String des;


    Coordinate(String x, String y,String des) {
        this.x = x;
        this.y = y;
    }

    public static String execShell(Coordinate target){
        return EXEC_CLICK.replace("x", target.x).replace("y", target.y);
    }

}
