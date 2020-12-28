package com.xuan.autocard;

import com.xuan.autocard.WebSocket.ConnWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AutocardApplication {

    public static Boolean openTest = false;

        public static void main(String[] args) throws InterruptedException {





        SpringApplication.run(AutocardApplication.class, args);
        ConnWebSocket c = new ConnWebSocket();
        c.run();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("---------------[自动尝试已启用]---------------");





        new Thread() {
            public void run(){
                System.out.println("自动操作线程...");
                Start start = new Start();
                try {
                    start.autoCard();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }.start();







    }

}