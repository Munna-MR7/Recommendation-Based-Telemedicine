package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import com.project.Recommendation_Based.Telemedicine.service.HealthRecordService;
import com.project.Recommendation_Based.Telemedicine.service.PrescriptionService;
import com.project.Recommendation_Based.Telemedicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private HealthRecordService healthRecordService;
    @Autowired
    private PrescriptionService prescriptionService;
    @GetMapping("/profile")
    public String viewProfile(Model model, Principal principal) {
        // Fetch the logged-in user's email
        String email = principal.getName();

        // Fetch the user's profile data
        User user = userService.getUserProfile(email);

        // Add user data to the model
        model.addAttribute("user", user);
        model.addAttribute("appointments", appointmentService.getUserAppointments(user));
        model.addAttribute("healthRecords", healthRecordService.getUserHealthRecords(user));
        model.addAttribute("prescriptions", prescriptionService.getUserPrescriptions(user));

        // Return the profile view
        return "profile";  // This will render profile.html from the Thymeleaf template
    }


}
