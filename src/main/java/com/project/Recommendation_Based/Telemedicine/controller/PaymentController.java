package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.dto.PaymentRequest;
import com.project.Recommendation_Based.Telemedicine.entity.Payment;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.PaymentRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.PaymentService;
import com.project.Recommendation_Based.Telemedicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/payment")
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
    private UserService userService;
    @Autowired
    private PaymentRepo paymentRepo;

    @PostMapping("/confirm")
    @ResponseBody
    public String confirmPayment(@RequestParam("status") String status, @RequestParam("tran_id") String transactionId, @RequestParam("amount") String amountSt) {

        double amount =Double.parseDouble(amountSt);// Integer.parseInt(amountSt);
        //System.out.println("Status:"+status + "\nT_Id: "+ transactionId + "\nAmount: "+ amount);
         //Retrieve the payment information (you can also retrieve the amount and other details from the session)
          // Replace with the actual amount

        // Create a Payment entity and set its values
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setTransactionId(transactionId);
        payment.setStatus(status);

        // Assuming you have a logged-in user
        // You can retrieve the User from the session or authentication context
        // payment.setUser(loggedInUser);

        // Save the payment details in the database
        paymentRepo.save(payment);

        // Confirm the payment in the payment service
        //paymentService.confirmPayment(transactionId, status);

        // Redirect to profile page after confirmation

        return "payment Success";

    }
}

