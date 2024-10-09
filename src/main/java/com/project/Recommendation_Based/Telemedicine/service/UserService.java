package com.project.Recommendation_Based.Telemedicine.service;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

//import com.opencsv.exceptions.CsvValidationException;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import com.project.Recommendation_Based.Telemedicine.dto.UserDTO;

public interface UserService {

    public User saveUser(User user);

    public void removeSessionMessage();

    void saveStudent(UserDTO user);

    public User getUserProfile(String email);

    User findByEmail(String email);

    //public void insertuserDataFromCSV(MultipartFile file) throws IOException, CsvValidationException, NumberFormatException;

}

