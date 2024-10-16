package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;

import java.util.List;

public interface DoctorRequestService {
    void saveDoctorRequest(DoctorRequest doctorRequest);
    List<DoctorRequest> showAllPendingRequest();

    DoctorRequest searchDoctorById(Integer id);

    void deleteDoctorRequest(Integer id);

}
