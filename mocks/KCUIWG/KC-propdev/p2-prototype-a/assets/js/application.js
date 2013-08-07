$(document).ready(function () {
   
    $('.submenu > a').click(function (e) {
        e.preventDefault();
        var submenu = $(this).siblings('ul');
        var li = $(this).parents('li');
        var submenus = $('#ToC li.submenu ul');
        var submenus_parents = $('#ToC li.submenu');
        if (li.hasClass('open')) {
            if (($(window).width() > 768) || ($(window).width() < 479)) {
                submenu.slideUp();
            } else {
                submenu.fadeOut(250);
            }
            li.removeClass('open');
        } else {
            if (($(window).width() > 768) || ($(window).width() < 479)) {
                submenus.slideUp();
                submenu.slideDown();
            } else {
                submenus.fadeOut(250);
                submenu.fadeIn(250);
            }
            submenus_parents.removeClass('open');
            li.addClass('open');
        }
    });
    var ul = $('#ToC > ul');
    $('#ToC > a').click(function (e) {
        e.preventDefault();
        var ToC = $('#ToC');
        if (ToC.hasClass('open')) {
            ToC.removeClass('open');
            ul.slideUp(250);
        } else {
            ToC.addClass('open');
            ul.slideDown(250);
        }
    });
    // === Resize window related === //
    $(window).resize(function () {
        if ($(window).width() > 479) {
            ul.css({
                'display': 'block'
            });
            $('#content-header .btn-group').css({
                width: 'auto'
            });
        }
        if ($(window).width() < 479) {
            ul.css({
                'display': 'none'
            });
            fix_position();
        }
        if ($(window).width() > 768) {
            $('#user-nav > ul').css({
                width: 'auto',
                margin: '0'
            });
            $('#content-header .btn-group').css({
                width: 'auto'
            });
        }
    });
    if ($(window).width() < 468) {
        ul.css({
            'display': 'none'
        });
        fix_position();
    }
    if ($(window).width() > 479) {
        $('#content-header .btn-group').css({
            width: 'auto'
        });
        ul.css({
            'display': 'block'
        });
    }
   
    /*
    ACCESSIBILITY
    Details: Allows focusing on the main content section of the page. Useful if we do 'skip links'.
    --------------------------------------- */
    if ($('#content').length) {
        $('#content').attr('tabindex', '-1').addClass('focusable');
    }

    if ($('#incident-alerts').length) {
        $('#incident-alerts').attr('tabindex', '-1').addClass('focusable');
    }
    
    // If a URL has a page anchor hash, focus on it
    if (document.location.hash) {
        var anchorUponArrival = document.location.hash;
        setTimeout(function() {
            $(anchorUponArrival).focus();
        }, 100);
    }

    /*
    HIGHLIGHTING LABELS
    Details: Highlights the current row of the selected label or form element
    --------------------------------------- */
    $('.control-group *').on('focus', function() {
        $(this).addClass('control-group-focused');
    }).on('blur', function() {
        $('.control-group.control-group-focused').removeClass('control-group-focused');
    });

    /*
    STICKY ELEMENTS
    Details: Uses the jquery.sticky.js plugin to easily stick elements during scroll
    --------------------------------------- */
    $('#ToC').sticky({ topSpacing: 52 });
    // $('.docControls').sticky({ topSpacing: 200 });

});