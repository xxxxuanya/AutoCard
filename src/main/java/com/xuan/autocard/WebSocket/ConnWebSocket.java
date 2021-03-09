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
//            create by xuan on 2019/3/26  4:33 PM               //
///////////////////////////////////////////////////////////////////

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

//
public class ConnWebSocket {

    private static String uri = "ws://auto.vq8.net:19138/websocket?usr=root&pwd=6066";
    public static Session session;
    public static ConnWebSocket client = null;

    public static void start() {
        WebSocketContainer container = null;
        try {
            container = ContainerProvider.getWebSocketContainer();
        } catch (Exception ex) {
            System.out.println("error" + ex);
        }
        try {
            URI r = URI.create(uri);
            session = container.connectToServer(Client.class, r);
            System.out.println("session初始化");
        } catch (DeploymentException | IOException e) {
            System.out.println("session初始化失败");
        }
    }

    public void run() {
        System.out.println("尝试初始化socket服务器...");

        client = new ConnWebSocket();
//        ConnWebSocket client = new ConnWebSocket();
        try {
            client.start();
        }catch (Exception e){
            System.err.println("初始化链接socket服务失败,远程控制与实时图传已禁用");
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            do {
                input = br.readLine();
                if (!input.equals("exit")){
                    client.session.getBasicRemote().sendText("client of autocard:" + input);}
            } while (!input.equals("exit"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

