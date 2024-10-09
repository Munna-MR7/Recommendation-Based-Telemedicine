package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppointmentController {

//    @GetMapping("/makeAppointment")
//    public String showAppointmentForm(Model model) {
//        // Add an empty Appointment object to the model for form binding
//        model.addAttribute("appointment", new Appointment());
//        return "appointment-form"; // Make sure this corresponds to your Thymeleaf template (appointment-form.html)
//    }

    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/makeAppointment")
    public String submitAppointmentForm(@ModelAttribute Appointment appointment, RedirectAttributes ra, Model model) {



        appointmentService.saveAppointment(appointment);

//        Appointment appointment1=new Appointment();
        model.addAttribute("appointment", appointment);

        //ra.addFlashAttribute("msg", "Congratulations! You made an appointment.");
        return "appointmentSuccess";
    }
}
