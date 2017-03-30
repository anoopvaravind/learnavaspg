//csrf tokens for ajax post
var token = $("meta[name='_csrf']").attr("content");
$.ajaxSetup({
    beforeSend:function (xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN', token);
    }
});

$('#errorDiv').hide();
$('#successDiv').hide();

$(document).ready(function () {
    $("#generate").prop("disabled", false);
    jQuery.validator.addMethod(
        "validateMoney",
        function (value, element) {
            var isValidMoney = /^\d{0,4}(\.\d{0,2})?$/.test(value);
            return this.optional(element) || isValidMoney;
        },
        "Please enter a valid amount"
    );

    $("form[name='accountForm']").validate({
        // Specify validation rules
        rules:{
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            currentRentAmount:{
                required:true,
                validateMoney:true
            }
        },
        // Specify validation error messages
        messages:{
            currentRentAmount:{
                required:"Please enter rent amount"
            }
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler:function (form) {
            //e.preventDefault(); /// <--- THIS LINE
            $.ajax({
                type:"POST",
                url:'/expmanager/admin/account/generatemonthlystatement',
                data:{month:$('#month').val(), year:$('#year').val(), currentRentAmount:$('#currentRentAmount').val()},
                success:function (notification) {
                    if (notification.success == true) {
                        $('#success').text(notification.message);
                        $('#successDiv').show();
                        $('#errorDiv').hide();
                    } else {
                        $('#errorDiv').show();
                        $('#successDiv').hide();
                        $('#error').text(notification.message);
                    }

                },
                error:function (response) {
                    $('#errorDiv').show();
                    $('#successDiv').hide();
                    $('#error').text("Error occurred !!");
                }
            });
        }
    });


});

