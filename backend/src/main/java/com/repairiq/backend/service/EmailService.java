package com.repairiq.backend.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String toEmail, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("RepairIQ Login OTP");
        message.setText(
                "Hello,\n\n" +
                "Your RepairIQ login OTP is: " + otp + "\n\n" +
                "This OTP is valid for your current login session.\n\n" +
                "Regards,\n" +
                "RepairIQ Team"
        );

        mailSender.send(message);
    }
}