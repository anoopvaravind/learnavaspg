//csrf tokens for ajax post
var token = $("meta[name='_csrf']").attr("content");
$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN', token);
    }
});
$('#errorDiv').hide();
$('#successDiv').hide();

var months = [ "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December" ];

var latestRentSheet;

$(document).ready(function(){

    $("#submit").prop("disabled", false);

    populateRentTable();

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

            latestRentSheet.rentActullyPaid = $('#totalamount').val();
            alert(JSON.stringify(latestRentSheet));
            $.ajax({
                type: "POST",
                url: "/expmanager/app/rent/pay",
                data: JSON.stringify(latestRentSheet) ,
                //data: {rentSheet: latestRentSheet},
                contentType: "application/json; charset=utf-8",
               dataType   : "json",
                success:  function(notification) {
                    alert("Success");
                    $('#success').text(notification.message);
                    populateRentTable();
                   $('#successDiv').show();
                    $('#errorDiv').hide();

                },
                error: function(response) {
                    alert("Error");
                    $('#errorDiv').show();
                    $('#successDiv').hide();
                    $('#error').text('Error Occured while saving data !!');
                }
            });
        }
    });


});

function populateRentTable() {
    $.ajax({
        type: "GET",
        url:"/expmanager/app/rent/renthistoryforuser",

        dataType: "json",
        success: function (rentsheet) {
            var trHTML = '';
            $('#rentsheettable tbody').empty();
            latestRentSheet = rentsheet[0];
            var div_data="<option value="+rentsheet[0].rentGeneratedForMonth+">"+months[rentsheet[0].rentGeneratedForMonth-1]+"</option>";

            $(div_data).appendTo('#monthselector');
            $('#amountdue').val(rentsheet[0].due);
            $('#currentmonthrent').val(rentsheet[0].originalRentAmount);
            $('#netamount').val(rentsheet[0].adjustedRent);


            for(var i = 0; i < rentsheet.length; i++) {
                trHTML += '<tr><td>' + months[rentsheet[i].rentGeneratedForMonth-1] + '</td><td>' + rentsheet[i].rentGeneratedForYear + '</td><td>' +
                    rentsheet[i].totalExpensePaid + '</td><td>' + rentsheet[i].originalRentAmount
                    + '</td><td>'+ rentsheet[i].due + '</td>'
                    + '<td>'+ rentsheet[i].adjustedRent + '</td>'
                    + '<td>'+ rentsheet[i].rentActullyPaid + '</td>';

                if(rentsheet[i].rentPaidDate==null) {
                    trHTML += '<td></td>';
                }else {
                    trHTML += '<td>'+ rentsheet[i].rentPaidDate + '</td>' ;
                }
                trHTML +='</tr>';
            }
            $('#rentsheettable').append(trHTML);
        },
        error:	function (data) {
            var div_data="<option value="+"1"+">"+"Error"+"</option>";
            $(div_data).appendTo('#category');
        }

    });
}

