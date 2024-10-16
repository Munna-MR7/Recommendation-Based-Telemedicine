package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import com.project.Recommendation_Based.Telemedicine.repository.DoctorRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorRequestServiceImpl implements DoctorRequestService{
    @Autowired
    private DoctorRequestRepo doctorRequestRepo;
    @Override
    public void saveDoctorRequest(DoctorRequest doctorRequest) {
        doctorRequestRepo.save(doctorRequest);
    }

    @Override
    public List<DoctorRequest> showAllPendingRequest() {
        return doctorRequestRepo.findAll();
    }

    @Override
    public DoctorRequest searchDoctorById(Integer id) {
        return doctorRequestRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteDoctorRequest(Integer id) {
        doctorRequestRepo.deleteById(id);
    }
}
