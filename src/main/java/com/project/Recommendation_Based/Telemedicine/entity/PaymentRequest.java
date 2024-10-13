package com.project.Recommendation_Based.Telemedicine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentRequest {

    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private double amount;
}
