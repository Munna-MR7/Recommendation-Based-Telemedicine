package com.project.Recommendation_Based.Telemedicine.service;

<<<<<<< HEAD
=======
import com.project.Recommendation_Based.Telemedicine.dto.UserDTO;
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
import com.project.Recommendation_Based.Telemedicine.entity.Doctor;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
<<<<<<< HEAD

=======
import java.util.Optional;
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)


public interface DoctorService {
    List<Doctor> getDoctors();
<<<<<<< HEAD
    public Doctor saveDoctor(Doctor doctor);

    Doctor saveDoctor(User user);
=======
    Optional<Doctor> searchDoctor(Integer id);

    public Doctor saveDoctor(Doctor doctor);
    public void removeSessionMessage();

    void saveStudent(UserDTO user);

//    Doctor saveDoctor(User user);
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
}
