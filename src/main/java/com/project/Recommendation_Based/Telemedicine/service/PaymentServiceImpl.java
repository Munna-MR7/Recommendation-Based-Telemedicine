package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    public Appointment getAppointmentById(Integer appointmentId) {
        return appointmentRepo.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public String initiatePayment(Integer appointmentId, String paymentMethod) {
        // Logic to initiate payment using a third-party API
        // For example, calling Bkash, Stripe, or a local payment gateway API

        // For demonstration, returning a dummy payment gateway URL
        if (paymentMethod.equals("bkash")) {
            return "https://www.bkash.com/payment";
        } else if (paymentMethod.equals("card")) {
            return "https://www.stripe.com/payment";
        }
        return "https://www.example.com/payment";
    }

    public void updatePaymentStatus(Integer appointmentId, String status) {
        Appointment appointment = getAppointmentById(appointmentId);
        appointment.setPaymentStatus(status);
        appointmentRepo.save(appointment);
    }
}
