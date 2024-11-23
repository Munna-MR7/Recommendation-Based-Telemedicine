window.onload = function () {
    function getUrlParams(url) {
        let urlStr = url.split('?')[1];
        const urlSearchParams = new URLSearchParams(urlStr);
        const result = Object.fromEntries(urlSearchParams.entries());
        return result;
    }
    const params = getUrlParams(window.location.href);

    //for doctor
    const doctorId = params['doctorId'];
    //for user
    const appointmentId = params['appointmentId']; // Get appointmentId from URL params
    let roomID =params['roomID'];
    console.log("doctor id recieved----->",doctorId);
    console.log("doctor parameter room id---->", roomID);

    if ((appointmentId && roomID) || doctorId ) {
        alert("condition done!");
        const appID = 637155509; // Replace with your actual App ID
        const userName = params['username'];
        if(roomID==null) {

            roomID = (Math.floor(Math.random() * 10000) + "");
            console.log("Room id was null , new id is---->",roomID);
        }
        const userID = Math.floor(Math.random() * 10000) + ""; // Random user ID for demo


        const serverSecret = "00f9e9a5b59c7568f921ba95f2a6d51d"; // Replace with your actual Server Secret
        console.log("Room Id after---> ",roomID);
//        console.log("appointment Id is---> ",appointmentId);
//        console.log("User Name is---> ",userName);


        // Generate the Kit Token for Zego
        const kitToken = ZegoUIKitPrebuilt.generateKitTokenForTest(appID, serverSecret, roomID, userID, userName);

        // Save roomID and appointmentId in the database
        if (doctorId) {
            $.ajax({
    //            console.log("Ajax call okk!");
                type: "POST",
                url: `/appointments/saveRoomID`, // Use appointmentId as path variable
                contentType: "application/json",
                data: JSON.stringify({ roomID: roomID, doctorId: doctorId }),
                success: function (response) {
                    console.log("Room ID and Appointment ID saved successfully:", response);
                },
                error: function (err) {
                    console.error("Error saving Room ID and Appointment ID:", err);
                }
            });
        }

        // Initialize the Zego video call
        const zp = ZegoUIKitPrebuilt.create(kitToken);
        zp.joinRoom({
            container: document.querySelector("#root"), // Ensure you have an element with this selector
            scenario: {
                mode: ZegoUIKitPrebuilt.VideoConference,
            },
            turnOnMicrophoneWhenJoining: true, // Microphone settings
            turnOnCameraWhenJoining: true, // Camera settings
            showMyCameraToggleButton: true,
            showMyMicrophoneToggleButton: true,
            showAudioVideoSettingsButton: true,
            showScreenSharingButton: true,
            showTextChat: true,
            showUserList: true,
            maxUsers: 2, // Adjust as necessary
            layout: "Auto",
            showLayoutButton: false,
        });
    }
};
