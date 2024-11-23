package com.project.Recommendation_Based.Telemedicine.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ZegoCloudService.class})
@ExtendWith(SpringExtension.class)
class ZegoCloudServiceDiffblueTest {
    @Autowired
    private ZegoCloudService zegoCloudService;

    /**
     * Method under test: {@link ZegoCloudService#createRoom(String)}
     */
    @Test
    void testCreateRoom() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ZegoCloudService.appId
        //     ZegoCloudService.appSign
        //     ZegoCloudService.serverSecret
        //     ZegoCloudService.serverUrl
        //     ZegoCloudService.webClient

        // Arrange and Act
        zegoCloudService.createRoom("42");
    }

    /**
     * Method under test: {@link ZegoCloudService#joinRoom(String, String)}
     */
    @Test
    void testJoinRoom() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ZegoCloudService.appId
        //     ZegoCloudService.appSign
        //     ZegoCloudService.serverSecret
        //     ZegoCloudService.serverUrl
        //     ZegoCloudService.webClient

        // Arrange and Act
        zegoCloudService.joinRoom("42", "42");
    }

    /**
     * Method under test: {@link ZegoCloudService#leaveRoom(String, String)}
     */
    @Test
    void testLeaveRoom() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ZegoCloudService.appId
        //     ZegoCloudService.appSign
        //     ZegoCloudService.serverSecret
        //     ZegoCloudService.serverUrl
        //     ZegoCloudService.webClient

        // Arrange and Act
        zegoCloudService.leaveRoom("42", "42");
    }
}
