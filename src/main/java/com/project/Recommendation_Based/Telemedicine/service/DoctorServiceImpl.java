package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.dto.UserDTO;
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRepo;
<<<<<<< HEAD
=======
import com.project.Recommendation_Based.Telemedicine.repository.UserRepo;
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
<<<<<<< HEAD

@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorRepo doctorRepo;
=======
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private UserRepo userRepo;

>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
    @Override
    public List<Doctor> getDoctors() {
        return doctorRepo.findAll();
    }

<<<<<<< HEAD
=======
    public Optional<Doctor> searchDoctor(Integer id) {
        return doctorRepo.findById(id);
    }

>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Doctor saveDoctor(Doctor doctor) {

<<<<<<< HEAD
        String password=passwordEncoder.encode(doctor.getPassword());
=======
        String password = passwordEncoder.encode(doctor.getPassword());
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
        doctor.setPassword(password);
        doctor.setRole("ROLE_USER");


//		Type t = new Type();
//		t.setName("electric");
//		typeRepo.save(t);
//		Type t2 = new Type();
//		t2.setName("furniture");
//		typeRepo.save(t2);


<<<<<<< HEAD
        return userRepo.save(user);
=======
        return doctorRepo.save(doctor);
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)

    }


<<<<<<< HEAD

=======
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
    @Override
    public void removeSessionMessage() {

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }

    @Override
    public void saveStudent(UserDTO userDTO) {
<<<<<<< HEAD
        String password=passwordEncoder.encode(userDTO.getPassword());
        String email= userDTO.getEmail();
        String name= userDTO.getName();

        User user=new User();
=======
        String password = passwordEncoder.encode(userDTO.getPassword());
        String email = userDTO.getEmail();
        String name = userDTO.getName();

        User user = new User();
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRole("ROLE_USER");

        userRepo.save(user);


    }
<<<<<<< HEAD
=======

}
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
