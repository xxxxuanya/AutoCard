package com.xuan.autocard;

import java.io.IOException;

public class RunTimeExec {

    public static void execString(String str,Integer sleep_time){
        try {
            Runtime.getRuntime().exec(str);
            Thread.sleep(sleep_time);
        } catch (IOException e) {
            System.out.println("========== cmd  exec  error ==========");
        } catch (InterruptedException e) {
            System.out.println("========== exec sleep error ==========");
        }
    }

}
