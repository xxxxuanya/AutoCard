package com.xuan.autocard.mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class SendMailText_Picture_Enclosure {

    public static String sendMail(String copy,String pic,String sxb){
        //1、连接邮件服务器的参数配置
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.xxx.com");
        props.setProperty("mail.transport.protocol", "SMTP");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.socketFactory.port", "25");
        Session session = Session.getInstance(props);
        //session.setDebug(true);
        Message msg = null;
        try {
            msg = getMimeMessage(session,copy,pic,sxb);
            Transport transport = session.getTransport("smtp");
            transport.connect("auto-card@xxxx.com", "123123");
            transport.sendMessage(msg,msg.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            System.err.println("发送通知邮件失败");
            e.printStackTrace();
            return "NO";
        }
        return "SUCCESS";
    }

    /** 获得创建一封邮件的实例对象*/
    public static MimeMessage getMimeMessage(Session session,String copy,String pic,String sxb) throws Exception{
        //1.创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //2.设置发件人地址
        msg.setFrom(new InternetAddress("auto-card@xxxx.com"));
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(copy));
        msg.setSubject("通知","UTF-8");

        // 5. 创建图片"节点"
        MimeBodyPart image = new MimeBodyPart();
        image.setContent("<h3>这是您今日"+sxb+"的打卡截图,请确认</h3><br/>", "text/html;charset=UTF-8");

        // 9. 创建附件"节点"
        MimeBodyPart attachment = new MimeBodyPart();
        // 读取本地文件
        DataHandler dh2 = new DataHandler(new FileDataSource(pic));
        // 将附件数据添加到"节点"
        attachment.setDataHandler(dh2);
        // 设置附件的文件名（需要编码）
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(image,0);
        mm.addBodyPart(attachment,1);// 如果有多个附件，可以创建多个多次添加
        mm.setSubType("mixed");// 混合关系

        // 11. 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
        msg.setContent(mm);
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());
        return msg;
    }

    public static void main(String[] args) {
        String s = sendMail("abc@outlook.com", "/Users/xuan/Downloads/IMG_0697.JPG", "S");
        System.out.println(s);
    }
}