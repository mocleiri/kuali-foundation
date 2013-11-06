<?php
# Variables
$section = 'budget';
$page = 'budget-summary';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper">
                
        <h3>Budget Summary</h3>

        <form action="#" method="post" class="form-horizontal">
            <fieldset>
                <legend>Coming soon</legend>
                
            </fieldset>
        </form>

        <div class="uif-stickyFooter uif-stickyButtonFooter">
            <div class="btn-row-page-action">
                <button href="prop.questionnaire.php" class="btn btn-default">Back</button>
                <button class="btn btn-default">Save</button>
                <button href="prop.budget.params.php" class="btn btn-primary">Save and Continue</button>
            </div>
        </div>
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>