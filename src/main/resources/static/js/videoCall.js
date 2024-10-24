const appID = 1572906346;
    const serverSecret = '3e1e0b4aa66f275c7473641033ce5ca8';
    const roomID = /*[[${roomId}]]*/ 'default_room';
    const userID = /*[[${doctorId}]]*/ 'doctor';

    let zg = new ZegoExpressEngine(appID, serverSecret);
    let localStream;

    document.getElementById('startCall').onclick = async () => {
        await zg.loginRoom(roomID, { userID: userID, userName: 'Doctor' });
        localStream = await zg.createStream();
        document.getElementById('localVideo').srcObject = localStream;
        zg.startPublishingStream(roomID, localStream);
    };

    document.getElementById('endCall').onclick = () => {
        zg.stopPublishingStream(roomID);
        zg.leaveRoom(roomID);
    };

    document.getElementById('muteButton').onclick = () => {
        const isMuted = localStream.getAudioTracks()[0].enabled;
        localStream.getAudioTracks()[0].enabled = !isMuted;
    };