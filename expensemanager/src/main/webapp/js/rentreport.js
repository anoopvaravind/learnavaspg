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

    $("form[name='rentReportForm']").validate({
        submitHandler:function (form) {

            $.ajax({
                type:"GET",
                url:"/expmanager/app/report/renthistorypermonthandyear",
                data:{month:$('#month').val(), year:$('#year').val()},
                success:function (rentsheet) {
                    if (rentsheet.length == 0) {
                        $('#rentsheettable tbody').empty();
                        $('#errorDiv').show();
                        $('#successDiv').hide();
                        $('#error').text('No records found !!');
                        return;
                    }
                    $('#errorDiv').hide();

                    var trHTML = '';
                    $('#rentsheettable tbody').empty();
                    for (var i = 0; i < rentsheet.length; i++) {
                        //months[rentsheet[i].user.displayName]
                        trHTML += '<tr>' +
                            '<td>' + rentsheet[i].user.displayName + '</td>'
                            + '<td>' + rentsheet[i].totalExpensePaid + '</td>'
                            + '<td>' + rentsheet[i].originalRentAmount + '</td>'
                            + '<td>' + rentsheet[i].due + '</td>'
                            + '<td>' + rentsheet[i].adjustedRent + '</td>'
                            + '<td>' + rentsheet[i].rentActullyPaid + '</td>';
                        if (rentsheet[i].rentPaidDate == null) {
                            trHTML += '<td></td>';
                        } else {
                            trHTML += '<td>' + rentsheet[i].rentPaidDate + '</td>';
                        }
                        trHTML += '</tr>';
                    }
                    $('#rentsheettable').append(trHTML);

                },
                error:function (response) {
                    alert("Error");
                    $('#errorDiv').show();
                    $('#successDiv').hide();
                    $('#error').text('Error Occurred retrieving data !!');
                }
            });
        }
    });


});


