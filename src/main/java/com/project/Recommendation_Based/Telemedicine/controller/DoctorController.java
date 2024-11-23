package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.Config.CustomUser;
import com.project.Recommendation_Based.Telemedicine.entity.*;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorRequestService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/admin/{id}/documents")
    public ResponseEntity<Resource> getDocument(@PathVariable int id) {
        Doctor doctor = doctorService.searchDoctor(id);
        String documentPath = doctor.getDocuments();

        // Load the document as a Resource
        Resource resource = (Resource) new FileSystemResource(documentPath);

        // Set the response headers and return the resource
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.name() + "\"")
                .body(resource);
    }

    @GetMapping("/doctorLoginPage")
    public String doctorLoginPage(){
        return "doctorLogin";
    }

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorRepo doctorRepo;
    @GetMapping("/doctor/pendingPatients")
    public String pendingAppointments(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        User loggedInUser;
        Doctor loggedInDoctor;

        if (principal instanceof CustomUser) {
            // If principal is an instance of CustomUser, get the actual User entity
            loggedInUser = ((CustomUser) principal).getUser();
            loggedInDoctor= doctorRepo.findByEmail(loggedInUser.getEmail());

            System.out.println("Yes: Instance");
        } else if (principal instanceof String) {
            // If principal is a String (email), fetch the user from the database
            String email = (String) principal;
            loggedInDoctor = doctorRepo.findByEmail(email);
            System.out.println("Yes: String " + email);
        } else {
            System.out.println("None");
            // Handle case when neither CustomUser nor String (should not happen)
            throw new IllegalStateException("Unexpected authentication principal type");
        }
        model.addAttribute("doctor", loggedInDoctor.getId());


        int doctorId=loggedInDoctor.getId();
        //System.out.println("Doctor Is: "+doctorId);
        List<Appointment> appointments= appointmentService.showPendingPatients(doctorId);
        model.addAttribute("appointments", appointments);
        return "pendingAppointments";
    }
    @PostMapping("/markVisited/{appointmentId}")
    public ResponseEntity<?> markVisited(@PathVariable("appointmentId") Integer appointmentId){
        System.out.println("Id fot mark visited is: "+appointmentId);
        appointmentService.patientMarkVisited(appointmentId);
        return ResponseEntity.ok("Patient marked visited!");
    }

}
