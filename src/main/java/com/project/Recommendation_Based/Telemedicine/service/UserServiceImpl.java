package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        System.out.println(user.getPassword());
        String password=passwordEncoder.encode(user.getPassword());
        //System.out.println("Encoded password: " + user.getPassword());
        user.setPassword(password);
        //user.setRole("ROLE_USER");
        userRepo.save(user);

    }



    @Override
    public void removeSessionMessage() {

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }

    @Override
    public User getUserProfile(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void saveDoctor(User user) {
        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_DOCTOR");
        userRepo.save(user);
    }


}