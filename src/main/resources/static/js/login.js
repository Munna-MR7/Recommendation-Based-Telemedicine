 $(document).ready(function () {
        //alert("okkkkkkkkkkkkkkkkk");
        $("#signin").click(function (event) {
            event.preventDefault(); // Prevent the default form submission

            var form = $("#login");

            // Make the AJAX request
            $.ajax({
                type: "GET",
                url: "/signin", // Backend endpoint to save the user
                data: form.serialize(), // Serialize form data
                success: function (response) {
                    alert("Registration successful!");
                    console.log(response); // You can log or handle the server response as needed
                    window.location.href='/home'
                },
                error: function (err) {
                    alert("Error: " + JSON.stringify(err));
                }
            });
        });
    });