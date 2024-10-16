package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.Config.CustomUser;
import com.project.Recommendation_Based.Telemedicine.dto.PaymentRequest;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.Payment;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;
import com.project.Recommendation_Based.Telemedicine.repository.PaymentRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.PaymentService;
import com.project.Recommendation_Based.Telemedicine.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/patient/payment")
public class PaymentController {


    @Autowired
    private UserRepo userRepo;

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }

    }

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
            e.printStackTrace();  // Make sure to log the error for debugging
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Payment initiation failed"));
        }

    }

    @Autowired
    private PatientService userService;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PatientRepo patientRepo;

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmPayment(@RequestParam("status") String status, @RequestParam("tran_id") String transactionId, @RequestParam("amount") String amountSt) {

        double amount =Double.parseDouble(amountSt);// Integer.parseInt(amountSt);

        //System.out.println("Status:"+status + "\nT_Id: "+ transactionId + "\nAmount: "+ amount);

        // Create a Payment entity and set its values
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setTransactionId(transactionId);
        payment.setStatus(status);

        // Retrieve the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        User loggedInUser;
        Patient loggedInPatient;

        if (principal instanceof CustomUser) {
            // If principal is an instance of CustomUser, get the actual User entity
            loggedInUser = ((CustomUser) principal).getUser();
            loggedInPatient= patientRepo.findByEmail(loggedInUser.getEmail());

            System.out.println("Yes: Instance");
        } else if (principal instanceof String) {
            // If principal is a String (email), fetch the user from the database
            String email = (String) principal;
            loggedInPatient = patientRepo.findByEmail(email);
            System.out.println("Yes: String " + email);
        } else {
            System.out.println("None");
            // Handle case when neither CustomUser nor String (should not happen)
            throw new IllegalStateException("Unexpected authentication principal type");
        }
        // Set the user in the payment entity
        payment.setPatient(loggedInPatient);
        // Now continue saving the payment, or any other business logic
        paymentRepo.save(payment);
        return "payment Success";

    }
}

