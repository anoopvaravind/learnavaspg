var globalusers;

$(document).ready(function () {

    $("#submitButton").prop("disabled", false);

    jQuery.validator.addMethod(
            "validatelength",
            function (value, element) {
                var StringLength = element.length;
                if(StringLength < 6) {
                return false;
                }
                return true;
            },
            "Password should be atleast 6 characters long"
        );

    $("form[name='changepasswordForm']").validate({
        // Specify validation rules
        rules:{
            password:{
                required:true
             },
             newPassword:{
                 required:true,
                 minlength:6
              },
             changePassword:{
                 required:true,
                 equalTo :"#newPassword",

              }
        },
        // Specify validation error messages
         password:{
            required:"Please provide password"
        },
        newPassword:{
            required:"Please provide a new password",
            minlength:"Your new password must be at least 6 characters long"
        },
        changePassword:{
            required:"Please provide confirm password",
            equalTo:"New password and confirm password must be same"
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler:function (form) {
            $.ajax({
                type:"POST",
                url:'/expmanager/app/dochangepassword',
                data:{password:$('#password').val(), newPassword:$('#newPassword').val(), changePassword:$('#changePassword').val()},
                success:function (notification) {
                    if (notification.error) {
                        $('#errorDiv').show();
                        $('#successDiv').hide();
                        $('#error').text(notification.message);
                    } else {
                        $('#success').text(notification.message);
                        $('#successDiv').show();
                        $('#errorDiv').hide();

                        $('#password').val("");
                        $('#newPassword').val("");
                        $('#changePassword').val("");
                    }

                },
                error:function (response) {
                    $('#errorDiv').show();
                    $('#successDiv').hide();
                    $('#error').text('Error Occurred while saving data !!');
                }
            });
        }
    });

});
