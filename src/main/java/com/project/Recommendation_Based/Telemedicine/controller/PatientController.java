package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;
import com.project.Recommendation_Based.Telemedicine.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.Principal;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private HealthRecordService healthRecordService;
    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private PatientRepo patientRepo;

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            Patient patient = patientRepo.findByEmail(email);
            m.addAttribute("patient", patient);
        }

    }

    @PostMapping("/savePatient")
    @ResponseBody
    public String savePatient(@ModelAttribute Patient patient, HttpSession session, Model m) {


        System.out.println(patient);
        patient.setRole("ROLE_PATIENT");

        User user=new User();
        user.setName(patient.getName());
        user.setEmail(patient.getEmail());
        user.setPassword(patient.getPassword());
        System.out.println("password: " + user.getPassword());

        user.setRole(patient.getRole());
        userService.saveUser(user);

        Patient p = patientService.savePatient(patient);

        if (p != null) {

            session.setAttribute("msg", "Register successfully");

        } else {

            session.setAttribute("msg", "Something wrong server");
        }
        return "Register successfully";
    }



    @GetMapping("/patient/patientProfile")
    public String viewProfile(Model model, Principal principal) {
        // Fetch the logged-in user's email
        String email = principal.getName();

        // Fetch the user's profile data
        Patient patient = patientService.getPatientProfile(email);

        // Add user data to the model
        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointmentService.getPatientAppointments(patient));
        model.addAttribute("healthRecords", healthRecordService.getPatientHealthRecords(patient));
        model.addAttribute("prescriptions", prescriptionService.getPatientPrescriptions(patient));

        // Return the profile view
        return "patientProfile";
    }

}
