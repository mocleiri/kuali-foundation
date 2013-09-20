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

	<div class="container-fluid"> 	<?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h3>Opportunity Search</h3>
				</div>
                <div id="oppsearch-tabs">
                    <a class="various fancybox.ajax" data-fancybox-type="ajax" href="modal/lookup-oppsearch.html">Ajax</a>
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
					
			</div></div>

		

		<!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.basics.details.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.basics.deliveryinfo.php'" class="btn btn-primary">Save and continue</button>
			
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