package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.Config.CustomUser;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;
import com.project.Recommendation_Based.Telemedicine.repository.PatientRepo;
import com.project.Recommendation_Based.Telemedicine.repository.PaymentRepo;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Controller

public class AppointmentController {


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
    private AppointmentService appointmentService;

    @PostMapping("/Appointment")
    public String doctorAppointment(@ModelAttribute Doctor doctor, Model model){

        System.out.println(doctor.getName());
        System.out.println("ok");

        //Optional<Doctor> doctor= doctorService.searchDoctor();
        model.addAttribute("doctor", doctor);
        return "doctorAppointment";
    }

    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private DoctorRepo doctorRepo;
    @PostMapping("/makeAppointment")
    public String submitAppointmentForm(@ModelAttribute Appointment appointment, RedirectAttributes ra, Model model,@RequestParam("doctorId") int doctorId ) {

        //System.out.println("++++++++++++++++++++++++++>>>>>>>>>>>"+doctorId);
        Optional<Doctor> doctorOptional=doctorRepo.findById(doctorId);
        if (doctorOptional.isPresent()) {
        Doctor doc = doctorOptional.get();
        appointment.setDoctor(doc);}


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        User loggedInUser;
        Patient loggedInPatient;

        if (principal instanceof CustomUser) {
            // If principal is an instance of CustomUser, get the actual User entity
            loggedInUser = ((CustomUser) principal).getUser();
            loggedInPatient= patientRepo.findByEmail(loggedInUser.getEmail());

            System.out.println("Yes: Instance");
        } else if (principal instanceof String) {
            // If principal is a String (email), fetch the user from the database
            String email = (String) principal;
            loggedInPatient = patientRepo.findByEmail(email);
            System.out.println("Yes: String " + email);
        } else {
            System.out.println("None");
            // Handle case when neither CustomUser nor String (should not happen)
            throw new IllegalStateException("Unexpected authentication principal type");
        }
        // Set the user in the payment entity
        appointment.setPatient(loggedInPatient);
        appointment.setVisitStatus("Unvisited");
        appointment.setPaymentStatus("Unpaid");
        // After finding the doctor entity
       // Optional<Doctor> doctor = doctorRepo.findById(appointment.getDoctorId());
       //doctor.ifPresent(appointment::setDoctor);

        appointmentService.saveAppointment(appointment);

        model.addAttribute("appointment", appointment);
        //ra.addFlashAttribute("msg", "Congratulations! You made an appointment.");
        return "appointmentSuccess";
    }


    // Endpoint to download the PDF of the appointment receipt
    @GetMapping("/appointment/download/{id}")
    public ResponseEntity<InputStreamResource> downloadAppointmentReceipt(@PathVariable Integer id) throws DocumentException, IOException {

        // Fetch appointment details from the service layer
        Appointment appointment = appointmentService.getAppointmentById(id);

        // Generate the PDF using the service layer
        ByteArrayInputStream bis = appointmentService.generateAppointmentReceipt(appointment);

        // Set HTTP headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=appointment_" + appointment.getPatientName() + ".pdf");
//        if(appointment.getPaymentStatus()== null){
//            appointment.="Unpaid";
//        }


        // Return the PDF file as a ResponseEntity
        return ResponseEntity.ok()
                .headers(headers) //the headers description of pdf file
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    @GetMapping("/payNow")
    public String payNow(){
        return "Payment";
    }


    @PostMapping("/appointments/saveRoomID")
    public ResponseEntity<?> saveRoomID(@RequestBody Map<String, String> requestData) {
        String roomID = requestData.get("roomID");

        int doctorId = Integer.parseInt(requestData.get("doctorId"));
        appointmentService.saveRoomID(roomID, doctorId);
        System.out.println("Room ID Saved---->"+roomID+ " --------For doctor id: "+doctorId);
        return ResponseEntity.ok("Room ID saved successfully");
    }


    @GetMapping("/appointments/getRoomID/{appointmentId}")
    public ResponseEntity<Map<String, String>> getRoomID(@PathVariable Integer appointmentId) {
        // Fetch the room ID for the appointment
        String roomID = appointmentService.getRoomID(appointmentId);
        Map<String, String> response = new HashMap<>();
        response.put("roomID", roomID);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/payLater")
//    public void payLater(){
//        appointmentService.setPaymentStatus("Unpaid");
//    }
}








