//csrf tokens for ajax post
var token = $("meta[name='_csrf']").attr("content");
$.ajaxSetup({
    beforeSend:function (xhr) {
        xhr.setRequestHeader('X-CSRF-TOKEN', token);
    }
});

$('#errorDiv').hide();
$('#successDiv').hide();

$body = $("body");
$(document).on({
    ajaxStart:function () {
        $body.addClass("loading");
    },
    ajaxStop:function () {
        $body.removeClass("loading");
    }
});

$(document).ready(function () {
    var chart = Morris.Area({
        element: 'morris-area-chart-edited',
        parseTime: false,
        data: [],
        xkey: 'monthYear',
        ykeys: ['openingBalance','closingBalance','totalExpense'],
        labels: ['Op. Balance','Cl. Balance','Total Expense']
    });
	

    $.ajax({
        type:"GET",
        url:"/expmanager/app/report/accountsummary",
        dataType:"json",
        success:function (data) {
            chart.setData(data);
        },
        error:function (response) {
        }
    });
});