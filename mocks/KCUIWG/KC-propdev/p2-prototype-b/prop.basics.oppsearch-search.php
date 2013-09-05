<?php
# Variables
$section = 'basics';
$page = 'basics-search';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

	<div class="container">
		
		<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

		<div class="row">

			<div class="col-md-3">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-md-9" id="content" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Opportunity Search</h2>
                    <a class="various fancybox.ajax" data-fancybox-type="ajax" href="modal/lookup-oppsearch2.html">Ajax</a>
                    <a data-toggle="modal" data-target="#myModal" href="modal/lookup-oppsearch.html" class="btn btn-sm btn-default">Load New...</a>
				</div>

				<div class="section-content hidden">
					<form action="#" method="post" class="">
						<fieldset>
							<legend>Opportunity actions</legend>
							<div class="form-group clearfix">
								<button class="btn btn-default modal" data-page="page-name-here.html">Load new opportunity...</button>
							</div>
						</fieldset>
					</form>
				</div>

				<!-- // -->
					<div id="oppsearch-tabs"></div>
			</div>

		</div>

		<!-- Button row -->
		<div class="btn-row-page-action">
			<div class="row">
				<button class="btn btn-default">Save</button>
				<button class="btn btn-primary">Save and continue...</button>
			</div>
		</div>
		<!-- // -->

	</div>

</section>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-hidden="true"></div>
<script>
$(document).ready(function() {
	
	$("#btn-oppsearch, #btn-newsearch").live('click', function() {	

		$('.modal-backdrop').remove();
		$('#myModal').removeData('bs.modal');
		$('#myModal').modal ({remote : this.href });
	
		return false;
	});
	
	$(".fancy-close").live("click", function(){
		parent.$.fancybox.close();
		
	});
	
	$(".various").fancybox({
		fitToView	: false,
		width		: 800,
		height		: '70%',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none',
		//type: 'iframe',
	});
	
	$(".load-tabs").live("click", function(){
			$('#oppsearch-tabs').load('modal/lookup-oppsearch-tabs.html');
		});
	
   
});
</script>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>