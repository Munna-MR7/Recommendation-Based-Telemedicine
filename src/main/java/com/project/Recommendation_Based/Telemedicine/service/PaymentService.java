package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;

public interface PaymentService {
    public Appointment getAppointmentById(Integer appointmentId);
    public String initiatePayment(Integer appointmentId, String paymentMethod);
    public void updatePaymentStatus(Integer appointmentId, String status);
}
