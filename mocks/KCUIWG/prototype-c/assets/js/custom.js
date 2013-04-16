/*
	Custom scripting for additional functionality
	Author: 	Chris Rodriguez, clrux@bu.edu
	Created: 	8 Apr 2013, 16:34
	Last Mod: 	12 Apr 2013, 15:56
	Version: 	0.2.0
*/



/*
Document ready
---------------------------------- */
$(document).ready(function() {



	/*
	Global variables
	---------------------------------- */
	var $window = $(window);



	/*
	ScrollTop and ScrollBottom functions
	---------------------------------- */
	(function($){$.fn.jScroll=function(e){var f=$.extend({},$.fn.jScroll.defaults,e);return this.each(function(){var a=$(this);var b=$(window);var c=new location(a);b.scroll(function(){a.stop().animate(c.getMargin(b),f.speed)})});function location(d){this.min=d.offset().top;this.originalMargin=parseInt(d.css("margin-top"),10)||0;this.getMargin=function(a){var b=d.parent().height()-d.outerHeight();var c=this.originalMargin;if(a.scrollTop()>=this.min)c=c+f.top+a.scrollTop()-this.min;if(c>b)c=b;return({"marginTop":c+'px'})}}};$.fn.jScroll.defaults={speed:"slow",top:10}})(jQuery);
	$.fn.scrollBottom=function(){return $(document).height()-this.scrollTop()-this.height();};



	/*
	Sticky elements
	---------------------------------- */
	if ( $('#sticky').length && $('#ToC').length ) {
			
		var sOffset = $('#sticky').offset();
		var nOffset = $('#ToC').offset();

		function init_sticky_elems() {

			if ($window.scrollTop() > sOffset.top) {
				$('#sticky').addClass('fixed').css({
					width: $('.column-navleft').width()
				});

				$('.column-navleft').css({
					paddingTop: $('#sticky').height()
				});
			} else {
				$('#sticky').removeClass('fixed').css({
					width: 'auto'
				});

				$('.column-navleft').css({
					paddingTop: '0'
				});
			}

			if ($window.scrollTop() > nOffset.top - 117) {
				$('#ToC').addClass('fixed');
			} else {
				$('#ToC').removeClass('fixed');
			}

		}
	}

	if ( $('.docControls').length ) {

		var d 		= $(document).height();
		var w 		= $(window).height();
		var b 		= 98;

		function init_sticky_controls() {

			if ( ($(window).height() + $(window).scrollTop()) <= (d - b) ) {
				$('.docControls').addClass('fixed').css({
					width: $('.column-navleft').width()
				});
			} else {
				$('.docControls').removeClass('fixed');
			}

		}

	}

	init_sticky_elems();
	init_sticky_controls();

	$(window).bind("resize, scroll", function() {
		init_sticky_elems();
		init_sticky_controls();
	});

});	
