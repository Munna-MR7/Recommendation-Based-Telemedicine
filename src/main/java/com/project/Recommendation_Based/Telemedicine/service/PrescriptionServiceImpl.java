package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.Prescription;
import com.project.Recommendation_Based.Telemedicine.repository.PrescriptionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
    @Autowired
    private PrescriptionRepo prescriptionRepo;
    @Override
    public List<Prescription> getPatientPrescriptions(Patient patient) {
        return prescriptionRepo.findByPatient(patient);
    }
}
