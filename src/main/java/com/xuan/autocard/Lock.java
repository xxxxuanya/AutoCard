package com.xuan.autocard;

public class Lock {

    public static void unLock() throws InterruptedException {

        //亮屏/滑动
        RunTimeExec.execString("adb shell input keyevent 26",600);
        RunTimeExec.execString("adb shell input swipe 500 500 500 70",600);
        //解锁
        RunTimeExec.execString("adb shell input tap 847 1400",500);//9
        RunTimeExec.execString("adb shell input tap 577 1568",500);//0
        RunTimeExec.execString("adb shell input tap 847 1400",500);//9
        RunTimeExec.execString("adb shell input tap 847 1400",500);//9
        Thread.sleep(1000);
        //9099
        //adb shell input tap 530 1850*/
        //return true;
    }

    public static void lock() throws InterruptedException {
        RunTimeExec.execString("adb shell input keyevent 26",600);
        Thread.sleep(1000);
    }
}
