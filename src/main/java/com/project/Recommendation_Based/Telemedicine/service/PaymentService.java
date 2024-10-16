package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.dto.PaymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;

public interface PaymentService {

    public String initiatePayment(PaymentRequest paymentRequest) throws IOException, InterruptedException;
    public void confirmPayment( String transactionId,String status);
}
