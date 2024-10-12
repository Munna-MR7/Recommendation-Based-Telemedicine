package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itextpdf.text.DocumentException;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;



@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
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

//    @PostMapping("/payLater")
//    public void payLater(){
//        appointmentService.setPaymentStatus("Unpaid");
//    }
}








