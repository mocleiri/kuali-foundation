/*
	Custom scripting for additional functionality
	Author: 	Chris Rodriguez, clrux@bu.edu
	Created: 	29 Jan 2013, 12:09
	Last Mod: 	13 Feb 2013, 15:45
	Version: 	0.4
*/



/*
ScrollTop and ScrollBottom functions
---------------------------------- */
(function($){$.fn.jScroll=function(e){var f=$.extend({},$.fn.jScroll.defaults,e);return this.each(function(){var a=$(this);var b=$(window);var c=new location(a);b.scroll(function(){a.stop().animate(c.getMargin(b),f.speed)})});function location(d){this.min=d.offset().top;this.originalMargin=parseInt(d.css("margin-top"),10)||0;this.getMargin=function(a){var b=d.parent().height()-d.outerHeight();var c=this.originalMargin;if(a.scrollTop()>=this.min)c=c+f.top+a.scrollTop()-this.min;if(c>b)c=b;return({"marginTop":c+'px'})}}};$.fn.jScroll.defaults={speed:"slow",top:10}})(jQuery);
$.fn.scrollBottom=function(){return $(document).height()-this.scrollTop()-this.height();};



/*
Global variables
---------------------------------- */
var $header 	= $('#header'),
	$window 	= $(window),
	$document 	= $('#document'),
	$toc 		= $('#ToC'),
	$sec		= $('#secondary'),
	$dOffset	= $document.offset(),
	$tOffset 	= $toc.offset(),
	$sOffset	= $sec.offset();



/*
Sticky table of contents
---------------------------------- */
function init_sticky_elems() {
	if ($toc.length && $document.length) {
		if ($window.scrollTop() > $dOffset.top) {
			$document.addClass('fixed');
			$toc.addClass('fixed');
			$sec.addClass('fixed');
		} else {
			$document.removeClass('fixed');
			$toc.removeClass('fixed');
			$sec.removeClass('fixed');
		}
	}
}



/*
Form field validation
*** For example only. KRAD will probably have something already in place.
---------------------------------- */
// If there is a '#' in the URL (someone linking directly to a page with an anchor), go directly to that area and focus is
// Thanks to WebAIM.org for this idea
if (document.location.hash) {
	var anchorUponArrival = document.location.hash;
	setTimeout(function() {
		$(anchorUponArrival).focus();
	}, 100);
}

// Focuses on the correct section of the page if we're page linking
// Thanks to WebAIM.org for this idea
$('a[href^="#"]').click(function(event) {
	var inPageAnchor = "#" + this.href.split('#')[1];
	setTimeout(function() {
		$(inPageAnchor).focus();
	}, 100);
	return false;
});

$('#error_message_container').hide();

$('#validate_data').on('click', function() {
	var fields_with_errors = ['ProposalType', 'leadUnit', 'activityType', 'startDate', 'endDate', 'orgdocnum2', 'projectTitle'];

	$.each(fields_with_errors, function() {
		if( $('#' + this).is(':empty') || !$('#' + this).val()) {
			$('#' + this).parent().parent().addClass('error');
			$('#' + this).parent().parent().parent().addClass('error');
		}
	});

	$('#error_message_container').show().focus();
});



/*
Add required marks to all required fields' parents
---------------------------------- */
function init_required_elems() {
	$('body').find('input, textarea, select').each(function() {
		if ($(this).is(':required') || $(this).attr('required') == "required") {
			$('body').find($('label[for="' + $(this).attr('id') + '"]')).append('<span class="req">*<span>required</span></span>');
		}
	});
}



/*
Document ready
---------------------------------- */
$(document).ready(function() {
	
	init_required_elems();

	$window.bind("resize", function() {
		init_sticky_elems();
	});

	$window.bind("scroll", function() {
		init_sticky_elems();
	});


	/*
	Tooltips and Popovers
	------------------------------ */
	$('.show-popover').popover({
		trigger: 'hover',
		html: true
	});
	
});