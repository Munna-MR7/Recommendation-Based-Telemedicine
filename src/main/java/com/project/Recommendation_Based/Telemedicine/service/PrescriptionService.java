package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.Prescription;
import com.project.Recommendation_Based.Telemedicine.entity.User;

import java.util.List;

public interface PrescriptionService {
    List<Prescription> getUserPrescriptions(User user);
}
