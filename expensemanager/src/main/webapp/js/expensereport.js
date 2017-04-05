//csrf tokens for ajax post
var token = $("meta[name='_csrf']").attr("content");
$.ajaxSetup({
    beforeSend:function (xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN', token);
    }
});
$('#errorDiv').hide();
$('#successDiv').hide();

var months = [ "January", "February", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December" ];

$(document).ready(function () {

    $("#submit").prop("disabled", false);

    $("form[name='expenseReportForm']").validate({
        submitHandler:function (form) {

            $.ajax({
                type:"GET",
                url:"/expmanager/app/report/expensehistorypermonthandyear",
                data:{month:$('#month').val(), year:$('#year').val()},
                success:function (item) {
                    if (item.length == 0) {
                        $('#expenseReportTable tbody').empty();
                        $('#errorDiv').show();
                        $('#successDiv').hide();
                        $('#error').text('No records found !!');
                        return;
                    }
                    $('#errorDiv').hide();

                    var trHTML = '';
                    $('#expenseReportTable tbody').empty();
                    for (var i = 0; i < item.length; i++) {
                        //months[rentsheet[i].user.displayName]
                        trHTML += '<tr>' +
                            '<td>' + item[i].user.displayName + '</td>'
                            + '<td>' + item[i].itemName + '</td>'
                            + '<td>' + item[i].price + '</td>'
                            + '<td>' + item[i].purchasedDate + '</td>'
                            + '<td>' + item[i].comment + '</td>';
                        trHTML += '</tr>';
                    }
                    $('#expenseReportTable').append(trHTML);

                },
                error:function (response) {
                    $('#errorDiv').show();
                    $('#successDiv').hide();
                    $('#error').text('Error Occurred retrieving data !!');
                }
            });
        }
    });


});


