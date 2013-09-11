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

		
	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
<div class="container-fluid"><div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed">
			  	<div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Opportunity Search</h2>
				</div>

				<div class="section-content">
					<div class="tab-title-container clearfix">
						<h4>You don't have any opportunities.</h4>

			            <div class="page-controls">
			            	<div class="well">
			            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Add new</button>
			            	</div>
			            </div>
			        </div>

	                <div id="oppsearch-tabs">
	                </div>
	            </div>

				<div class="page-controls clearfix">
					<div class="page-actions">
						<div class="well"></div>
					</div>

					<div class="page-navigation">
						<div class="well">
							<button class="btn btn-default">Save</button>
							<button class="btn btn-primary">Save and continue...</button>
						</div>
					</div>
				</div>

				<!-- // -->
					
			</div>

		</div>

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
	
	
	
	$('body').on('hidden.bs.modal', '.modal', function () {
		$(this).removeData('bs.modal');
	});
	
	$('#myModal').on('show.bs.modal', function () {
		console.log('show');
    	$(this).find('.modal-body').css({width:'auto',
                               height:'auto', 
                              'max-height':'100%'});
		});
		
		
		
	$(".load-tabs").live("click", function(){
		$('#myModal').modal('toggle')
			$('#oppsearch-tabs').load('modal/lookup-oppsearch-tabs.html');
			
		});
		
	
	$(".various").fancybox({
		fitToView	: false,
		width		: 800,
		height		: '70%',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none',
		type: 'iframe',
	});
	
	
	$(".fancy-close").live("click", function(){
		parent.$.fancybox.close();
		
	});
	
	
	
   
});
</script>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>