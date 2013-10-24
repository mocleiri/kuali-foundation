<?php
# Variables
$page = 'attachments';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->

				
					<h3>Attachments</h3>
				
	
				
<ul class="nav nav-tabs" id="myTab">
  <li class="active"><a href="#proposal">Proposal</a></li>
  <li><a href="#personnel">Personnel</a></li>
  <li><a href="#internal">Internal</a></li>
  <li><a href="#abstracts">Abstracts</a></li>
    <li><a href="#notes">Notes</a></li>
</ul>

<div class="tab-content">
  <div class="tab-pane active" id="proposal"><h4>Proposal</h4>
  
  <table class="table table-condensed table-smaller-text" id="table">
          <thead>
            <tr>
              <th style="width:16px">&nbsp;</th>
              <th>File Name</th>
              <th>Type</th>
              <th> Status</th>
              <th style="width:25%">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td style="text-align:center"><a href="#"><span aria-hidden="true" class="icon-chevron-right"></span></a></td>
              <td>narritive1.pdf</td>
              <td>Narritive</td>
              <td>Something</td>
              <td><a href="#">view/edit</a> | <a href="#">delete</a></td>
            </tr>
            <tr>
              <td style="text-align:center"><a href="#"><span aria-hidden="true" class="icon-chevron-right"></span></a></td>
              <td>narritive2.pdf</td>
              <td>Narritive</td>
              <td>Something</td>
              <td><a href="#">view/edit</a> | <a href="#">delete</a></td>
            </tr>
            <tr>
              <td style="text-align:center"><a href="aaa-compliance-view.html"><span aria-hidden="true" class="icon-chevron-right"></span></a></td>
              <td>mydata.doc</td>
              <td>Personal Data</td>
              <td>Something</td>
              <td><a href="aaa-compliance-view.html">view/edit</a> | <a href="#">delete</a></td>
            </tr>
            <tr>
              <td style="text-align:center"><a href="#"><span aria-hidden="true" class="icon-chevron-right"></span></a></td>
              <td>mybib.dox</td>
              <td>Bibliography</td>
              <td>Something</td>
              <td><a href="#">view/edit</a> | <a href="#">delete</a></td>
            </tr>
          </tbody>
        </table>
        
        </div>
  <div class="tab-pane" id="personnel"><h4>Personnel</h4></div>
  <div class="tab-pane" id="internal"><h4>Internal</h4></div>
  <div class="tab-pane" id="abstracts"><h4>Abstracts</h4></div>
    <div class="tab-pane" id="notes"><h4>Notes</h4></div>
</div>


				
					

					
				

				<!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.attachments.internal.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.attachments.notes.php'" class="btn btn-primary">Save and Continue</button>
			
		</div>
		<!-- // -->
        </div>
      </div>
    </div>
  </div>
</section>


<script>
$('#myTab a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
})
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>



