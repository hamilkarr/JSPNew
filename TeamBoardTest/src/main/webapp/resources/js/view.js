$(function() {
    $('.form_cmt').focusin(function() {
        $('.refer_cmt').hide();
    });

    $('.form_cmt').focusout(function() {
        $('.refer_cmt').show();
    });
});