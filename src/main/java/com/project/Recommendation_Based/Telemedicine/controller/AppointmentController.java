package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/makeAppointment")
    public String submitAppointmentForm(@ModelAttribute Appointment appointment, RedirectAttributes ra, Model model) {

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

//    @PostMapping("/payLater")
//    public void payLater(){
//        appointmentService.setPaymentStatus("Unpaid");
//    }
}








