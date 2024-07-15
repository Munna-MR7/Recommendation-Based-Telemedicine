package com.project.Recommendation_Based.Telemedicine.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
        import java.security.Principal;
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
    @GetMapping("/admin/home")
    public String homeAd(){
        return "home";
    }
    @GetMapping("/user/home")
    public String homeUser(){
        return "home";
    }

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

        // System.out.println(user);

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
