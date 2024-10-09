package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.dto.UserDTO;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvValidationException;
//import com.sas.entity.User;
//import com.sas.entity.Type;
//import com.sas.entity.UserDOT;
//import com.sas.repository.TypeRepository;
//import com.sas.repository.UserRepository;


import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
//    @Autowired
//    private TypeRepo typeRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {

        String password=passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setRole("ROLE_USER");


//		Type t = new Type();
//		t.setName("electric");
//		typeRepo.save(t);
//		Type t2 = new Type();
//		t2.setName("furniture");
//		typeRepo.save(t2);


        return userRepo.save(user);

    }



    @Override
    public void removeSessionMessage() {

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }

    @Override
    public void saveStudent(UserDTO userDTO) {
        String password=passwordEncoder.encode(userDTO.getPassword());
        String email= userDTO.getEmail();
        String name= userDTO.getName();

        User user=new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRole("ROLE_USER");

        userRepo.save(user);



    }

    @Override
    public User getUserProfile(String email) {
        return userRepo.findByEmail(email);
    }


}
