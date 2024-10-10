package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.HealthRecord;
import com.project.Recommendation_Based.Telemedicine.entity.User;

import java.util.List;

public interface HealthRecordService {
    List<HealthRecord> getUserHealthRecords(User user);

}
