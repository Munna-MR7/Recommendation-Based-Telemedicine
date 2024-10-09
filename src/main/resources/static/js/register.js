 $(document).ready(function () {
        //alert("okkkkkkkkkkkkkkkkk");
<<<<<<< HEAD
        $("#saveUser").click(function (event) {
            event.preventDefault(); // Prevent the default form submission

            var form = $("#register");

            // Make the AJAX request
            $.ajax({
                type: "POST",
                url: "/save", // Backend endpoint to save the user
                data: form.serialize(), // Serialize form data
                success: function (response) {
                    alert("Registration successful!");
=======
        $("#saveUser").click(function (event) { //button's id // the id of which button is clicked
            event.preventDefault(); // Prevent the default form submission

            var form = $("#register"); //form's Id // from which FORM data is collected
                //console.log(form);
            // Make the AJAX request
            $.ajax({
                type: "POST",
                url: "/saveUser", // Backend endpoint to save the user
                data: form.serialize(), // Serialize form data
                success: function (response) {
                    //alert("Registration successful!");
>>>>>>> 4f1b50b (Appointment form created and download appointment reciept)
                    console.log(response); // You can log or handle the server response as needed
                    window.location.href='/'
                },
                error: function (err) {
                    alert("Error: " + JSON.stringify(err));
                }
            });
        });
    });