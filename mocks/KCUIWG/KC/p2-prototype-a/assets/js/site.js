$(document).ready(function () {
    $(".expandControl").toggle(function () {
        $(this).removeClass("closed");
        $(this).next(".expandTarget").slideDown(600);
    }, function () {
        $(this).addClass("closed");
        $(this).next(".expandTarget").slideUp(600);
    });
});