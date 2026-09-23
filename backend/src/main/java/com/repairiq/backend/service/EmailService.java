package com.repairiq.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EmailService {

    @Value("${RESEND_API_KEY}")
    private String resendApiKey;

    @Value("${RESEND_FROM_EMAIL:onboarding@resend.dev}")
    private String fromEmail;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void sendOtp(String toEmail, String otp) {

        String htmlContent =
                "<div style='font-family:Arial,sans-serif;background:#f4f7fb;padding:40px 20px;'>"
                + "<div style='max-width:600px;margin:auto;background:white;border-radius:12px;"
                + "padding:35px;text-align:center;box-shadow:0 4px 15px rgba(0,0,0,0.08);'>"

                + "<h1 style='color:#1f2937;margin-bottom:5px;'>RepairIQ</h1>"

                + "<p style='color:#6b7280;font-size:16px;'>Secure Login Verification</p>"

                + "<hr style='border:none;border-top:1px solid #e5e7eb;margin:25px 0;'>"

                + "<p style='color:#374151;font-size:16px;'>"
                + "Use the following OTP to complete your login to <b>RepairIQ</b>."
                + "</p>"

                + "<div style='margin:30px 0;background:#f0fdf4;border:1px solid #bbf7d0;"
                + "border-radius:10px;padding:20px;'>"

                + "<p style='margin:0 0 8px;color:#166534;font-size:14px;'>YOUR LOGIN OTP</p>"

                + "<div style='font-size:36px;font-weight:bold;letter-spacing:8px;color:#15803d;'>"
                + otp
                + "</div>"

                + "</div>"

                + "<p style='color:#6b7280;font-size:14px;'>"
                + "This OTP is valid for 5 minutes. Please do not share this code with anyone."
                + "</p>"

                + "<p style='margin-top:30px;color:#374151;'>"
                + "Login successfully to <b>RepairIQ</b> and continue managing your product "
                + "warranty and repairability assessment."
                + "</p>"

                + "<hr style='border:none;border-top:1px solid #e5e7eb;margin:30px 0;'>"

                + "<p style='color:#9ca3af;font-size:12px;'>"
                + "RepairIQ – AI-Powered Product Warranty & Repairability Assessment Platform"
                + "</p>"

                + "</div></div>";

        String json = "{"
                + "\"from\":\"" + escapeJson(fromEmail) + "\","
                + "\"to\":[\"" + escapeJson(toEmail) + "\"],"
                + "\"subject\":\"RepairIQ Login OTP\","
                + "\"html\":\"" + escapeJson(htmlContent) + "\""
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.resend.com/emails"))
                .header("Authorization", "Bearer " + resendApiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response =
                    httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException(
                        "Resend email failed: " + response.statusCode()
                                + " - " + response.body()
                );
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }

    private String escapeJson(String value) {
        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}