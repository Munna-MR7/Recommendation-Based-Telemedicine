$(document).ready(function () {
    <!--        $('#pay-now').click(function () {-->
    <!--            $('#payment-modal').fadeIn();-->
    <!--        });-->

    <!--        $('.close').click(function () {-->
    <!--            $('#payment-modal').fadeOut();-->
    <!--        });-->

    <!--        $('#bkash-option').click(function () {-->
    <!--            initiatePayment();-->
    <!--        });-->

    <!--        $('#nagad-option').click(function () {-->
    <!--            initiatePayment();-->
    <!--        });-->

            $('#pay-now').click(function () {
                initiatePayment();
            });

            function initiatePayment() {
                const paymentRequest = {
                    customerName: "John Doe",
                    customerEmail: "john.doe@example.com",
                    customerPhone: "017xxxxxxxx",
                    amount: 800.00
                };

                $.ajax({
                    url: '/patient/payment/initiate',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(paymentRequest),
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