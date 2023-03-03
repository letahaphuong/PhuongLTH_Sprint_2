package com.example.phuonglth_sprint_2.controller.autoSendEmail;

import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.service.customer.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SendMailController {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    ICustomerService customerService;

    @Scheduled(cron = "0 12 15 * * MON-FRI") // "s m h "
    public void sendEmail() {
        List<String> customerList = customerService.getAllEmailCustomer();
        if (!(customerList.size() == 0)) {
            String[] array = customerList.toArray(new String[0]);
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom("letahaphuong@gmail.com");
            message.setTo(array);
            message.setSubject("Chương trình khuyến mãi");
            message.setText("Chào bạn \n"
                    + "CameraStore xin gửi bạn chương trình khuyến mãi giảm 20% trên 1 đơn hàng."
                    + "Áp dụng cho 20 đơn hàng đầu tiên trong ngày. Hình thừ online và trực tiếp tại cửa hàng" +
                    "\n Giờ mở cửa:  Sáng: 07:30–11:00, Chiều: 13:30–16:30");

            this.mailSender.send(message);
        } else {
            log.error("Không có mail để gửi");
        }
    }
}
