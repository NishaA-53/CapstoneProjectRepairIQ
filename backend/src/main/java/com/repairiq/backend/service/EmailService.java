package com.repairiq.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class EmailService {

    @Value("${GOOGLE_SCRIPT_URL}")
    private String googleScriptUrl;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public void sendOtp(String toEmail, String otp) {

        String json = "{"
                + "\"to\":\"" + escapeJson(toEmail) + "\","
                + "\"otp\":\"" + escapeJson(otp) + "\""
                + "}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(googleScriptUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        try {

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            if (response.statusCode() < 200 ||
                    response.statusCode() >= 300) {

                throw new RuntimeException(
                        "Google Apps Script email failed: "
                                + response.statusCode()
                                + " - "
                                + response.body()
                );
            }

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to send OTP email",
                    e
            );
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