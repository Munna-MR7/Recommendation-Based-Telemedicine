package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.DoctorRequestService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class DoctorController {
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

    @PostMapping("/saveDoctor")
    @ResponseBody
    public String saveDoctor(@ModelAttribute Doctor doctor, HttpSession session, Model m) {


        System.out.println(doctor);
        doctorService.saveDoctor(doctor);
        return "Success";
    }
    @Autowired
    DoctorService doctorService;
    @GetMapping("/patient/allDoctor")
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

    @Autowired
    private DoctorRequestService doctorRequestService;

    @PostMapping("/doctorRequest")
    @ResponseBody
    public String doctorRequest(@ModelAttribute DoctorRequest doctorRequest){
        doctorRequestService.saveDoctorRequest(doctorRequest);
        return "Request Pending";

    }

    @GetMapping("/doctorLoginPage")
    public String doctorLoginPage(){
        return "doctorLogin";
    }

}
