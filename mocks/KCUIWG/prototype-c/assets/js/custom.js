/*
	Custom scripting for additional functionality
	Author: 	Chris Rodriguez, clrux@bu.edu
	Created: 	8 Apr 2013, 16:34
	Last Mod: 	12 Apr 2013, 09:16
	Version: 	0.1.4
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
	Sticky table of contents
	---------------------------------- */
	if ( $('#sticky').length && $('#ToC').length ) {
			
		var sOffset = $('#sticky').offset();
		var nOffset = $('#ToC').offset();

		function init_sticky_elems() {

			// Doc header
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

			// ToC
			if ($window.scrollTop() > nOffset.top - 117) {
				$('#ToC').addClass('fixed');
			} else {
				$('#ToC').removeClass('fixed');
			}

		}
	}

	init_sticky_elems();

	$(window).bind("resize, scroll", function() {
		init_sticky_elems();
	});

});	
