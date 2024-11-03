package com.project.Recommendation_Based.Telemedicine.controller;
import com.project.Recommendation_Based.Telemedicine.Config.CustomUser;
import com.project.Recommendation_Based.Telemedicine.dto.SurveyDTO;
import com.project.Recommendation_Based.Telemedicine.entity.Appointment;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.service.ApiService;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import com.project.Recommendation_Based.Telemedicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.PatientService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

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

    @GetMapping("/api/user/current")
    @ResponseBody
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        System.out.println(("ConnectedUserEmail----------> "+principal.getName()));
        String email=principal.getName();
        User user = userService.getUserProfileByEmail(email);
        return ResponseEntity.ok(user);
    }



    @GetMapping("/")
    public String index() { return "index";}

    @GetMapping("/register")
    public String register() { return "register";}

    @GetMapping("/signin")
    public String login() { return "login";}



    @GetMapping("/admin/home")
    public String homeAd(){
        return "home";
    }
    @GetMapping("/user/home")
    public String homeUser(){
        return "home";
    }

    @GetMapping("/doctorRegisterPage")
    public String doctorRegisterPage(){
        return "doctorRegisterPage";
    }

    //AI Recommendation
    @Autowired
    private ApiService apiService;
    @Autowired
    private DoctorService doctorService;
    @GetMapping("submitSurvey")

    @ResponseBody
    public String aitest(@ModelAttribute SurveyDTO surveyDTO){
        List<Doctor> doctors= doctorService.getDoctors();
        String doctorList= doctors.toString();
        String prompt = surveyDTO.toString();
        prompt="Hey google! I'm developing a recommendation-based telemedicine app. here for the testing purpose I need doctor recommendation as close as you can depending on the given symptoms. The symptoms are: "+prompt+ " And here the Doctor list: "+doctorList+ " Now provide the correspondent doctor depending on the symptomps.";
        return apiService.generateContent(prompt);
    }
}