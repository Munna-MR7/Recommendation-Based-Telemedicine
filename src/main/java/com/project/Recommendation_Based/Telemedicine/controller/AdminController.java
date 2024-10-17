package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.DoctorRequestService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import com.project.Recommendation_Based.Telemedicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }
    }
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
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/acceptDoctorRequest/{id}")
    @ResponseBody
    public String acceptDoctorRequest(@PathVariable("id") int id){

        DoctorRequest doctorRequest = doctorRequestService.searchDoctorById(id);

        Doctor doctor = getDoctor(doctorRequest);

        doctorService.saveDoctor(doctor);

        User user=new User();
        user.setName(doctorRequest.getName());
        user.setEmail(doctorRequest.getEmail());
        user.setPassword(doctorRequest.getPassword());
        user.setRole("ROLL_DOCTOR");
        userService.saveUser(user);

        doctorRequestService.deleteDoctorRequest(id);
        return "Request Accepted";
    }

    private Doctor getDoctor(DoctorRequest doctorRequest) {
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
        return doctor;
    }

    @PostMapping("/deleteDoctorRequest/{id}")
    public ResponseEntity<String> deleteDoctorRequest(@PathVariable("id") int id) {
        try {
            doctorRequestService.deleteDoctorRequest(id); // Delete doctor request
            return ResponseEntity.ok("Doctor request deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting doctor request.");
        }
    }



    @GetMapping("/viewRequestedDoctorDetails/{id}")
    public String viewRequestedDoctorDetails(@PathVariable("id") int id, Model model){
        System.out.println("Ok it gained: "+id);
        DoctorRequest doctorRequest = doctorRequestService.searchDoctorById(id);
        model.addAttribute("doctor", doctorRequest );
        System.out.println(doctorRequest);
        return "viewRequestedDoctorDetails";
    }

}
