package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;

import java.io.IOException;

public interface PaymentService {

    public String initiatePayment(Appointment appointment) throws IOException, InterruptedException;
    public void confirmPayment( String transactionId,String status);
}
