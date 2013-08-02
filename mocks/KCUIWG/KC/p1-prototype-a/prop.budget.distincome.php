<?php
# Variables
$page = 'budget-distincome';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <ul class="breadcrumb"> <li class="active"><a href="prop.budget.php">Budget Home</a> <span class="divider">/</span></li>
          <li >Budget 1</li>
        </ul> <ul class="nav nav-tabs page">
          <li><a href="prop.budget.summary.php">Summary</a></li>
          <li><a href="prop.budget.paramaters.php">Parameters</a></li>
          <li><a href="prop.budget.rates.php">Rates</a></li>
          <li><a href="prop.budget.personnel.php">Personnel</a></li>
          <li><a href="prop.budget.nonpersonnel.php">Non-personnel</a></li>
          <li class=" active"><a href="prop.budget.distincome.php">Distribution &amp; Income</a></li>
          <li><a href="prop.budget.modular.php">Modular Budget</a></li>
          <li><a href="prop.budget.actions.php">Budget Actions</a></li>
        </ul>
        <div class="box"> <div class="boxHeader expandControl">
            <h3> Cost Sharing</h3>
          </div>
        </div>
        <div class="box"> <div class="boxHeader expandControl">
            <h3> Unrecovered F&amp;A         </h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" >
            <table class="table table-striped table-bordered table-condensed">
              <thead>
                <tr>
                  <th>Label</th>
                  <th>Label</th>
                  <th>Label</th>
                  <th>Label</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="box"> <div class="boxHeader expandControl">
            <h3> Project Income</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:none" >
            <table class="table table-striped table-bordered table-condensed">
              <thead>
                <tr>
                  <th>Label</th>
                  <th>Label</th>
                  <th>Label</th>
                  <th>Label</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
                <tr>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                  <td>1234.56</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <!--<div class="box"> <div class="boxHeader expandControl">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent expandTarget"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>--> 
        <div style=" padd12px; text-align:center">
          <a href="prop.budget.nonpersonnel.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.budget.modular.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

  <script type="text/javascript">
    $(document).ready(function () {
  
  
  
  $(".disclose-dl1").toggle(

        function () {
            $("#dl1").slideDown(600);
            $(".disclose-dl1").html("hide");
        },

        function () {
            $("#dl1").slideUp(600);
            $(".disclose-dl1").html("show");
        });
  
	
});
  </script>

</body>
</html>