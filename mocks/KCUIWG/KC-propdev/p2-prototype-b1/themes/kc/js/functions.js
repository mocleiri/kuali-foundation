/*
	JavaScript functions for the Kuali Coeus protoype version 2
	Dependencies:
	- jQuery (1.7.2 +)
	- bootstrap.min.js
	- Fancybox
	- jquery.sticky.js

	Chris Rodriguez, clrux@bu.edu
	Tom Clark, thrclark@indiana.edu
*/

$(document).ready(function() {

	/*
		Focus on first form field for ease and flow
		Chris Rodriguez
	*/
	function focus_content_area() {
		$('#content').focus();
	}

	focus_content_area();


	/*
		Skip links for accessibility
		When a link receives focus it's position is changed making it visible
		Chris Rodriguez
	*/
	if (document.location.hash) {
		var anchorUponArrival = document.location.hash;
		setTimeout(function() {
			$(anchorUponArrival).focus();
		}, 100);
	}

	$('a[href^="#"]').click(function(e) {
		e.preventDefault();
		var inPageAnchor = "#" + this.href.split('#')[1];
		setTimeout(function() {
			$(inPageAnchor).focus();
		}, 100);
	});

	

	/*
		Subnavigation
		Set default view for navigation on page load
		Chris Rodriguez
	*/
	if ($('.uif-navigation')) {
		$('.uif-navigation ul li.expanded').find('ul').show();
	}



	/*
		Select box multiselect widget
		Initializes the multiselect plugin
		Chris Rodriguez (plugin by Eric Hynds http://www.erichynds.com/examples/jquery-ui-multiselect-widget/demos/#selectedlist)
	*/
	function init_select() {
		if ($('select').length) {
			$('select').each(function() {
				if ($(this).attr('multiple')) {
					$(this).multiselect({
						selectedList: 9
					});
				} else {
					$(this).multiselect({
						multiple: false,
						header: 'Select an option',
						noneSelectedText: 'Select an option',
						selectedList: 1
					});
				}
			});
		}
	}

	init_select();



	/*
		Modal handler
		Calls Fancybox modal using the `page` data attribute
		Chris Rodriguez
	*/
	$('.launch-modal').on('click', function(e){
		e.preventDefault();

		var fb_href = 	$(this).data('modal-page');

		$.fancybox.open({
			type: 'iframe',
			href: fb_href,
			minHeight: 300,
			width: 700,
			maxWidth: 700,
			padding: 0
		});
	});



	/*
		Dropdown focus for accessibility
		Brings focus to the popup/dropdown menu when the toggle is clicked
		Chris Rodriguez
	*/
	$('.dropdown-toggle').on('click', function(e) {
		var that = $(this).attr('id');
		e.preventDefault();

		if ($(this).parent().hasClass('open')) {
			$(this).focus();
		} else {
			$(this).parent().find('.dropdown-menu').attr('tabindex', '-1').focus();
		}
	});



	/*
		Additional <select> options
		Shows or hides additional form fields based on chosen <select> value
		Chris Rodriguez
	*/
	$('.onchange').change(function() {
		var that = $(this).val();
		$('.hidden-fields').hide();

		if ($('#' + that).length) {
			$('#' + that).show();
		} else {
			$('.hidden-fields').hide();
		}
	});



	/*
		Datepickers
		Inits the datepickers on classed elements
		Chris Rodriguez
	*/
	if ($('.uif-dateControl').length) {
		$('.uif-dateControl').each(function() {
			$(this).datepicker({
				autoclose: true,
			    todayHighlight: true
			});
		});
	}



	/*
		Subnav toggle
		Collapses and expandes the subnav making the content area full-width if desired
		Chris Rodriguez
	*/
	// var handle_sidebar_menu = function() {

	// 	$('#sidebar .has-sub > a').click(function(e) {
	// 		var last = $('.has-sub.open', $('#sidebar'));
	// 		last.removeClass('open');
	// 		$('.sub', last).slideUp(200);
			
	// 		var sub = $(this).next();
	// 		if (sub.is(':visible')) {
	// 			$(this).parent().removeClass('open');
	// 			sub.slideUp(200);
	// 		} else {
	// 			$(this).parent().addClass('open');
	// 			sub.slideDown(200);
	// 		}

	// 		e.preventDefault();
	// 	});
	// }

	var handle_sidebar_toggler = function() {

		$('#nav-toggle').click(function() {
			if ($('#sidebar').parent().hasClass('closed') === false) {
				$('#sidebar').removeClass('open').addClass('closed');
				$(this).removeClass('open').addClass('closed');

				if ($(window).width() > 000) {
					$('#sidebar').parent().addClass('closed').removeClass('open');
					$('#sidebar').parent().next().addClass('open').removeClass('closed');
				} else {
					$('#sidebar').parent().addClass('closed');
				}
			} else {
				$('#sidebar').removeClass('closed').addClass('open');
				$(this).removeClass('closed').addClass('open');

				if ($(window).width() > 000) {
					$('#sidebar').parent().removeClass('closed').addClass('open');
					$('#sidebar').parent().next().addClass('closed').removeClass('open');
				} else {
					$('#sidebar').parent().removeClass('closed');
				}
			}
		});
	}

	var handle_toggle_icon_col_size = function() {

		if ($(window).width() > 000) {
			$('#nav-toggle span').addClass('icon-expand').removeClass('icon-collapse');
			$('#sidebar').parent().addClass('open').removeClass('closed');
			$('#sidebar').parent().next().addClass('closed').removeClass('open');
		} else {
			$('#nav-toggle span').addClass('icon-collapse').removeClass('icon-expand');
			$('#sidebar').parent().addClass('closed').removeClass('open');
			$('#sidebar').parent().next().addClass('open').removeClass('closed');
		}

	}

	if ($('#sidebar').length) {
		// handle_sidebar_menu();
		handle_sidebar_toggler();
	}



	/*
		Button hrefs
		Makes a button act like a link
		Chris Rodriguez
	*/
	$('button').on('click', function() {
		if ($(this).attr('href')) {
			document.location = $(this).attr('href');
		} else {
			return false;
		}
	});



	/*
		Faux inline editing
		Inline editing plugins require AJAX and PHP, so we're just faking it for the prototype
		Appends a little edit icon to the container
		Wraps the clicked text in a form input box with two buttons
		Chris Rodriguez
	*/
	if ($('.uif-switchme').length) {
		$('.uif-switchme').each(function() {
			$(this).append('<button class="uif-switchme-edit icon-pencil"></button>');
		});
	}

	$('.uif-switchme').on('click', '.uif-switchme-edit', function() {
		var current_value = $(this).prev('span').text();

		if ($(this).parent().hasClass('uif-switchme-select')) {

			$(this).prev('span').wrapInner('<select class="form-control input-sm chzn uif-switchme-input" multiple />');

			current_options = '';
			current_value = current_value.split(",");

			for (var i = 0; i < current_value.length; i++) {
				current_options += '<option>' + current_value[i] + '</option>';
			};

			$('.uif-switchme').find('select.uif-switchme-input').html(current_options).chosen();

		} else if ($(this).parent().hasClass('uif-switchme-date')) {

			$(this).prev('span').wrapInner('<input type="text" class="form-control input-sm chzn uif-switchme-input uif-switchme-date" value="' + current_value + '" />');
			$('.uif-switchme').find('.uif-switchme-date').datepicker();

		} else {

			$(this).prev('span').wrapInner('<input type="text" class="form-control input-sm chzn uif-switchme-input" value="' + current_value + '" />');

		}

		$(this).parent().append('<button class="uif-switchme-save icon-save"></button><button class="uif-switchme-cancel" data-default-value="' + current_value + '">Cancel</button>');
		$(this).remove();

		return false;
	});

	$('.uif-switchme').on('click', '.uif-switchme-save', function() {
		var new_value = $(this).prev().find('input').val();

		if ($(this).prev('span').find('.chosen-container')) {

			new_value = '';
			var chosen_results = $(this).prev().find('.chosen-results li');

			chosen_results.each(function() {
				if ($(this).hasClass('result-selected')) {
					new_value += $(this).text() + ",";
				}
			});

			$(this).prev('span').find('.uif-switchme-input').remove();
			new_value = new_value.substr(0, new_value.length - 1);
			$(this).prev('span').text(new_value);

		} else {

			$(this).prev('span').find('.uif-switchme-input').remove();
			$(this).prev('span').text(new_value);

		}

		$(this).parent().append('<button class="uif-switchme-edit icon-pencil"></button>');
		$(this).next('button').remove();
		$(this).remove();

		return false;
	});

	$('.uif-switchme').on('click', '.uif-switchme-cancel', function() {
		var orig_value = $(this).data('default-value');

		$(this).parent().append('<button class="uif-switchme-edit icon-pencil"></button>');
		$(this).parent().find('input').remove();
		$(this).parent().find('span').text(orig_value);
		$(this).prev().remove();
		$(this).remove();

		return false;
	});



	/*
		Mailchimp style inline help
		Shows helper text on focus and hides it on blur
		Chris Rodriguez
	*/
	if ($('.helper-text').length) {
		$('.helper-text').slideUp();
	}

	$('.has-helper').on('focus', function() {
		if ($(this).parent().find('.helper-text')) {
			$(this).parent().find('.helper-text').slideDown();
		}
	});

	$('.has-helper').on('blur', function() {
		if ($(this).parent().find('.helper-text')) {
			$(this).parent().find('.helper-text').slideUp();
		}
	});



	/*
		New window
		Opens classed links in a new window
		Chris Rodriguez
	*/
	$('.new-window').on('click', function() {
		window.open($(this).attr('href'), 'Kuali Help Documentation');
		return false;
	});



	/*
		Window resize listener
		Performes specified functions when the window is resized
		Chris Rodriguez
	*/
	$(window).resize(function() {

		if ($('#sidebar').length) {
			handle_toggle_icon_col_size();
		}

	});
	



	// Document search, Fancybox
	// $("#docsearch").click(function () {
	// 	$.fancybox.open({
	// 		href: 'modal/docsearch.html',
	// 		type: 'iframe',
	// 		padding: 0,
	// 	});
	// });

	// Sign in, Fancybox
	// $("#signin").click(function () {
	// 	$.fancybox.open({
	// 		href: 'modal/login.html',
	// 		type: 'iframe',
	// 		padding: 0,
	// 		width: 500,
	// 	});
	// });

	// Document info, Fancybox
	// $("#docinfo").on('click', function() {
	// 	$.fancybox.open({
	// 		href: 'modal/docinfo.html',
	// 		type: 'iframe',
	// 		padding: 0,
	// 		width: 640,
	// 	});
	// });

	// Trigger edit search, jQuery
	// $("#triggerEditSearch").click(function () {
	// 	$("#editSearch").slideToggle(500);
	// });

	// Navigation handling, jQuery
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
		$("#link4").addClass('active');
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

});


/*      Opportunity search      */
$(document).ready(function() {
	
		
	$(".load-tabs").live("click", function(){
			parent.$.fancybox.close();
			$('#oppsearch-tabs').load('modal/lookup-oppsearch-tabs.html');
			
		});	
	
	$(".various").fancybox({
		fitToView	: true,
		//width		: 800,
		height		: '70%',
		autoSize	: true,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none',
		//type: 'iframe',
	});
	
	
	$(".fancy-close").live("click", function(){
		parent.$.fancybox.close();
		
	});

   
});



