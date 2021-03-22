package com.example.mail.demo13_1.demo13_1.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.xml.soap.MimeHeader;
import java.io.File;

@Service
public class MailService {
    @Resource
    private MailSender mailSender;
    @Resource
    private JavaMailSender javaMailSender;
    public void sendSimpleMail(String from,String to,String cc,String subject,String context){
        try{
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom(from);//发件人
            mailMessage.setTo(to);//收件人
            mailMessage.setCc(cc);//
            mailMessage.setSubject(subject);//主题
            mailMessage.setText(context);
            mailSender.send(mailMessage);
            System.out.println("邮件发送成功");
        }catch (Exception e){
            System.out.println("邮件发送失败");
            e.printStackTrace();
        }
    }
    public void sendAttacgeMail(String from, String to, String subject, String context, File file) {
        try{
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);//发件人
            mimeMessageHelper.setTo(to);//收件人
            mimeMessageHelper.setSubject(subject);//主题
            mimeMessageHelper.setText(context);
            mimeMessageHelper.addAttachment(file.getName(),file);
            javaMailSender.send(mimeMessage);
            System.out.println("带附件的邮件发送成功");
        }catch (Exception e){
            System.out.println("带附件的邮件发送失败");
            e.printStackTrace();
        }

    }
    public void sendGeishi(String from, String to, String subject, String context,String[] imgpath,String[] resourceId){
        try{
            if (imgpath.length!=resourceId.length){
                System.out.println("带附件的邮件发送失败");
                return;
            }
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);//发件人
            mimeMessageHelper.setTo(to);//收件人
            mimeMessageHelper.setSubject(subject);//主题
            mimeMessageHelper.setText(context);
            for (int i=0;i<imgpath.length;i++){
                FileSystemResource res=new FileSystemResource(new File(imgpath[i]));
                mimeMessageHelper.addInline(resourceId[i],res);
            }
            javaMailSender.send(mimeMessage);
            System.out.println("带图片资源的邮件发送成功");


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
