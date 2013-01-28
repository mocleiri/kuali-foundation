/*
	Custom scripting for additional functionality
	Author: Chris Rodriguez, clrux@bu.edu
	Created: 29 Jan 2012, 12:09 PM
	Version: 0.1
*/



/*
Sticky table of contents
---------------------------------- */
(function($){$.fn.jScroll=function(e){var f=$.extend({},$.fn.jScroll.defaults,e);return this.each(function(){var a=$(this);var b=$(window);var c=new location(a);b.scroll(function(){a.stop().animate(c.getMargin(b),f.speed)})});function location(d){this.min=d.offset().top;this.originalMargin=parseInt(d.css("margin-top"),10)||0;this.getMargin=function(a){var b=d.parent().height()-d.outerHeight();var c=this.originalMargin;if(a.scrollTop()>=this.min)c=c+f.top+a.scrollTop()-this.min;if(c>b)c=b;return({"marginTop":c+'px'})}}};$.fn.jScroll.defaults={speed:"slow",top:10}})(jQuery);

$.fn.scrollBottom=function(){return $(document).height()-this.scrollTop()-this.height();};

var $toc = $('#ToC');
if ($toc.length) {

	var $window = $(window),
		$brand = $('#brand'),
		$header = $('#header'),
		tocOffset = $toc.offset();

	function init_sticky_toc() {
		if( $window.scrollTop() > tocOffset.top ) {
			$toc.addClass('fixed');
		} else {
			$toc.removeClass('fixed');
		}
	}
}



/*
Document ready
---------------------------------- */
$(document).ready(function() {
	$window.bind("resize", function() {
		init_sticky_toc();
	});
	
	$window.bind("scroll", function() {
		init_sticky_toc();
	});
});