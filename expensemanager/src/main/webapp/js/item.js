
//csrf tokens for ajax post
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ready(function(){
	$("#submitButton").prop("disabled", false);
    $( "#datepicker" ).datepicker();

    $.ajax({
        type: "GET",
        url:"/expmanager/app/category/listAll",
        dataType: "json",
        success: function (data) {
            $.each(data.data,function(value,key)
            {

                var div_data="<option value="+value+">"+key+"</option>";

                $(div_data).appendTo('#category');
            });
        },
        error:	function (data) {
            var div_data="<option value="+"1"+">"+"Error"+"</option>";
            $(div_data).appendTo('#category');
        }

    });

    $(function() {
        $("form[name='itemForm']").validate({
            // Specify validation rules			
            rules: {
                // The key name on the left side is the name attribute
                // of an input field. Validation rules are defined
                // on the right side
                itemName: "required",
                purchasedate: {
                    required: true,
                    date:true,
                },
                email: {
                    required: true,
                    // Specify that email should be validated
                    // by the built-in "email" rule
                    email: true
                },
                password: {
                    required: true,
                    minlength: 5
                }
            },
            // Specify validation error messages
            messages: {
                itemName: "Please enter Item name",
                purchasedate: {
                    required: "Please enter paid date",
                    date:"Please enter a valid date in MM/DD/YYYY format",
                },
                password: {
                    required: "Please provide a password",
                    minlength: "Your password must be at least 5 characters long"
                },
                email: "Please enter a valid email address"
            },
            // Make sure the form is submitted to the destination defined
            // in the "action" attribute of the form when valid
            submitHandler: function(form) {				
                form.submit();
            }
        });
    });



});

