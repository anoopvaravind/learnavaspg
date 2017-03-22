//csrf tokens for ajax post
var token = $("meta[name='_csrf']").attr("content");
$.ajaxSetup({
    beforeSend: function(xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN', token);
    }
});

$('#errorDiv').hide();
$('#successDiv').hide();


$(document).ready(function(){

    var token = $("meta[name='_csrf']").attr("content");
    $.ajaxSetup({
        beforeSend: function(xhr) {
            xhr.setRequestHeader('X-CSRF-TOKEN', token);
        }
    });

    $("#submitButton").prop("disabled", false);
    $( "#datepicker" ).datepicker();
    $("#datepicker").datepicker({dateFormat: 'mm/dd/yy'});

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

    populateTable();

    jQuery.validator.addMethod(
        "validateMoney",
        function(value, element) {
            var isValidMoney = /^\d{0,4}(\.\d{0,2})?$/.test(value);
            return this.optional(element) || isValidMoney;
        },
        "Please enter a valid amount"
    );
    $("form[name='itemForm']").validate({
        // Specify validation rules
        rules: {
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            itemName: "required",
            price:{
                required:true,
                validateMoney:true
            },
            purchasedDate: {
                required: true,
                date:true
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
            price:{
                required:"Please enter amount"
            },
            purchasedDate: {
                required: "Please enter paid date",
                date:"Please enter a valid date in MM/DD/YYYY format"
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
			 
           /* var data = {}
            //data["id"] = "1";
            data["itemName"] = $('#itemName').val();
            data["price"] = $('#price').val();

            data["purchasedDate"] = convertDate($("input[name=purchasedDate]").val());
            //data["category"] = $('#category').val();
            data["comment"] = $('#comment').val();
            //data.category.id = $('#category').val();*/
			var data = {};
			var nestedObj={};
			data.itemName = $('#itemName').val();
			data.price = $('#price').val();
			data.purchasedDate = convertDate($("input[name=purchasedDate]").val());
			data.comment = $('#comment').val();
			nestedObj.id = $('#category').val();
			data.category=nestedObj;			

            $('#itemName').val("");
            $('#price').val("");
            $('#purchasedDate').val("");
            //data["category"] = $('#category').val();
            $('#comment').val("");

            //var item = "x";

            $.ajax({
                type: "POST",
                url: "/expmanager/app/item/save",
                data: JSON.stringify(data) ,
                contentType: "application/json; charset=utf-8",
                dataType   : "json",
                success:  function(item) {
                    $('#success').text('Item Saved successfully');
                    $('#successDiv').show();
                    $('#errorDiv').hide();
                    var trHTML = '';
                    $('#itemTable tbody').empty();
                    for(var i = 0; i < item.length; i++) {
                        trHTML += '<tr><td>' + (i+1) + '</td><td>' + item[i].itemName + '</td><td>' + item[i].price + '</td><td>' + item[i].purchasedDate + '</td><td>' + item[i].comment + '</td></tr>';
                    }
                    $('#itemTable').append(trHTML);

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

function populateTable() {
    $.ajax({
        type: "GET",
        url: "/expmanager/app/item/findAllItemPerUserAndDate",
        dataType   : "json",
        success:  function(item) {
            var trHTML = '';
            $('#itemTable tbody').empty();
            for(var i = 0; i < item.length; i++) {

                trHTML += '<tr><td>' + (i+1) + '</td><td>' + item[i].itemName + '</td><td>' + item[i].price + '</td><td>' + item[i].purchasedDate + '</td><td>' + item[i].comment + '</td></tr>';
            }
            $('#itemTable').append(trHTML);
        },
        error: function(response) {
            $('#errorDiv').show();
            $('#successDiv').hide();
            $('#error').text('Error Occurred while loading items!!');
        }
    });
}

function convertDate(usDate) {
  var dateParts = usDate.split(/(\d{1,2})\/(\d{1,2})\/(\d{4})/);
  return dateParts[3] + "-" + dateParts[1] + "-" + dateParts[2];
}
