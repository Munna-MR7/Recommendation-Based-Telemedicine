package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/initiatePayment/{appointmentId}")
    public String showPaymentPage(@PathVariable Integer appointmentId, Model model) {
        Appointment appointment = paymentService.getAppointmentById(appointmentId);
        model.addAttribute("appointmentId", appointmentId);
        model.addAttribute("amount", appointment.getConsultationFee());
        return "payment";
    }

    @PostMapping("/process")
    @ResponseBody
    public ResponseEntity<Map<String, String>> processPayment(@RequestBody Map<String, String> paymentRequest) {
        String paymentMethod = paymentRequest.get("method");
        Integer appointmentId = Integer.parseInt(paymentRequest.get("appointmentId"));

        // Generate the payment URL based on the payment method (via payment service)
        String paymentUrl = paymentService.initiatePayment(appointmentId, paymentMethod);

        // Return the payment URL as a JSON response
        Map<String, String> response = new HashMap<>();
        response.put("paymentUrl", paymentUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/confirmPayment")
    public String confirmPayment(@RequestParam("status") String status, @RequestParam("appointmentId") Integer appointmentId) {
        // Update the appointment's payment status based on the gateway response
        paymentService.updatePaymentStatus(appointmentId, status.equals("success") ? "Paid" : "Unpaid");

        // Redirect to a success or failure page
        return status.equals("success") ? "redirect:/profile" : "redirect:/paymentFailed";
    }
}
