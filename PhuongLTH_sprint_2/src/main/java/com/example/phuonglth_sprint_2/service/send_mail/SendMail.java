package com.example.phuonglth_sprint_2.service.send_mail;

import com.example.phuonglth_sprint_2.common.MyConstants;
import com.example.phuonglth_sprint_2.dto.customer.CustomerDto;
import com.example.phuonglth_sprint_2.service.customer.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class SendMail {
    private static final Logger logger = LoggerFactory.getLogger(SendMail.class);
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    ICustomerService customerService;

    @Async
    public void SendEmailToCustomer(CustomerDto customerDto) {
        try {
            //        SimpleMailMessage message = new SimpleMailMessage();
            Thread.sleep(3000L);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setFrom(MyConstants.MY_EMAIL);
            helper.setTo(customerDto.getEmail());

            String mailSubject = MyConstants.mailSubject;
            String mailContent = "<a><b>Người gửi: </b>" + "Camera Store" + "</a>";
            mailContent += "<p><b>Sender E-mail:</b> " + "camerastore@gmail.com" + "</p>";
            mailContent += "<p><b>Subject: </b>" + "Thư phản hồi" + "</p>";
            mailContent += "<p><b>Content: </b>" + "Chào mừng quí khách đã đến với Camera Store" + "</p>";
            mailContent += "<p><b>Tên đăng nhập: </b>" + customerDto.getEmail() + "</p>";
            mailContent += "<p><b>Mật khẩu: </b>" + customerDto.getEncryptPassword() + "</p>";
            mailContent += "<p><b>Content: </b>" + "Vui lòng đăng nhập để tiếp tục." + "</p>";
            helper.setSubject(mailSubject);
            helper.setText(mailContent, true);

            mailSender.send(message);
        } catch (MessagingException | IllegalArgumentException | InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    @Async
    public void SendEmailToAdmin() {
        try {
            Thread.sleep(3000L);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(MyConstants.MY_EMAIL);
            message.setTo(MyConstants.MY_EMAIL);
            String mailSubject = MyConstants.mailSubjectToAdmin;
            String mailContent = "Người gửi: " + "Camera Store" + "\n";
            mailContent += "Sender E-mail: " + "camerastore@gmail.com" + "\n";
            mailContent += "Content: " + "Đơn đặt hàng từ khách hàng" + "\n";
//        mailContent += "Tên đăng nhập: " + customerDto.getEmail() + "\n";
//        mailContent += "Mật khẩu: " + customerDto.getEncryptPassword() + "\n";
//        mailContent += "Content: " + "Vui lòng đăng nhập để tiếp tục." + "\n";
            message.setSubject(mailSubject);
            message.setText(mailContent);
            mailSender.send(message);
        }catch (IllegalArgumentException | InterruptedException e){
            logger.error(e.getMessage());
        }

    }
}
