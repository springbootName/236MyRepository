package com.example.mail.demo13_1.demo13_1;


import com.example.mail.demo13_1.demo13_1.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Demo131ApplicationTests {
    @Resource
    private MailService mailService;
    @Test
    public void testSendSimpleMail(){
        mailService.sendSimpleMail("2437226380@qq.com","785494189@qq.com","785494189@qq.com","第一封邮件","萨拉黑");
    }

    @Test
    public void sendAttacgeMail() {
        mailService.sendAttacgeMail("2437226380@qq.com","785494189@qq.com","第一封带附件邮件","萨拉黑",new File("D:\\照片\\u=2843140950,2236303840&fm=26&gp=0.jpg"));
    }
    @Test
    public void sendGeishi(){

    }

}
