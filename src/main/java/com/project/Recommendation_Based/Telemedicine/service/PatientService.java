package com.project.Recommendation_Based.Telemedicine.service;

//import com.opencsv.exceptions.CsvValidationException;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;

public interface PatientService {

    public Patient savePatient(Patient patient);

    public void removeSessionMessage();

    public Patient getPatientProfile(String email);

    User findByEmail(String email);
}

