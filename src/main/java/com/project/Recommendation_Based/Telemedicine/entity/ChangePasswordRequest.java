package com.project.Recommendation_Based.Telemedicine.entity;

import jakarta.persistence.Entity;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ChangePasswordRequest {

	private int id;
	private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
