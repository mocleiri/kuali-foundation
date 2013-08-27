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
		Subnavigation
		Expanding and collapsing handler
	*/
	$('.uif-navigation ul li.expanded').find('ul').show();

	$('.uif-navigation ul li a').on('click', function(e) {

		if ($(this).parent().hasClass('expanded')) {

			// Do nothing
			e.preventDefault();
			return false;

		} else {

			// Slide open the child list
			$(this).parent().find('ul').slideDown();

		}		
	});



	/*
		Modal handler
		Calls Fancybox modal using the `page` data attribute
	*/
	$('.launch-modal').on('click', function(e){
		e.preventDefault();

		$.fancybox.open({
			href: $(this).data('modal-page'),
			type: 'iframe',
			padding: 0
		});
	});





	// Document search, Fancybox
	$("#docsearch").click(function () {
		$.fancybox.open({
			href: 'modal/docsearch.html',
			type: 'iframe',
			padding: 0,
		});
	});

	// Sign in, Fancybox
	$("#signin").click(function () {
		$.fancybox.open({
			href: 'modal/login.html',
			type: 'iframe',
			padding: 0,
			width: 500,
		});
	});

	// Document info, Fancybox
	$("#docinfo").on('click', function() {
		$.fancybox.open({
			href: 'modal/docinfo.html',
			type: 'iframe',
			padding: 0,
			width: 640,
		});
	});

	// Trigger edit search, jQuery
	$("#triggerEditSearch").click(function () {
		$("#editSearch").slideToggle(500);
	});

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