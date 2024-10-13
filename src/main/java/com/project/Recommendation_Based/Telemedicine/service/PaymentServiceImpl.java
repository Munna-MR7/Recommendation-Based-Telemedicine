package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.PaymentRequest;
import com.project.Recommendation_Based.Telemedicine.repository.AppointmentRepo;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private AppointmentRepo appointmentRepo;

    // SSLCommerz Sandbox Credentials
    private final String storeId = "rmedi670acea5f07e5";
    private final String storePassword = "rmedi670acea5f07e5@ssl";
    private final String baseUrl = "http://localhost:8080/";

    public String initiatePayment(PaymentRequest paymentRequest) throws IOException, InterruptedException {

        // Set up the parameters for payment initiation
        Map<String, String> parameters = new HashMap<>();
        parameters.put("store_id", storeId);
        parameters.put("store_passwd", storePassword);
        parameters.put("total_amount", String.valueOf(paymentRequest.getAmount()));
        parameters.put("currency", "BDT");
        parameters.put("tran_id", UUID.randomUUID().toString());  // Unique transaction ID
        parameters.put("success_url", "http://localhost:8080/payment/confirm?status=success");
        parameters.put("fail_url", "http://localhost:8080/payment/confirm?status=failed");
        parameters.put("cancel_url", "http://localhost:8080/payment/confirm?status=canceled");
        parameters.put("cus_name", paymentRequest.getCustomerName());
        parameters.put("cus_email", paymentRequest.getCustomerEmail());
        parameters.put("cus_phone", paymentRequest.getCustomerPhone());

        // Make an HTTP call to SSLCommerz for payment initiation
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "gwprocess/v4/api.php"))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(getParamsString(parameters)))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse response JSON to extract the payment URL
        JSONObject jsonResponse = new JSONObject(response.body());
        return jsonResponse.getString("GatewayPageURL");
    }

    public void confirmPayment(String transactionId, String status) {
        // Confirm the payment status and update your DB accordingly (implementation as needed)
        System.out.println("Transaction ID: " + transactionId + ", Status: " + status);
    }

    private String getParamsString(Map<String, String> params) {
        return params.entrySet()
                .stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }
}
