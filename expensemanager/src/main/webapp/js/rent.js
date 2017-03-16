
//csrf tokens for ajax post
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
$(document).ready(function(){
	$("#submit").prop("disabled", false);

	jQuery.validator.addMethod(
    "validateMoney",
    function(value, element) {
        var isValidMoney = /^\d{0,4}(\.\d{0,2})?$/.test(value);
        return this.optional(element) || isValidMoney;
    },
    "Please enter a valid amount"
	);

	$("form[name='rentForm']").validate({
		// Specify validation rules
		rules: {
			// The key name on the left side is the name attribute
			// of an input field. Validation rules are defined
			// on the right side
			totalamount:{
				required:true,
				validateMoney:true
			}
		},
		// Specify validation error messages
		messages: {
			totalamount:{
				required:"Please enter amount"
			}
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler: function(form) {
			e.preventDefault(); /// <--- THIS LINE
    $.ajax({
        type: 'POST',
        data: {name: $('#name').val()},
        url: 'signup_backend.php',
        success: function(data, textStatus, jqXHR){

        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('error');
        }
    });
		}
	});


});

