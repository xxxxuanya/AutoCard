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
//            create by xuan on 2019/3/26  4:52 PM               //
///////////////////////////////////////////////////////////////////

import com.xuan.autocard.Lock;
import com.xuan.autocard.RunTimeExec;
import com.xuan.autocard.Start;
import com.xuan.autocard.Utils.BackUpFile;
import com.xuan.autocard.Utils.SomeDo;
import com.xuan.autocard.mail.SendMailText_Picture_Enclosure;
import net.coobird.thumbnailator.Thumbnails;

import java.io.IOException;
import java.util.UUID;

//
public class HandleTask {

    public void exec(int code) throws InterruptedException, IOException {
        switch (code){
            case 1://当前
                SomeDo.nowScrn();
                break;
            case 2:
                //解锁
                Lock.unLock();
                SomeDo.nowScrn();
                break;
            case 3:
                //锁屏
                Lock.lock();
                SomeDo.nowScrn();
                break;
            case 4:
                //运行主方法
                new Start().switchApp("打卡","xxx@123.com");
                SomeDo.nowScrn();
                break;
            case 5:
                //打开app
                Runtime.getRuntime().exec("adb shell am start -n com.alibaba.android.rimet/com.alibaba.android.rimet.biz.LaunchHomeActivity");
//                Runtime.getRuntime().exec("adb shell am start -n com.alibaba.android.rimet/com.alibaba.android.rimet.biz.SplashActivity");
                SomeDo.nowScrn();
                break;
            case 6:
                //返回
                RunTimeExec.execString("adb shell input keyevent 4",600);
                Thread.sleep(500);
                SomeDo.nowScrn();
                break;

        }
    }
}
