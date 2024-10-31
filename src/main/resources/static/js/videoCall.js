async function fetchCurrentUser() {
    try {
        const response = await fetch("/api/user/current");
        if (response.ok) {
            const connectedUser = await response.json();
            console.log(("ConnectedUser----------> "+connectedUser));
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

async function handleNewMeeting() {
    const connectedUser = await fetchCurrentUser();
    console.log("console is:",connectedUser);
    if (connectedUser) {
        window.open(`videoCallApi.html?username=${connectedUser.name}`, "_blank");
    }
}

async function handleJoinMeeting() {
    const connectedUser = await fetchCurrentUser();

    const roomId = document.getElementById("meetingId").value;
    if (connectedUser) {
        const url = `videoCallApi.html?roomID=${roomId}&username=${connectedUser.name}`;
        window.open(url, "_blank");
    }
}

// Attach the functions to buttons
const newMeetingBtn = document.getElementById("newMeetingBtn");
console.log(newMeetingBtn);
newMeetingBtn.addEventListener("click", handleNewMeeting);

const joinMeetingBtn = document.getElementById("joinMeetingBtn");
joinMeetingBtn.addEventListener("click", handleJoinMeeting);


















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
