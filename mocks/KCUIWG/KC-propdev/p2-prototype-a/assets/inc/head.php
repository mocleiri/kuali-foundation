<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Kuali Coeus</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!--  styles -->
<link href="assets/css/bootstrap.css" rel="stylesheet">
<link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
<link href="assets/css/bootstrap-editable.css" rel="stylesheet">
<link href="assets/css/less/style.css" rel="stylesheet">
<!-- <link href="assets/css/styles.css" rel="stylesheet"> -->
<link href="assets/css/icons-custom.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="assets/js/fancybox/jquery.fancybox.css" media="screen" />

<!--  HTML5 shim, for IE6-8 support of HTML5 elements --><!--[if lt IE 9]><script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->

<!-- Scriptage -->
<script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script>
<script src="assets/js/jquery.js"></script>
<script src="assets/js/bootstrap-transition.js"></script>
<script src="assets/js/bootstrap-alert.js"></script>
<script src="assets/js/bootstrap-modal.js"></script>
<script src="assets/js/bootstrap-dropdown.js"></script>
<script src="assets/js/bootstrap-scrollspy.js"></script>
<script src="assets/js/bootstrap-tab.js"></script>
<script src="assets/js/bootstrap-tooltip.js"></script>
<script src="assets/js/bootstrap-popover.js"></script>
<script src="assets/js/bootstrap-button.js"></script>
<script src="assets/js/bootstrap-collapse.js"></script>
<script src="assets/js/bootstrap-carousel.js"></script>
<script src="assets/js/bootstrap-datepicker.js"></script>
<script src="assets/js/bootstrap-typeahead.js"></script>
<script src="assets/js/bootstrap-affix.js"></script>
<script src="assets/js/site.js" type="text/javascript"></script>
<script src="assets/js/toolbar.js" type="text/javascript"></script>
<script type="text/javascript" src="assets/js/jquery.jqplot.min.js"></script>
<script type="text/javascript" src="assets/js/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript" src="assets/js/fancybox/jquery.fancybox.js"></script>
<script type="text/javascript" src="assets/js/jquery.sticky.js"></script>
<script src="assets/js/bootstrap-editable.js"></script>
<script src="assets/js/application.js"></script>


<script type="text/javascript">
    $(document).ready(function () {
        $("#docsearch").click(function () {
            $.fancybox.open({
                href: 'modal/docsearch.html',
                type: 'iframe',
                //autoSize: false,
                padding: 0,
                //minHeight : 470,
                //height: 600,
            });
        });
        $("#signin").click(function () {
            $.fancybox.open({
                href: 'modal/login.html',
                type: 'iframe',
                padding: 0,
                width: 500,
            });
        });

       
    });
	
</script>
<script type='text/javascript'>
    //<![CDATA[ 
    //]]>
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $("#triggerEditSearch").click(function () {
            $("#editSearch").slideToggle(500);
        });
    });
</script>

<script>  
$(function ()  
{ $("#more").popover();  
});  
</script>

<script type='text/javascript'>
    //<![CDATA[ 
    $(window).load(function () {
        $("#link1").click(function () {
            $("#link1").addClass('active');
            $("#link2, #link3, #link4, #link5").removeClass('active');
            $("#menu1").slideDown(200);
            $("#menu2, #menu3, #menu4, #menu5").slideUp(200);
        });
        $("#menu1").mouseleave(function () {
            $("#link1").removeClass('active');
            $("#menu1").slideUp(200);
        });
        $("#link2").click(function () {
            $("#link2").addClass('active');
            $("#link1, #link3, #link4, #link5").removeClass('active');
            $("#menu2").slideDown(200);
            $("#menu1, #menu3, #menu4, #menu5").slideUp(200);
        });
        $("#menu2").mouseleave(function () {
            $("#link2").removeClass('active');
            $("#menu2").slideUp(200);
        });
        $("#link3").click(function () {
            $("#link3").addClass('active');
            $("#link1, #link2, #link4, #link5").removeClass('active');
            $("#menu3").slideDown(200);
            $("#menu1, #menu2, #menu4, #menu5").slideUp(200);
        });
        $("#menu3").mouseleave(function () {
            $("#link3").removeClass('active');
            $("#menu3").slideUp(200);
        });
        $("#link4").click(function () {
            $("#link2").addClass('active');
            $("#link1, #link2, #link3, #link5").removeClass('active');
            $("#menu4").slideDown(200);
            $("#menu1, #menu2, #menu3, #menu5").slideUp(200);
        });
        $("#menu4").mouseleave(function () {
            $("#link4").removeClass('active');
            $("#menu4").slideUp(200);
        });
        $("#link5").click(function () {
            $("#link5").addClass('active');
            $("#link1, #link2, #link3, #link4").removeClass('active');
            $("#menu5").slideDown(200);
            $("#menu1, #menu2, #menu3, #menu4").slideUp(200);
        });
        $("#menu5").mouseleave(function () {
            $("#link5").removeClass('active');
            $("#menu5").slideUp(200);
        });
    }); //]]>
</script>


<script>
$(document).ready(function() {
    $("#docinfo").on('click', function() {
        $.fancybox.open({
            href: 'modal/docinfo.html',
            type: 'iframe',
            padding: 0,
            width: 640,
        });
    });
});
</script>
</head>

<body>
    <div class="a11y-jump-links">
        <ul>
            <li><a href="#content">Skip to main content</a></li>
        </ul>
    </div>