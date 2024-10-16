package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.Prescription;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> getPatientPrescriptions(Patient patient);
}
