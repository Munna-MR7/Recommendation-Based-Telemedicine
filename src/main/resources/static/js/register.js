 $(document).ready(function () {
        //alert("okkkkkkkkkkkkkkkkk");

        $("#savePatient").click(function (event) { //button's id // the id of which button is clicked
            event.preventDefault(); // Prevent the default form submission

            var form = $("#register"); //form's Id // from which FORM data is collected
                //console.log(form);
            // Make the AJAX request
            $.ajax({
                type: "POST",
                url: "/savePatient", // Backend endpoint to save the user
                data: form.serialize(), // Serialize form data
                success: function (response) {
                    alert("Registration successful!");
                    console.log(response); // You can log or handle the server response as needed
                    window.location.href='/'
                },
                error: function (err) {
                    alert("Error: " + JSON.stringify(err));
                }
            });
        });

        $("#saveDoctor").click(function (event) { //button's id // the id of which button is clicked
                    event.preventDefault(); // Prevent the default form submission

                    var form = $("#register"); //form's Id // from which FORM data is collected
                        //console.log(form);
                    // Make the AJAX request
                    $.ajax({
                        type: "POST",
                        url: "/saveDoctor", // Backend endpoint to save the user
                        data: form.serialize(), // Serialize form data
                        success: function (response) {
                            //alert("Registration successful!");
                            console.log(response); // You can log or handle the server response as needed
                            window.location.href='/'
                        },
                        error: function (err) {
                            alert("Error: " + JSON.stringify(err));
                        }
                    });
                });
    });