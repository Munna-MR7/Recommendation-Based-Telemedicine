package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.PaymentRequest;
import com.project.Recommendation_Based.Telemedicine.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/initiate")
    public ResponseEntity<Map<String, String>> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            // Initiate payment using SSLCommerz
            String paymentUrl = paymentService.initiatePayment(paymentRequest);

            // Return the payment URL
            Map<String, String> response = new HashMap<>();
            response.put("paymentUrl", paymentUrl);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/confirm")
    public String confirmPayment(@RequestParam("status") String status, @RequestParam("tran_id") String transactionId) {
        // Confirm the payment and update the status in the database
        paymentService.confirmPayment(transactionId, status);

        // Redirect to profile page after confirmation
        return "redirect:/profile";
    }
}
