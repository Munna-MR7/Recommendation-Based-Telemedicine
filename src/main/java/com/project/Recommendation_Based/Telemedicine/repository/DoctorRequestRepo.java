package com.project.Recommendation_Based.Telemedicine.repository;

import com.project.Recommendation_Based.Telemedicine.entity.DoctorRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRequestRepo extends JpaRepository<DoctorRequest, Integer> {

}
