package com.project.Recommendation_Based.Telemedicine.repository;

import com.project.Recommendation_Based.Telemedicine.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
