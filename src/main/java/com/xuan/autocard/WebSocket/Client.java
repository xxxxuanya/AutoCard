package com.xuan.autocard.WebSocket;
///////////////////////////////////////////////////////////////////
//                        .::::.                                  //
//                      .::::::::.                                //
//                     :::::::::::                                //
//                  ..:::::::::::'                                //
//               '::::::::::::'                                   //
//                 .::::::::::                                    //
//            '::::::::::::::..                                   //
//                 ..::::::::::::.                                //
//               ``::::::::::::::::                               //
//                ::::``:::::::::'        .:::.                   //
//               ::::'   ':::::'       .::::::::.                 //
//             .::::'      ::::     .:::::::'::::.                //
//            .:::'       :::::  .:::::::::' ':::::.              //
//           .::'        :::::.:::::::::'      ':::::.            //
//          .::'         ::::::::::::::'         ``::::.          //
//      ...:::           ::::::::::::'              ``::.         //
//     ```` ':.          ':::::::::'                  ::::..      //
//                                                                //
//            create by xuan on 2019/3/26  4:38 PM               //
///////////////////////////////////////////////////////////////////

//
import com.xuan.autocard.Utils.TimeFormat;

import javax.websocket.*;

import java.io.IOException;

import static com.xuan.autocard.mail.statics.Info.CONN_STATE;

@ClientEndpoint()
public class Client {
    @OnOpen
    public void onOpen(Session session) throws IOException, InterruptedException {
        CONN_STATE = true;
        System.out.print("WebSocket connection  ");
        new Thread() {
            public void run(){
                try {
                    Thread.sleep(2*60*2000);
                    System.out.println("socket即将运行....");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (CONN_STATE) {
                    try {
                        Thread.sleep(60*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Session session1 = ConnWebSocket.session;
                    RemoteEndpoint.Basic basicRemote = session1.getBasicRemote();
                    try {
                        basicRemote.sendText("-----连接检测-----");
                    } catch (IOException e) {
                        CONN_STATE = false;
                        System.out.println("连接检测失败,连接可能已断开");
                    }
                }
            }
        }.start();

    }
    @OnMessage
    public void onMessage(String message) {
        System.out.print(TimeFormat.now()+"Received instructions: " + message);
        //调用具体处理器
        try {
            Integer integer = Integer.valueOf(message);
            System.out.println("  指令");
            new HandleTask().exec(integer);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("  非数字指令,"+e.getMessage());
        }
    }
    @OnClose
    public void onClose() throws InterruptedException {
        CONN_STATE = false;
        System.out.println("链接断开,5s后尝试重连......");
        while (!CONN_STATE){
            Thread.sleep(5000);
            try {
                ConnWebSocket.start();
            } catch (Exception e) {
                System.err.println("重连失败,等待下次重连......");
            }
        }
    }
}
