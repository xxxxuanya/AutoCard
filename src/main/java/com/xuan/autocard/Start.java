package com.xuan.autocard;

import com.xuan.autocard.config.Coordinate;
import com.xuan.autocard.mail.SendMailText_Picture_Enclosure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import static com.xuan.autocard.AutocardApplication.openTest;
import static com.xuan.autocard.config.config.*;

public class Start {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    boolean topEmail = true;
    boolean xiaEmail = true;
    static Integer count = 0;

    public void autoCard() throws InterruptedException {
        System.out.println("////////////////////////////////////////////////");
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入上班打卡时间(示例:08:50,请注意输入英文冒号): ");
        String top = scanner.next();
        top = top.replace(":", "");
        System.out.println("请输入下班打卡时间(示例:18:30,请注意输入英文冒号): ");
        String xia = scanner.next();
        System.out.println("请输入通知(邮箱)地址: ");
        String email = scanner.next();
        xia = xia.replace(":", "");


        System.out.println("--------------------------设置信息--------------------------");
        System.out.println("▇sat_start_time:" + top);
        System.out.println("▇end_start_time:" + xia);
        System.out.println("▇email:" + email);
        System.out.println("--------------------------设置信息--------------------------");

        System.out.println("输入1进行测试,输入2跳过测试");
        String test = scanner.next();
        scanner.close();
        scanner.close();
        if (test.equals("1")) {
            try {

                logger.info("[test]打开钉钉");
                Runtime.getRuntime().exec(OPEN_APP.replace("package", DING_DING_PACKAGE));
                logger.info("[test]等待8S");

                //有些手机可能卡慢 我的小米5S 8S刚刚好
                Thread.sleep(8000);

                logger.info("[test]尝试进入公司主页");
                Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_INDEX));
                Thread.sleep(7000);

                logger.info("[test]尝试进入打卡页面");
                Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_CLOCK_IN));
                Thread.sleep(8000);

                backMenu(CLICK_BACK_MENU_COUNT);

                logger.info("已经执行五次返回操作返回桌面,测试完毕,任务已经开始");

                Thread.sleep(2000);
                Runtime.getRuntime().exec(OPEN_APP.replace("package", WORK_FINISH_PACKAGE));

            } catch (IOException | InterruptedException e) {
                System.out.println("执行中断,系统退出...");
                e.printStackTrace();
                System.exit(0);
            }
        }

        /********************上面是前置方法 得到基本信息与测试手机连接状态****************/


        /**
         * 二十秒执行一次的循环
         */
        while (true) {
            if (!openTest)
                openTest = true;
            String time = new SimpleDateFormat("HHmm").format(new Date());
            if (GetWeekInfo.getDamaiDay(null) == 6 || GetWeekInfo.getDamaiDay(null) == 7) {
                if (time.startsWith(xia))
                    switchApp("下班打卡", email);
                else if (time.startsWith(top))
                    switchApp("上班打卡", email);
            } else System.out.println("周末跳过...");

            //新的一天 可以发送邮件
            if (time.startsWith("0000")) {
                topEmail = true;
                xiaEmail = true;
            }

            Thread.sleep(20000);
        }
    }

    public boolean switchApp(String isWhat, String email) {
        logger.info("开始打卡进程,将随机暂停1-5分钟再执行");
        int rml = (int) (Math.random() * 5 * 60 * 1000);
        Date date = new Date(System.currentTimeMillis() + rml);
        String runTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        logger.info("计划执行时间:" + runTime);
        try {
            Thread.sleep(rml);
        } catch (InterruptedException e) {
            logger.error("随机暂停执行失败,继续执行...");
        }

        count++;
        if (count % 10 == 0 && count != 0)
            logger.info("[打卡进程  {} ]:已经执行 {} 次", isWhat, count);
        try {
            Runtime.getRuntime().exec(OPEN_APP.replace("package", DING_DING_PACKAGE));
            logger.info("[open]:尝试打开钉钉");

            logger.info("[wait]:等待8秒");
            Thread.sleep(8000);

            if (CLICK_DONT_UPDATE){
                Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.DONT_UPDATE));
                logger.warn("[click]点击更新提示的‘暂不更新’按钮");
            }

            Thread.sleep(200);
            backMenu(CLICK_BACK_MENU_COUNT);
            logger.warn("[click]点击五次返回按钮,尝试返回到桌面");

            Runtime.getRuntime().exec(OPEN_APP.replace("package", DING_DING_PACKAGE));
            logger.info("[open]:尝试打开钉钉");

            logger.info("[click]:尝试点击中间按钮进入单位主页");
            Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_INDEX));

            logger.info("[wait]:等待5秒");
            Thread.sleep(5000);

            Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_CLOCK_IN));
            logger.info("[click]:尝试进入打卡页面");

            logger.info("[wait]:等待两秒");
            Thread.sleep(2000);

            backMenu(CLICK_BACK_MENU_COUNT);
            logger.warn("[click]点击5次返回按钮,尝试返回到桌面");

            logger.info("[open]:尝试打开钉钉");
            Runtime.getRuntime().exec(OPEN_APP.replace("package", DING_DING_PACKAGE));

            logger.info("[wait]:等待五秒");
            Thread.sleep(5000);

            logger.info("[click]:尝试进入公司主页");
            Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_INDEX));

            logger.info("[click]:尝试进入公司主页");
            Thread.sleep(500);
            Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_INDEX));

            logger.info("[click]:尝试进入公司主页");
            Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_INDEX));
            Thread.sleep(7000);

            logger.info("[click]:尝试进入打卡页面");
            Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_CLOCK_IN));
            Thread.sleep(500);

            logger.info("[click]:尝试进入打卡页面");
            Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.FIRM_CLOCK_IN));

            logger.info("[wait]:等待10秒");
            Thread.sleep(10000);

            if ((isWhat.equals("上班打卡") && topEmail) || (isWhat.equals("下班打卡") && xiaEmail)) {
                if (isWhat.equals("上班打卡")) {
                    Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.UP_CLICK));
                    logger.info("[click]:尝试点击上班打卡");
                }

                if (isWhat.equals("下班打卡")) {
                    Runtime.getRuntime().exec(Coordinate.execShell(Coordinate.DOWN_CLICK));
                    logger.info("[click]:尝试点击下班打卡");
                }

                Thread.sleep(3000);
                try {
                    System.out.print("发送通知邮件...                   ");
                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                    Runtime.getRuntime().exec("adb shell screencap -p /sdcard/" + uuid + ".png");
                    Thread.sleep(2222);

                    Runtime.getRuntime().exec("adb pull /sdcard/" + uuid + ".png d:/pic/" + uuid + ".png");
                    Thread.sleep(2222);

                    String sendMail = SendMailText_Picture_Enclosure.sendMail(email, "d:/pic/" + uuid + ".png", isWhat);
                    if (isWhat.equals("上班打卡"))
                        topEmail = false;
                    else
                        xiaEmail = false;
                    System.out.println(sendMail);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Thread.sleep(6666);
            backMenu(CLICK_BACK_MENU_COUNT);
            Runtime.getRuntime().exec(OPEN_APP.replace("package", WORK_FINISH_PACKAGE));

            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }


    public void backMenu(Integer count) {
        for (int i = 0; i < count; i++) {
            try {
                Runtime.getRuntime().exec(EXEC_SHELL.replace("target", PHONE_BACK_CODE));
                Thread.sleep(700);
            } catch (IOException e) {
                System.out.println("cmd执行异常");
            } catch (InterruptedException e) {
                System.out.println("sleep异常");
            }
        }
    }

    public static void main(String[] args) {
        int rml = (int) (Math.random() * 5 * 60 * 1000);
        System.out.println(rml);
    }
}
