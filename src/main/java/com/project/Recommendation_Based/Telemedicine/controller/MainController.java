package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.service.DoctorRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/admin")
public class MainController {
    @Autowired
    private DoctorRequestService doctorRequestService;
    @GetMapping("/pendingRequest")
    public String pendingRequst(Model model){

        List<DoctorRequest> pendingDoctors= doctorRequestService.showAllPendingRequest();
        model.addAttribute("pendingDoctors", pendingDoctors);
        return "pendingRequest";
    }

}
