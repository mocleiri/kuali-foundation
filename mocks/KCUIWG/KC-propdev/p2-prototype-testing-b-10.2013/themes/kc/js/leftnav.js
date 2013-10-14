$(function () {
    function e() {
        if (typeof cookie_not_handle_user_settings != "undefined" && cookie_not_handle_user_settings == true) {
            return
        }
        if ($.cookie("sidebar-collapsed") == "true") {
            $("#sidebar").addClass("sidebar-collapsed")
        }
        if ($.cookie("sidebar-fixed") == "true") {
            $("#sidebar").addClass("sidebar-fixed")
        }
        if ($.cookie("navbar-fixed") == "true") {
            $("#navbar").addClass("navbar-fixed")
        }

       
        if (t !== undefined) {
            $("#main-container").addClass("sidebar-" + t)
        }
        if (n !== undefined) {
            $("#navbar").addClass("navbar-" + n)
        }
    }
   
    $(".show-popover").popover();
    window.prettyPrint && prettyPrint();
    var n = function () {
        if ($("#sidebar.sidebar-fixed").size() == 0) {
            $("#sidebar .nav").css("height", "auto");
            return
        }
        if ($("#sidebar.sidebar-fixed.sidebar-collapsed").size() > 0) {
            $("#sidebar .nav").css("height", "auto");
            return
        }
        var e = $(window).height() - 90;
        $("#sidebar.sidebar-fixed .nav").css("height", e + "px").niceScroll({
            railalign: "left",
            railoffset: {
                left: 3
            },
            cursoropacitymax: .7
        });
        setTimeout(function () {
            $("#sidebar.sidebar-fixed .nav").getNiceScroll().doScrollPos(0, $("#sidebar .nav").scrollTop() + 40, 900)
        }, 9)
    };
    n();
    $("#sidebar a.dropdown-toggle").click(function () {
        var e = $(this).next(".submenu");
        var t = $(this).children(".arrow");
        if (t.hasClass("icon-angle-right")) {
            t.addClass("anim-turn90")
        } else {
            t.addClass("anim-turn-90")
        }
        e.slideToggle(400, function () {
            if ($(this).is(":hidden")) {
                t.attr("class", "arrow icon-angle-right");
                $("#sidebar.sidebar-fixed .nav").getNiceScroll().resize()
            } else {
                t.attr("class", "arrow icon-angle-down");
                n()
            }
            t.removeClass("anim-turn90").removeClass("anim-turn-90")
        })
    });
    $("#sidebar.sidebar-collapsed #sidebar-collapse > i").attr("class", "icon-angle-right");
    $("#sidebar-collapse").click(function () {
        $("#sidebar").toggleClass("sidebar-collapsed");
        if ($("#sidebar").hasClass("sidebar-collapsed")) {
            $("#sidebar-collapse > i").attr("class", "icon-angle-right");
            $.cookie("sidebar-collapsed", "true")
        } else {
            $("#sidebar-collapse > i").attr("class", "icon-angle-left");
            $.cookie("sidebar-collapsed", "false")
        }
        $(".nice-scroll").getNiceScroll().resize();
        n()
    });
    $("#sidebar").on("show.bs.collapse", function () {
        if ($(this).hasClass("sidebar-collapsed")) {
            $(this).removeClass("sidebar-collapsed")
        }
    });
    $("#sidebar .search-form").click(function () {
        $('#sidebar .search-form input[type="text"]').focus()
    });
    $("#sidebar .nav > li.active > a > .arrow").removeClass("icon-angle-right").addClass("icon-angle-down");


})