 $(document).ready(function () {
        alert("okkkkkkkkkkkkkkkkk");

        $(".mark-visited-btn").click(function (event) {
            event.preventDefault(); // Prevent the default form submission

            const appointmentId= $(this).data('id');
            $.ajax({
                type: "POST",
                url: "/markVisited/"+appointmentId,
                success: function (response) {
                    alert("successful!");
                    console.log(response);
                },
                error: function (err) {
                    alert("Error: " + JSON.stringify(err));
                }
            });
        });
});