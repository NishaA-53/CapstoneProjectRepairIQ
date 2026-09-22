package com.repairiq.backend.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String toEmail, String otp) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("RepairIQ Login OTP");
            helper.setFrom("nishaarumugam73@gmail.com", "RepairIQ");

            String htmlContent =
                    "<div style='font-family:Arial,sans-serif; background:#f4f7fb; padding:40px 20px;'>"
                    + "<div style='max-width:600px; margin:auto; background:white; border-radius:12px; padding:35px; text-align:center; box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"

                    + "<h1 style='color:#1f2937; margin-bottom:5px;'>RepairIQ</h1>"
                    + "<p style='color:#6b7280; font-size:16px;'>Secure Login Verification</p>"

                    + "<hr style='border:none; border-top:1px solid #e5e7eb; margin:25px 0;'>"

                    + "<p style='color:#374151; font-size:16px;'>"
                    + "Use the following OTP to complete your login to <b>RepairIQ</b>."
                    + "</p>"

                    + "<div style='margin:30px 0; background:#f0fdf4; border:1px solid #bbf7d0; border-radius:10px; padding:20px;'>"
                    + "<p style='margin:0 0 8px; color:#166534; font-size:14px;'>YOUR LOGIN OTP</p>"
                    + "<div style='font-size:36px; font-weight:bold; letter-spacing:8px; color:#15803d;'>"
                    + otp
                    + "</div>"
                    + "</div>"

                    + "<p style='color:#6b7280; font-size:14px;'>"
                    + "This OTP is valid for 5 minutes. Please do not share this code with anyone."
                    + "</p>"

                    + "<p style='margin-top:30px; color:#374151;'>"
                    + "Login successfully to <b>RepairIQ</b> and continue managing your product warranty and repairability assessment."
                    + "</p>"

                    + "<hr style='border:none; border-top:1px solid #e5e7eb; margin:30px 0;'>"

                    + "<p style='color:#9ca3af; font-size:12px;'>"
                    + "RepairIQ – AI-Powered Product Warranty & Repairability Assessment Platform"
                    + "</p>"

                    + "</div></div>";

            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }
}