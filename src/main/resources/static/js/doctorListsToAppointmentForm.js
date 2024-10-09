//$(document).ready(function () {
//    // Trigger when any appointment button is clicked
//    $(document).on('click', '.btn-appointment', function (event) {
//        event.preventDefault(); // Prevent the default form submission
//
//        // Find the closest `.card-body` and get the relevant data
//        var cardBody = $(this).closest('.card-body');
//        var doctorData = {
//            name: cardBody.find('h4[name="name"]').text(),
//            degrees: cardBody.find('h6[name="degrees"]').text(),
//            designation: cardBody.find('p[name="designation"]').text(),
//            consultationFee: cardBody.find('h6[name="consultationFee"]').text().replace('[Consultation Fee: ]', '')
//        };
//
//        // Make the AJAX request
//        $.ajax({
//            type: "POST",
//            url: "/Appointment", // Backend endpoint to handle the doctor appointment
//            contentType: "application/json", // Send data as JSON
//            data: JSON.stringify(doctorData), // Serialize the doctor data to JSON
//            success: function (response) {
//                //alert("Sure! Make an Appointment?");
//                console.log(response);
//                // Redirect to another page or update the view as needed
//                window.location.href = '/doctorAppointment';
//            },
//            error: function (error) {
//                console.log("Error: ", error);
//                alert("Error making appointment.");
//            }
//        });
//    });
//});
