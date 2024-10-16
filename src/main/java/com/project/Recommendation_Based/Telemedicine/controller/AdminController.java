package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.service.DoctorRequestService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import com.project.Recommendation_Based.Telemedicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private DoctorRequestService doctorRequestService;
    @GetMapping("/pendingRequest")
    public String pendingRequst(Model model){

        List<DoctorRequest> pendingDoctors= doctorRequestService.showAllPendingRequest();
        model.addAttribute("pendingDoctors", pendingDoctors);
        return "pendingRequest";
    }
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private UserService userService;
    @PostMapping("/acceptDoctorRequest/{id}")
    public String acceptDoctorRequest(@PathVariable("id") int id){

        DoctorRequest doctorRequest = doctorRequestService.searchDoctorById(id);

        Doctor doctor=new Doctor();
        doctor.setName(doctorRequest.getName());
        doctor.setEmail(doctorRequest.getEmail());
        doctor.setPhone(doctorRequest.getPhone());
        doctor.setPassword(doctorRequest.getPassword());
        doctor.setGender(doctorRequest.getGender());
        doctor.setAddress(doctorRequest.getAddress());
        doctor.setDegrees(doctorRequest.getDegrees());
        doctor.setSpecialist(doctorRequest.getSpecialist());
        doctor.setDocuments(doctorRequest.getDocuments());
        doctor.setConsultationFee(doctorRequest.getConsultationFee());

        doctorService.saveDoctor(doctor);

        User user=new User();
        user.setName(doctorRequest.getName());
        user.setEmail(doctorRequest.getEmail());
        user.setPassword(doctorRequest.getPassword());
        user.setRole("ROLL_DOCTOR");
        userService.saveUser(user);

        doctorRequestService.deleteDoctorRequest(id);
        return "pendingRequest";
    }

    @PostMapping("/deleteDoctorRequest/{id}")
    public String deleteDoctorRequest(@PathVariable("id") int id){
        doctorRequestService.deleteDoctorRequest(id);
        return "pendingRequest";
    }
    @GetMapping("/viewRequestedDoctorDetails/{id}")
    public String viewRequestedDoctorDetails(@PathVariable("id") int id, Model model){
        DoctorRequest doctorRequest = doctorRequestService.searchDoctorById(id);
        model.addAttribute("doctor", doctorRequest );
        return "viewRequestedDoctorDetails";
    }

}
