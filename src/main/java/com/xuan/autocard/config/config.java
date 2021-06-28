package com.xuan.autocard.config;

public class config {
    //控制点不点弹出的暂不更新按钮 前提是你知道按钮的位置 如果出现了这个位置 建议记下来
    public static final boolean CLICK_DONT_UPDATE = false;

    //哪个键码代表返回 我的小米是4 如果正常 不必修改
    public static final String PHONE_BACK_CODE = "4";

    //返回桌面的时候 点几次返回键
    public static int CLICK_BACK_MENU_COUNT = 5;

    //打开手机上的一个app 通过adb，用包名
    public static final String OPEN_APP = "adb shell am start -n package";

    //丁丁
    public static final String DING_DING_PACKAGE = "com.alibaba.android.rimet/com.alibaba.android.rimet.biz.LaunchHomeActivity";

    //执行完后打开的软件 这个是个时钟
    public static final String WORK_FINISH_PACKAGE = "one.alynx.flipclock/.MainActivity";

    //adb命令 执行按键代码
    public static final String EXEC_SHELL = "adb shell input keyevent target";

    //adb命令 模拟点击
    public static final String EXEC_CLICK = "adb shell input tap x y";

    //返回键
    public static final String EXEC_RETURN = "adb shell input keyevent 4";

    //发送邮件的账户信息
    public static final String MAIL_ACCOUNT = "autasdfrd@xuaasdf.co";
    public static final String MAIL_PWD = "adsfas.";
    public static final String TRANS_TYPE = "smtp";
    //其他参数详见 sendMail.class

}
