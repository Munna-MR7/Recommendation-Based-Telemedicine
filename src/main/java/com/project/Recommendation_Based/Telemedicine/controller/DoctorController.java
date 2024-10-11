package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class DoctorController {

    @Autowired
    DoctorService doctorService;
    @GetMapping("/user/allDoctor")
    public String getDoctors(Model model, HttpSession session){
        List<Doctor> doctors= doctorService.getDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("session", session);
        return "doctorLists";
    }
    @GetMapping("/allDoctor")
    public String getDoctor(Model model){
        List<Doctor> doctors= doctorService.getDoctors();
        model.addAttribute("doctors", doctors);
        return "doctorLists";
    }
    @PostMapping("/Appointment")
    public String doctorAppointment(@ModelAttribute Doctor doctor, Model model){
        System.out.println(doctor.getName());
        System.out.println("ok");

        //Optional<Doctor> doctor= doctorService.searchDoctor();
        model.addAttribute("doctor", doctor);
        return "doctorAppointment";
    }
}
