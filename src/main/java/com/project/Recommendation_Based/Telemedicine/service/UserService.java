package com.project.Recommendation_Based.Telemedicine.service;

import com.project.Recommendation_Based.Telemedicine.entity.User;

public interface UserService {

    public void saveUser(User user);

    public void removeSessionMessage();

    public User getUserProfileByEmail(String email);

}