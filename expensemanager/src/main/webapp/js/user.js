var globalusers;

$(document).ready(function () {

    $("#submitButton").prop("disabled", false);

    populateTable();

    jQuery.validator.addMethod(
        "validateMoney",
        function (value, element) {
            var isValidMoney = /^\d{0,4}(\.\d{0,2})?$/.test(value);
            return this.optional(element) || isValidMoney;
        },
        "Please enter a valid amount"
    );
    $("form[name='userForm']").validate({
        // Specify validation rules
        rules:{
            displayName:"required",
            username:"required",
            email:{
                required:true,
                // Specify that email should be validated
                // by the built-in "email" rule
                email:true
            }
        },
        // Specify validation error messages
        messages:{
            displayName:"Please enter display name",
            username:"Please enter user name",
            email:"Please enter a valid email address"
        },
        // Make sure the form is submitted to the destination defined
        // in the "action" attribute of the form when valid
        submitHandler:function (form) {
            var data = {};
            var nestedObj = {};
            var hiddenValue1 = $('#hiddenField1').val();
            if (hiddenField1 != "") {
                data.id = $('#hiddenField1').val();
            }
            var hiddenValue2 = $('#hiddenField2').val();
            if (hiddenField2 != "") {
                //never do this type of security issue
                data.password = $('#hiddenField2').val();
            }

            data.displayName = $('#displayName').val();
            data.username = $('#username').val();
            data.email = $('#email').val();
            nestedObj.id = $('#roles').val();
            if($('#optionsRadiosInline1').is(':checked') == true) {
                data.enabled = 1;
            }else {
                data.enabled = 0;
            }
            data.roles = nestedObj;
            alert(JSON.stringify(data));

            $.ajax({
                type:"POST",
                url:"/expmanager/admin/user/save",
                data:JSON.stringify(data),
                contentType:"application/json; charset=utf-8",
                dataType:"json",
                success:function (notification) {
                    if (notification.error) {
                        $('#errorDiv').show();
                        $('#successDiv').hide();
                        $('#error').text(notification.message);

                    } else {
                        users = notification.response;
                        globalusers = users;
                        $('#success').text(notification.message);
                        $('#successDiv').show();
                        $('#errorDiv').hide();
                        var trHTML = '';
                        $('#userTable tbody').empty();
                        for (var i = 0; i < users.length; i++) {
							trHTML += '<tr><td>' + users[i].displayName + '</td><td>' + users[i].username + '</td><td>' + users[i].email +
								'</td><td>' + users[i].roles.rolename + '</td>'
								+ '<td>' + users[i].enabled + '</td>'
								+ '<td>' + '<input type="image" id="editButton" style="height:25px;width:25px;" src="/expmanager/images/edit.jpg" onclick="editUser(' + i + ')"></input>'
								+ '&nbsp;&nbsp;'
								+ '<input type="image" id="deleteButton" style="height:25px;width:25px;" src="/expmanager/images/delete.png" onclick="deleteUser(' + i + ')"></input>' + '</td>'
								+ '</tr>';
						}
                        $('#userTable').append(trHTML);

                        $('#displayName').val("");
                        $('#username').val("");
                        $('#email').val("");
						$('#hiddenField1').val("");
						$('#hiddenField2').val("");
                    }

                },
                error:function (response) {
                    alert("Error");
                    $('#errorDiv').show();
                    $('#successDiv').hide();
                    $('#error').text('Error Occurred while saving data !!');
                }
            });
        }
    });

});

function populateTable() {
    $.ajax({
        type:"GET",
        url:"/expmanager/admin/user/allusers",
        dataType:"json",
        success:function (users) {
            globalusers = users;
            var trHTML = '';
            $('#userTable tbody').empty();
            for (var i = 0; i < users.length; i++) {
                trHTML += '<tr><td>' + users[i].displayName + '</td><td>' + users[i].username + '</td><td>' + users[i].email +
                    '</td><td>' + users[i].roles.rolename + '</td>'
					+ '<td>' + users[i].enabled + '</td>'
                    + '<td>' + '<input type="image" id="editButton" style="height:25px;width:25px;" src="/expmanager/images/edit.jpg" onclick="editUser(' + i + ')"></input>'
                    + '&nbsp;&nbsp;'
                    + '<input type="image" id="deleteButton" style="height:25px;width:25px;" src="/expmanager/images/delete.png" onclick="deleteUser(' + i + ')"></input>' + '</td>'
                    + '</tr>';
            }
            $('#userTable').append(trHTML);
        },
        error:function (response) {
            $('#errorDiv').show();
            $('#successDiv').hide();
            $('#error').text('Error Occurred while loading users!!');
        }
    });
}

function convertDate(usDate) {
    var dateParts = usDate.split(/(\d{1,2})\/(\d{1,2})\/(\d{4})/);
    return dateParts[3] + "-" + dateParts[1] + "-" + dateParts[2];
}

function editUser(i) {
    $('#displayName').val(globalusers[i].displayName);
    $('#username').val(globalusers[i].username);
    $('#email').val(globalusers[i].email);
    $('#roles').val(globalusers[i].roles.id);
    $('#hiddenField1').val(globalusers[i].id);
     $('#hiddenField2').val(globalusers[i].password);
	alert(globalusers[i].enabled);
    if(globalusers[i].enabled == true) {
        $('#optionsRadiosInline1').prop('checked', true);
    }else {
        $('#optionsRadiosInline2').prop('checked', true);
    }
}

function deleteUser(i) {
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

                    $.ajax({
                        type:"POST",
                        url:"/expmanager/admin/user/delete",
                        data:JSON.stringify(globalusers[i]),
                        contentType:"application/json; charset=utf-8",
                        dataType:"json",
                        success:function (notification) {
                            if (notification.error) {
                                $('#errorDiv').show();
                                $('#successDiv').hide();
                                $('#error').text(notification.message);

                            } else {
                                users = notification.response;
                                globalusers = users;
                                $('#success').text(notification.message);
                                $('#successDiv').show();
                                $('#errorDiv').hide();
                                var trHTML = '';
                                $('#userTable tbody').empty();
                                for (var i = 0; i < users.length; i++) {
                                    trHTML += '<tr><td>' + users[i].displayName + '</td><td>' + users[i].username + '</td><td>' + users[i].email +
                                        '</td><td>' + users[i].roles.rolename + '</td>'
                                        + '<td>' + users[i].enabled + '</td>'
                                        + '<td>' + '<input type="image" id="editButton" style="height:25px;width:25px;" src="/expmanager/images/edit.jpg" onclick="editUser(' + i + ')"></input>'
                                        + '&nbsp;&nbsp;'
                                        + '<input type="image" id="deleteButton" style="height:25px;width:25px;" src="/expmanager/images/delete.png" onclick="deleteUser(' + i + ')"></input>' + '</td>'
                                        + '</tr>';
                                }
                                $('#userTable').append(trHTML);
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
