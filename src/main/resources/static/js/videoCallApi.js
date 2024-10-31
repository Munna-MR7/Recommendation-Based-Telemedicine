 window.onload = function () {
      function getUrlParams(url) {
          let urlStr = url.split('?')[1];
          const urlSearchParams = new URLSearchParams(urlStr);
          const result = Object.fromEntries(urlSearchParams.entries());
          return result;
      }


          // Generate a Token by calling a method.
          // @param 1: appID
          // @param 2: serverSecret
          // @param 3: Room ID
          // @param 4: User ID
          // @param 5: Username
      const roomID = getUrlParams(window.location.href)['roomID'] || (Math.floor(Math.random() * 10000) + "");
      const userID = Math.floor(Math.random() * 10000) + "";
      const userName = "userName" + userID;
      const appID = 637155509;
      const serverSecret = "00f9e9a5b59c7568f921ba95f2a6d51d";
      const kitToken = ZegoUIKitPrebuilt.generateKitTokenForTest(appID, serverSecret, roomID, userID, userName);

      // Set the generated roomID in the hidden input
          const meetingIdInput = document.getElementById("meetingId");
          if (meetingIdInput) {
              meetingIdInput.value = roomID;  // Assign the roomID to the input field
          }

          const zp = ZegoUIKitPrebuilt.create(kitToken);
          zp.joinRoom({
              container: document.querySelector("#root"),
//              sharedLinks: [{
//                  name: 'Personal link',
//                  url: window.location.protocol + '//' + window.location.host  + window.location.pathname + '?roomID=' + roomID,
//              }],
              scenario: {
                  mode: ZegoUIKitPrebuilt.VideoConference,
              },

                 turnOnMicrophoneWhenJoining: true,
                 turnOnCameraWhenJoining: false,
                 showMyCameraToggleButton: true,
                 showMyMicrophoneToggleButton: true,
                 showAudioVideoSettingsButton: true,
                 showScreenSharingButton: true,
                 showTextChat: true,
                 showUserList: true,
                 maxUsers: 2,
                 layout: "Auto",
                 showLayoutButton: false,

              });
  }