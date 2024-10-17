$(document).ready(function() {
//alert("Okkkkkkkkkkkkkkkkkkkkkkk!")
    // Bind click event to delete buttons dynamically
    $('.accept-request-btn').click(function() {
        // Get the doctor ID from the data-id attribute
        var doctorRequestId = $(this).data('id');
        console.log(doctorRequestId);
        var $row = $(this).closest('tr'); // Get the row that this button is in

        // Perform the AJAX request
        $.ajax({
            url: '/admin/acceptDoctorRequest/' + doctorRequestId,
            type: 'POST',
            success: function(response) {
                // Remove the row of the deleted request
                $row.remove(); // Remove the row from the table
                //alert('Doctor request deleted successfully.');
            },
            error: function(error) {
                alert('Failed to accept request');
            }
        });
    });
});
