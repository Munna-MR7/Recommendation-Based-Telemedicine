package com.project.Recommendation_Based.Telemedicine.repository;

import com.project.Recommendation_Based.Telemedicine.entity.HealthRecord;
import com.project.Recommendation_Based.Telemedicine.entity.Patient;
import com.project.Recommendation_Based.Telemedicine.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthRecordRepo extends JpaRepository<HealthRecord, Integer> {
//    List<HealthRecord> findByUser(Patient patient);

    List<HealthRecord> findByPatient(Patient patient);

}
