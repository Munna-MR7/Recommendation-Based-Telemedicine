package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.Config.CustomUser;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VideoCallController {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/videoCall")
    public String videoCall(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        User loggedInUser;
        Doctor loggedInDoctor;

        if (principal instanceof CustomUser) {
            // If principal is an instance of CustomUser, get the actual User entity
            loggedInUser = ((CustomUser) principal).getUser();
            loggedInDoctor= doctorRepo.findByEmail(loggedInUser.getEmail());

            System.out.println("Yes: Instance");
        }
        else if (principal instanceof String) {
            // If principal is a String (email), fetch the user from the database
            String email = (String) principal;
            loggedInDoctor = doctorRepo.findByEmail(email);
            System.out.println("Yes: String " + email);
        } else {
            System.out.println("None");
            // Handle case when neither CustomUser nor String (should not happen)
            throw new IllegalStateException("Unexpected authentication principal type");
        }
        model.addAttribute("doctor", loggedInDoctor);


        int doctorId=loggedInDoctor.getId();
        System.out.println("Doctor Is: "+doctorId);
        List<Appointment> appointments= appointmentService.showPendingPatients(doctorId);
        model.addAttribute("appointments", appointments);
        return "videoCall";
    }
}
