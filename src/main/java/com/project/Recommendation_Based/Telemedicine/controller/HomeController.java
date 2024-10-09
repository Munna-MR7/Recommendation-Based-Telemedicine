package com.project.Recommendation_Based.Telemedicine.controller;

import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;
import com.project.Recommendation_Based.Telemedicine.service.DoctorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.Recommendation_Based.Telemedicine.entity.User;
import  com.project.Recommendation_Based.Telemedicine.dto.UserDTO;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import com.project.Recommendation_Based.Telemedicine.service.UserService;
import jakarta.servlet.http.HttpSession;

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

    @GetMapping("/")
    public String index() { return "index";}

    @GetMapping("/register")
    public String register() { return "register";}

    @GetMapping("/signin")
    public String login() { return "login";}
<<<<<<< HEAD
=======


>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
    @GetMapping("/admin/home")
    public String homeAd(){
        return "home";
    }
    @GetMapping("/user/home")
    public String homeUser(){
        return "home";
    }
<<<<<<< HEAD
=======
//    @GetMapping("/doctorLists")
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)

    /*
     * @GetMapping("/user/profile") public String profile(Principal p, Model m) {
     * String email = p.getName(); User user = userRepo.findByEmail(email);
     * m.addAttribute("user", user); return "profile"; }
     *
     * @GetMapping("/user/home") public String home() { return "home"; }
     */

    @PostMapping("/saveUser")
    @ResponseBody
    public String saveUser(@ModelAttribute User user, HttpSession session, Model m) {

<<<<<<< HEAD
        // System.out.println(user);
=======
         System.out.println(user);
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)

        User u = userService.saveUser(user);

        if (u != null) {

            session.setAttribute("msg", "Register successfully");

        } else {

            session.setAttribute("msg", "Something wrong server");
        }
        return "Register successfully";
    }
    @PostMapping("/admin/saveUser")
    @ResponseBody
    public Void saveStudent(UserDTO user) {

        System.out.println(user);

        userService.saveStudent(user);
        return null;


    }

<<<<<<< HEAD
    @GetMapping("/allDoctors")
    public String allDoctor(){

    }

    @Autowired
    private DoctorService doctorService;
    @GetMapping("/doctor/all")
    public List<Doctor> getDoctor() {
        return (List<Doctor>) doctorService.getDoctors();
    }
=======

//    @Autowired
//    private DoctorService doctorService;
//    @GetMapping("/user/doctorLists")
//    public List<Doctor> getDoctor() {
//        return (List<Doctor>) doctorService.getDoctors();
//    }


>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)

//    @GetMapping("/student/{id}")
//    public Optional<Doctor> getStudent(@PathVariable("id") int id) {
//        return DoctorService.findById(id);
//    }
//    @PostMapping("/admin/insert_CSV_user")
//    @ResponseBody
//    public String uploaduserCSVFile(@RequestParam("file") MultipartFile file) {
//        try {
//            userService.insertuserDataFromCSV(file);
//            return "save";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }

//	@PostMapping("/start")
//    public ResponseEntity<String> handleStartPost(@RequestBody YourRequestObject request) {
//        // Your logic here
//        return ResponseEntity.ok("Process started successfully");
//    }
}