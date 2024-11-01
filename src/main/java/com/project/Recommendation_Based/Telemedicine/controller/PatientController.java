package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.Config.CustomUser;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
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

    @GetMapping("/Profile")
    public String viewProfile(Model model, Principal principal) {
        // Fetch the logged-in user's email
        String email = principal.getName();
        //System.out.println("User email: "+ email);

        User user = userService.getUserProfileByEmail(email);
        //System.out.println("User details: "+user);
        System.out.println("User role: "+user.getRole());
        if(Objects.equals(user.getRole(), "ROLE_PATIENT")){
            Patient patient = patientService.getPatientProfile(email);

            // Add user data to the model
            model.addAttribute("patient", patient);
            model.addAttribute("appointments", appointmentService.getPatientAppointments(patient));
            model.addAttribute("healthRecords", healthRecordService.getPatientHealthRecords(patient));
            model.addAttribute("prescriptions", prescriptionService.getPatientPrescriptions(patient));
            return "patientProfile";
        }
        else if(Objects.equals(user.getRole(), "ROLE_DOCTOR")){
            Doctor doctor = doctorService.getDoctorByEmail(email);
            model.addAttribute("doctor", doctor);
//            model.addAttribute("appointments", appointmentService.getPatientAppointments(doctor));
//            model.addAttribute("healthRecords", healthRecordService.getPatientHealthRecords(doctor));
//            model.addAttribute("prescriptions", prescriptionService.getPatientPrescriptions(doctor));
            return "doctorProfile";
        }
        else if (user.getRole().equals("ROLE_ADMIN")) {
            return "adminProfile";
        } else {
            return "index";
        }
    }

    @GetMapping("/patientPendingAppointments")
    public String pendingAppointments(Principal principal, Model model) {

        String email = principal.getName();
        Patient patient = patientService.getPatientProfile(email);
        int patientId=patient.getId();
        System.out.println("Patient Id Is: "+patientId);
        List<Appointment> appointments= appointmentService.showPendingAppointments(patientId);
        System.out.println("Patients pending Appointments-------------->"+ appointments);
        model.addAttribute("appointments", appointments);
        return "patientPendingAppointments";
    }

}
