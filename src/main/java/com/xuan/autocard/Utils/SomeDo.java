package com.xuan.autocard.Utils;

import com.xuan.autocard.WebSocket.ConnWebSocket;
import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;
import java.util.UUID;

public class SomeDo {
    public Object getNow() throws IOException, InterruptedException {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        Runtime.getRuntime().exec("adb shell screencap -p /sdcard/"+uuid+".png");
        Thread.sleep(2222);

        Runtime.getRuntime().exec("adb pull /sdcard/"+uuid+".png d:/pic/"+uuid+".png");
        Thread.sleep(2222);

//        String sendMail = SendMailText_Picture_Enclosure.sendMail(email,"d:/pic/" + uuid + ".png",isWhat);
        return null;
    }


    public void openDD(){

    }

    public  static void nowScrn() throws IOException, InterruptedException {
        String uuid = "pic"+UUID.randomUUID().toString().replaceAll("-", "");
//        long l = System.currentTimeMillis();
        Runtime.getRuntime().exec("adb shell screencap -p /sdcard/"+uuid+".jpg");
//        System.out.println(System.currentTimeMillis()-l);
        Thread.sleep(2000);
        Runtime.getRuntime().exec("adb pull /sdcard/"+uuid+".jpg d:/pic/"+uuid+".jpg");
        System.out.println("ok");
        Thread.sleep(4000);
        try {
            Thumbnails.of("d:/pic/" + uuid + ".jpg")
                    .scale(1f)
                    .outputQuality(0.5f)
                    .toFile("d:/pic/" + uuid + ".jpg");
        } catch (Exception e) {
            System.out.println(TimeFormat.now()+"图片压缩操作出现异常,即将发送原图...");
        }
        String send = BackUpFile.send(null, "d:/pic/" + uuid + ".jpg");
        if (send.equals("success")) {
            System.out.println(TimeFormat.now()+"获取当前状态完成,文件发送成功,发送socket指令...");
            ConnWebSocket.session.getBasicRemote().sendText(uuid+".jpg");
        }
    }
}
