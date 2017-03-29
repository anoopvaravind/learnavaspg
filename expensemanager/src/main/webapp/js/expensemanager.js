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