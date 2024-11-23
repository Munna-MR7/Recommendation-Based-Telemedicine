$(document).ready(function () {
    $('.pay-now').click(function () {
        // Get appointmentId from the data-id attribute of the clicked button
        var appointmentId = $(this).data('id');
        console.log("appointmentId is ---> ", appointmentId);

        // Call initiatePayment with appointmentId as argument
        initiatePayment(appointmentId);
    });

    function initiatePayment(appointmentId) {
        $.ajax({
            url: '/patient/payment/initiate/' + appointmentId,
            type: 'POST',
            success: function (response) {
                if (response.paymentUrl) {
                    window.open(response.paymentUrl, '_blank');
                    $('#payment-modal').fadeOut();
                } else {
                    alert('Payment URL not found. Please try again.');
                }
            },
            error: function (error) {
                alert('Error initiating payment. Please try again.');
            }
        });
    }
});
