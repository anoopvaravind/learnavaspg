var globalItem;

$(document).ready(function () {

    $("#submitButton").prop("disabled", false);
    $("#datepicker").datepicker();
    $("#datepicker").datepicker({dateFormat:'mm/dd/yy'});

    $.ajax({
        type:"GET",
        url:"/expmanager/app/category/listAll",

        dataType:"json",
        success:function (data) {
            $.each(data.data, function (value, key) {
                var div_data = "<option value=" + value + ">" + key + "</option>";
                $(div_data).appendTo('#category');
            });
        },
        error:function (data) {
            var div_data = "<option value=" + "1" + ">" + "Error" + "</option>";
            $(div_data).appendTo('#category');
        }

    });

    populateTable();

    jQuery.validator.addMethod(
        "validateMoney",
        function (value, element) {
            var isValidMoney = /^\d{0,4}(\.\d{0,2})?$/.test(value);
            return this.optional(element) || isValidMoney;
        },
        "Please enter a valid amount"
    );
    $("form[name='itemForm']").validate({
        // Specify validation rules
        rules:{
            // The key name on the left side is the name attribute
            // of an input field. Validation rules are defined
            // on the right side
            itemName:"required",
            price:{
                required:true,
                validateMoney:true
            },
            purchasedDate:{
                required:true,
                date:true
            },
            email:{
                required:true,
                // Specify that email should be validated
                // by the built-in "email" rule
                email:true
            },
            password:{
                required:true,
                minlength:5
            }
        },
        // Specify validation error messages
        messages:{
            itemName:"Please enter Item name",
            price:{
                required:"Please enter amount"
            },
            purchasedDate:{
                required:"Please enter paid date",
                date:"Please enter a valid date in MM/DD/YYYY format"
            },
            password:{
                required:"Please provide a password",
                minlength:"Your password must be at least 5 characters long"
            },
            email:"Please enter a valid email address"
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler:function (form) {
            var data = {};
            var nestedObj = {};
            var hiddenValue = $('#hiddenField').val();
            if (hiddenField != "") {
                data.id = $('#hiddenField').val();
            }
            data.itemName = $('#itemName').val();
            data.price = $('#price').val();
            data.purchasedDate = convertDate($("input[name=purchasedDate]").val());
            data.comment = $('#comment').val();
            nestedObj.id = $('#category').val();
            data.category = nestedObj;
            alert(JSON.stringify(data));

            $.ajax({
                type:"POST",
                url:"/expmanager/app/item/save",
                data:JSON.stringify(data),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success:function (notification) {
                    if (notification.error) {
                        $('#errorDiv').show();
                        $('#successDiv').hide();
                        $('#error').text(notification.message);

                    } else {
                        item = notification.response;
                        globalItem.item;
                        $('#success').text(notification.message);
                        $('#successDiv').show();
                        $('#errorDiv').hide();
                        var trHTML = '';
                        $('#itemTable tbody').empty();
                        for (var i = 0; i < item.length; i++) {
                            trHTML += '<tr><td>' + (i + 1) + '</td><td>' + item[i].itemName + '</td><td>' + item[i].price + '</td><td>' + item[i].purchasedDate +
                                '</td><td>' + item[i].comment + '</td>'
                                + '<td>' + '<input type="image" id="editButton" style="height:25px;width:25px;" src="/expmanager/images/edit.jpg" onclick="editItem(' + i + ')"></input>'
                                + '&nbsp;&nbsp;'
                                + '<input type="image" id="deleteButton" style="height:25px;width:25px;" src="/expmanager/images/delete.png" onclick="deleteItem(' + i + ')"></input>' + '</td>'
                                + '</tr>';
                        }
                        $('#itemTable').append(trHTML);

                        $('#itemName').val("");
                        $('#price').val("");
                        $('#purchasedDate').val("");
                        //data["category"] = $('#category').val();
                        $('#comment').val("");
                    }

                },
                error:function (response) {
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
        type:"GET",
        url:"/expmanager/app/item/findAllItemPerUserAndDate",
        dataType:"json",
        success:function (item) {
            globalItem = item;
            var trHTML = '';
            $('#itemTable tbody').empty();
            for (var i = 0; i < item.length; i++) {
                trHTML += '<tr><td>' + (i + 1) + '</td><td>' + item[i].itemName + '</td><td>' + item[i].price + '</td><td>' + item[i].purchasedDate +
                    '</td><td>' + item[i].comment + '</td>'
                    + '<td>' + '<input type="image" id="editButton" style="height:25px;width:25px;" src="/expmanager/images/edit.jpg" onclick="editItem(' + i + ')"></input>'
                    + '&nbsp;&nbsp;'
                    + '<input type="image" id="deleteButton" style="height:25px;width:25px;" src="/expmanager/images/delete.png" onclick="deleteItem(' + i + ')"></input>' + '</td>'
                    + '</tr>';
            }
            $('#itemTable').append(trHTML);
        },
        error:function (response) {
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

function editItem(i) {
    $('#itemName').val(globalItem[i].itemName);
    $('#price').val(globalItem[i].price);
    $("input[name=purchasedDate]").val(globalItem[i].purchasedDate);
    $('#comment').val(globalItem[i].comment);
    $('#category').val(globalItem[i].category.id);
    $('#hiddenField').val(globalItem[i].id);
}

function deleteItem(i) {
    $('<div></div>').appendTo('body')
        .html('<div><h6>Are you sure?</h6></div>')
        .dialog({
            modal:true,
            title:'Delete message',
            zIndex:10000,
            autoOpen:true,
            width:'auto',
            resizable:false,
            buttons:{
                Yes:function () {

                    //globalItem[i].purchasedDate = convertDate(globalItem[i].purchasedDate);
                    globalItem[i].purchasedDate = null;
                    alert(globalItem[i].purchasedDate);
                    $.ajax({
                        type:"POST",
                        url:"/expmanager/app/item/delete",
                        data:JSON.stringify(globalItem[i]),
                        contentType:"application/json; charset=utf-8",
                        dataType:"json",
                        success:function (notification) {
                            if (notification.error) {
                                $('#errorDiv').show();
                                $('#successDiv').hide();
                                $('#error').text(notification.message);

                            } else {
                                item = notification.response;
                                globalItem = item;
                                $('#success').text(notification.message);
                                $('#successDiv').show();
                                $('#errorDiv').hide();
                                var trHTML = '';
                                $('#itemTable tbody').empty();
                                for (var i = 0; i < item.length; i++) {
                                    trHTML += '<tr><td>' + (i + 1) + '</td><td>' + item[i].itemName + '</td><td>' + item[i].price + '</td><td>' + item[i].purchasedDate +
                                        '</td><td>' + item[i].comment + '</td>'
                                        + '<td>' + '<input type="image" id="editButton" style="height:25px;width:25px;" src="/expmanager/images/edit.jpg" onclick="editItem(' + i + ')"></input>'
                                        + '&nbsp;&nbsp;'
                                        + '<input type="image" id="deleteButton" style="height:25px;width:25px;" src="/expmanager/images/delete.png" onclick="deleteItem(' + i + ')"></input>' + '</td>'
                                        + '</tr>';
                                }
                                $('#itemTable').append(trHTML);
                            }
                        },
                        error:function (response) {
                            $('#errorDiv').show();
                            $('#successDiv').hide();
                            $('#error').text('Error Occurred !!');
                        }
                    });

                    $(this).dialog("close");
                },
                No:function () {
                    $(this).dialog("close");
                }
            },
            close:function (event, ui) {
                $(this).remove();
            }
        });
}
