$(document).ready(function() {
    $('.viewRequestedDoctorDetails-btn').click(function() {
        // Get the doctor ID from the data-id attribute
        var doctorRequestId = $(this).data('id');
        console.log(doctorRequestId);

        $.ajax({
            url: '/admin/viewRequestedDoctorDetails/' + doctorRequestId,
            type: 'GET',
            success: function(response) {
                // Replace the content of the current page with the new content
                $('body').html(response);
            },
            error: function(error) {
                alert('Failed to load details');
            }
        });
    });
});
