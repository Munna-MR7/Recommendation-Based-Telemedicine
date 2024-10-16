package com.project.Recommendation_Based.Telemedicine.service;

//import com.opencsv.exceptions.CsvValidationException;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
public interface PatientService {

    public Patient savePatient(Patient patient);

    public void removeSessionMessage();

    public Patient getPatientProfile(String email);

    Patient findByEmail(String email);
}

