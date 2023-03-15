package com.example.phuonglth_sprint_2.service.send_mail;

import com.example.phuonglth_sprint_2.common.MyConstants;
import com.example.phuonglth_sprint_2.dto.customer.CustomerDto;
import com.example.phuonglth_sprint_2.dto.product.CartTotalPrice;
import com.example.phuonglth_sprint_2.dto.product.GetInfoSendMail;
import com.example.phuonglth_sprint_2.dto.product.OrderDto;
import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.entity.product.Product;
import com.example.phuonglth_sprint_2.service.customer.ICustomerService;
import com.example.phuonglth_sprint_2.service.product.IOrderDetailService;
import com.example.phuonglth_sprint_2.service.product.IOrderService;
import com.example.phuonglth_sprint_2.service.product.IProductService;
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
import java.util.List;
import java.util.Optional;

@Service
public class SendMail {
    private static final Logger logger = LoggerFactory.getLogger(SendMail.class);
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    ICustomerService customerService;

    @Autowired
    IProductService productService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Async
    public void SendEmailToCustomer(CustomerDto customerDto) {
        try {
            //        SimpleMailMessage message = new SimpleMailMessage();
            Thread.sleep(1500L);
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
    public void SendEmailToAdmin(OrderDto orderDto, Order order) {
        List<GetInfoSendMail> getInfoSendMails = orderService.getInfoSendMail(order.getCustomer().getIdCustomer());
        GetInfoSendMail[] arr = getInfoSendMails.toArray(new GetInfoSendMail[0]);
        CartTotalPrice cartTotalPrice = orderDetailService.getCartTotalPriceOb(order.getCustomer().getIdCustomer());
        String isPaymentStatus;
        if (order.isPaymentStatus()) {
            isPaymentStatus = "Đã thanh toán";
        } else {
            isPaymentStatus = "Chưa thanh toán";
        }
        try {
            Thread.sleep(1500L);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setFrom(MyConstants.MY_EMAIL);
            helper.setTo(MyConstants.MY_EMAIL);
            String mailSubject = MyConstants.order;

            String mailContent = "<a><b>Khách hàng tên: </b>" + order.getName() + "</a>";
            mailContent += "<p><b>Số điện thoại:</b> " + order.getPhone() + "</p>";
            mailContent += "<p><b>Địa chỉ giao hàng:</b> " + order.getAddress() + "</p>";
            mailContent += "<hr>";
            for (int i = 0; i < arr.length; i++) {
                mailContent += "<img style=\"width: 150px;height: 150px\" src = " + arr[i].getUrl() + "/>";
                mailContent += "<p><b>Tên sản phẩm: <b>" + arr[i].getNameProduct() + "</p>";
                mailContent += "<p><b>Số lượng: <b>" + arr[i].getQuantityOrder() + "</p>";
                mailContent += "<p><b>Đơn giá: <b>" + arr[i].getPrice() + "</p>";
                mailContent += "<hr>";
            }
            mailContent += "<h3> Tình trạng thanh toán : " + isPaymentStatus + "</h3>";
            mailContent += "<hr>";
            mailContent += "<p><b> Tổng cộng: </b>" + cartTotalPrice.getCartTotalPrice() + " (đ)" + "</p>";
            helper.setSubject(mailSubject);
            helper.setText(mailContent, true);
            mailSender.send(message);
        } catch (MessagingException | IllegalArgumentException | InterruptedException e) {
            logger.error(e.getMessage());
        }

    }

    @Async
    public void SendEmailToCustomerOrder(OrderDto orderDto, Order order) {
        List<GetInfoSendMail> getInfoSendMails = orderService.getInfoSendMail(order.getCustomer().getIdCustomer());
        GetInfoSendMail[] arr = getInfoSendMails.toArray(new GetInfoSendMail[0]);
        CartTotalPrice cartTotalPrice = orderDetailService.getCartTotalPriceOb(order.getCustomer().getIdCustomer());
        String isPaymentStatus;
        if (order.isPaymentStatus()) {
            isPaymentStatus = "Đã thanh toán";
        } else {
            isPaymentStatus = "Chưa thanh toán";
        }
        try {
            Thread.sleep(3000L);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            helper.setFrom(MyConstants.MY_EMAIL);
            helper.setTo(orderDto.getEmail());
            String mailSubject = MyConstants.mailToCustomerOrder;

            String mailContent = "<h1>Camera Store Xin Chào Quí Khách</h1>";
            mailContent += "<h3> Thông báo đặt hàng thành công!</h3>";
            mailContent += "<hr>";
            mailContent += "<p><b>Thông tin đơn hàng</b></p>";
            for (int i = 0; i < arr.length; i++) {
                mailContent += "<img style=\"width: 150px;height: 150px\" src = " + arr[i].getUrl() + "/>";
                mailContent += "<p><b>Tên sản phẩm: <b>" + arr[i].getNameProduct() + "</p>";
                mailContent += "<p><b>Số lượng: <b>" + arr[i].getQuantityOrder() + "</p>";
                mailContent += "<p><b>Đơn giá: <b>" + arr[i].getPrice() + "</p>";
                mailContent += "<hr>";
            }
            mailContent += "<h3>Thông tin khách hàng</h3>";
            mailContent += "<p><b>Tên khách hàng:</b> " + order.getName() + "</p>";
            mailContent += "<p><b>Địa chỉ giao hàng:</b> " + order.getAddress() + "</p>";
            mailContent += "<p><b>Số điện thoại nhận hàng:</b> " + order.getPhone() + "</p>";
            mailContent += "<hr>";
            mailContent += "<h3> Tình trạng thanh toán : " + isPaymentStatus + "</h3>";
            mailContent += "<hr>";
            mailContent += "<h4> Tổng cộng:" + cartTotalPrice.getCartTotalPrice() + " (đ)" + "</h4>";
            helper.setSubject(mailSubject);
            helper.setText(mailContent, true);
            mailSender.send(message);
        } catch (MessagingException | IllegalArgumentException | InterruptedException e) {
            logger.error(e.getMessage());
        }

    }
}
