async function fetchCurrentUser() {
    try {
        const response = await fetch("/api/user/current");
        if (response.ok) {
            const connectedUser = await response.json();
            console.log("Connected User:", connectedUser);
            return connectedUser;
        } else {
            console.error("Failed to fetch user data.");
            return null;
        }
    } catch (error) {
        console.error("Error fetching user data:", error);
        return null;
    }
}

$(document).ready(function() {
    // Handle creating a new meeting
    $('#newMeetingBtn').click(async function() {
        const connectedUser = await fetchCurrentUser(); // Fetch current user information
        if (connectedUser) {
            // Get a unique room ID
            //const roomID = Math.floor(Math.random() * 10000) + "";
            const appointmentId = $(this).data('id'); // Get appointment ID from button data attribute

            // Redirect to the video call page with appointmentId, roomID, and username as URL params
            window.open(`videoCallApi.html?appointmentId=${appointmentId}&username=${connectedUser.name}`, "_blank");
        } else {
            console.error("No connected user found, cannot create meeting.");
        }
    });

    // Handle joining an existing meeting
    $('#joinMeetingBtn').click(function() {
        const appointmentId = $(this).data('id'); // Get appointment ID from button data attribute
        console.log("Patient Appointment Id---> ",appointmentId);
        fetchCurrentUser().then(function(connectedUser) {
            if (connectedUser) {
                // Fetch the existing room ID associated with the appointment ID
                $.ajax({
//                    alert("OKKKKKKKK!!!")
                    type: "GET",
                    url: `/appointments/getRoomID/`+appointmentId,
                    contentType: "application/json",
                    success: function(response) {
                        const roomID = response.roomID;
                        console.log("Patient Room Id is: ",roomID);
                        if (roomID) {
                            // Redirect to the video call page with the existing roomID, appointmentId, and username
                            window.open(`videoCallApi.html?roomID=${roomID}&appointmentId=${appointmentId}&username=${connectedUser.name}`, "_blank");
                        } else {
                        alert("You Can not join Now");
                            console.error("Room ID not found for this appointment.");
                        }
                    },
                    error: function(err) {
                        console.error("Error fetching Room ID:", err);
                    }
                });
            } else {
                console.error("No connected user found, cannot join meeting.");
            }
        }).catch(function(error) {
            console.error("Error fetching connected user:", error);
        });
    });
});














//async function fetchCurrentUser() {
//    try {
//        const response = await fetch("/api/user/current");
//        if (response.ok) {
//            const connectedUser = await response.json();
//            console.log(("ConnectedUser----------> "+connectedUser));
//            return connectedUser;
//        } else {
//            console.error("Failed to fetch user data.");
//            return null;
//        }
//    } catch (error) {
//        console.error("Error fetching user data:", error);
//        return null;
//    }
//}
//
//async function handleNewMeeting() {
//    const connectedUser = await fetchCurrentUser();
//    console.log("handleNewMeeting console is:",connectedUser);
//    if (connectedUser) {
//        window.open(`videoCallApi.html?username=${connectedUser.name}`, "_blank");
//    }
//}
//function handleJoinMeeting() {
//    const connectedUser = fetchCurrentUser();
//    console.log("handleJoinMeeting console is:", connectedUser);






//}


//
//// Attach the functions to buttons
//const newMeetingBtn = document.getElementById("newMeetingBtn");
//console.log(newMeetingBtn);
//newMeetingBtn.addEventListener("click", handleNewMeeting);
//
////const joinMeetingBtn = document.getElementById("joinMeetingBtn");
//joinMeetingBtn.addEventListener("click", handleJoinMeeting);


















//const socket = new WebSocket("ws://localhost:8080/ws");
//
//socket.addEventListener('open', () => {
//    socket.send(JSON.stringify({ type: "joinRoom", roomId: roomId }));
//});
//
//// WebRTC peer connection setup, similar to your existing code
//// Send and receive offers, answers, and ICE candidates over the WebSocket connection
//socket.addEventListener("message", (event) => {
//    const data = JSON.parse(event.data);
//
//    if (data.type === "newJoining") {
//        makeOffer();
//    } else if (data.type === "offer") {
//        handleOffer(data.offer);
//    } else if (data.type === "answer") {
//        handleAnswer(data.answer);
//    } else if (data.type === "iceCandidate") {
//        handleIceCandidate(data.candidate);
//    }
//});
//
//function makeOffer() {
//    // Create and send offer
//    // socket.send(JSON.stringify({ type: "offer", offer, roomId }));
//    const offer = await peerConnection.createOffer();
//    peerConnection.setLocalDescription(offer);
//    socket.emit('makeOffer', offer, roomId)
//}
//
//function handleOffer(offer) {
//    // Handle received offer and send answer
//}
//
//function handleAnswer(answer) {
//    // Handle received answer
//}
//
//function handleIceCandidate(candidate) {
//    // Add ICE candidate to peer connection
//}
//
//// Other WebRTC and UI handling logic
