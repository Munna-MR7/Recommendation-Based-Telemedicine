package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.PaymentRequest;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Optional;

public interface PaymentService {

    public String initiatePayment(PaymentRequest paymentRequest) throws IOException, InterruptedException;
    public void confirmPayment(@RequestParam("status") String status, @RequestParam("tran_id") String transactionId);
}
