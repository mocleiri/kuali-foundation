/*
	Custom scripting for additional functionality
	Author: 	Chris Rodriguez, clrux@bu.edu
	Created: 	29 Jan 2013, 12:09
	Last Mod: 	8 Feb 2013, 09:39
	Version: 	0.3
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
	$document 	= $('#document'),
	$toc 		= $('#ToC'),
	$sec		= $('#secondary'),
	$window 	= $(window),
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
Add required marks to all required fields' parents
---------------------------------- */
function init_required_elems() {
	$('body').find('input, textarea, select').each(function() {
		if ($(this).is(':required') || $(this).attr('required') == "required") {
			$('body').find($('label[for="' + $(this).attr('id') + '"]')).append('<span class="req"><i class="icon icon-asterisk"></i><span>required</span></span>');
			console.log('required');
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