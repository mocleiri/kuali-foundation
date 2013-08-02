<?php
# Variables
$page = 'budget-personnel';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content"><!--<div class="btn-group" style="margin-bottom:20px">
          <button class="btn btn-small">Budget Period 1</button>
          <button class="btn btn-small dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span> </button>
          <ul class="dropdown-menu">
            <li> <a href="#">Budget Period 1</a></li>
            <li> <a href="#">Budget Period 2</a></li>
            <li> <a href="#">Budget Period 3</a></li>
            <li> <a href="#">Budget Period 4</a></li>
          </ul>
        </div>-->
        <div class="box"> <div class="boxHeader">
            <h3>Key Personnel</h3>
          </div>
          <div class="boxContent expandTarget" style="" >
            <div class="boxSubheader">
              <h4> Details</h4>
              <div class="boxControls"><a href="#" class="disclose-dl1">show</a></div>
            </div>
            <table class="table table-striped table-bordered table-condensed">
              <thead>
                <tr>
                  <th >Person</th>
                  <th >Job Code</th>
                  <th >Appointment Type</th>
                  <th >Base Salary</th>
                  <th >Salary Effective Date</th>
                  <th ></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Webster, Ben</td>
                  <td><a href="#" id="jobcode" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Job Code">AA000</a></td>
                  <td><a href="#" id="apptype" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Appointment Type">12M Duration</a></td>
                  <td><a href="#" id="basesal" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Base Salary">87,000.00</a></td>
                  <td><a href="#" id="saleff" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Salary Effective Date">01/01/2013</a></td>
                  <td><a href="#myModal" role="button" data-toggle="modal" class="btn btn-mini btn-danger"><i class="icon-white icon-minus"></i>Delete</a></td>
                </tr>
                <tr>
                  <td>Tatum, Art</td>
                  <td><a href="#">AA000</a></td>
                  <td><a href="#">12M  Duration</a></td>
                  <td><a href="#">69,000.00</a></td>
                  <td><a href="#">01/01/2013</a></td>
                  <td><a href="#myModal" role="button" data-toggle="modal" class="btn btn-mini btn-danger"><i class="icon-white icon-minus"></i>Delete</a></td>
                </tr>
                <tr>
                  <td>Lee, Peggy</td>
                  <td><a href="#">AA000</a></td>
                  <td><a href="#">12M  Duration</a></td>
                  <td><a href="#">69,000.00</a></td>
                  <td><a href="#">01/01/2013</a></td>
                  <td><a href="#myModal" role="button" data-toggle="modal" class="btn btn-mini btn-danger"><i class="icon-white icon-minus"></i>Delete</a></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="box"> <div class="boxHeader">
            <h3> Budget Overview <span> (Period 1)</span></h3>
          </div>
        </div>
        <div class="box"> <div class="boxHeader">
            <h3>Personnel Detail <span> (Period 1)</span></h3>
          </div>
        </div>
        
        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>--> 
        <div style=" padd12px; text-align:center">
          <a href="prop.budget.rates.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.budget.nonpersonnel.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
    </div>
  </form>
</div>

<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">Add Personnel</h3>
  </div>
  <iframe src="prop.keypersonnel.add1.html" style="border:none; width:100%; height:400px;"></iframe>
</div>
<div class="modal hide fade" id="res-modal"></div>

<?php include( 'assets/inc/scripts.global.php' ) ?>

<script type="text/javascript">
    $(document).ready(function () {
		
		
		$(".ddd1, .ddd2, .ddd3, .ddd4").fadeOut(0);
		
		
		
	     $(".hhh1").hover(

    function () {
        $(".ddd1").fadeIn(200);
    },

    function () {
        $(".ddd1").fadeOut(200);
    });
	
		
	     $(".hhh2").hover(

    function () {
        $(".ddd2").fadeIn(200);
    },

    function () {
        $(".ddd2").fadeOut(200);
    });
		
			
	     $(".hhh3").hover(

    function () {
        $(".ddd3").fadeIn(200);
    },

    function () {
        $(".ddd3").fadeOut(200);
    });
		
		     $(".hhh4").hover(

    function () {
        $(".ddd4").fadeIn(200);
    },

    function () {
        $(".ddd4").fadeOut(200);
    });
		
		
		
		
        $('#jobcode, #apptype, #basesal, #saleff').editable();
		
		

		
		
	
        $(".disclose-dl1").toggle(

        function () {
            $("#dl1").slideDown(600);
            $(".disclose-dl1").html("hide");
        },

        function () {
            $("#dl1").slideUp(600);
            $(".disclose-dl1").html("show");
        });
        $(".disclose-dl2").toggle(

        function () {
            $("#dl2").slideDown(600);
            $(".disclose-dl2").html("hide");
        },

        function () {
            $("#dl2").slideUp(600);
            $(".disclose-dl2").html("show");
        });
        $(".disclose-dl3").toggle(

        function () {
            $("#dl3").slideDown(600);
            $(".disclose-dl3").html("hide");
        },

        function () {
            $("#dl3").slideUp(600);
            $(".disclose-dl3").html("show");
        });
        $(".disclose-dl4").toggle(

        function () {
            $("#dl4").slideDown(600);
            $(".disclose-dl4").html("hide");
        },

        function () {
            $("#dl4").slideUp(600);
            $(".disclose-dl4").html("show");
        });
		
		$(".disclose-dl5").toggle(

        function () {
            $("#dl5").slideDown(600);
            $(".disclose-dl5").html("hide");
        },

        function () {
            $("#dl5").slideUp(600);
            $(".disclose-dl5").html("show");
        });
		
		
		
    });

  $(function() {
    $( "#sortable1" ).sortable({
      items: "li:not(.ui-state-disabled)"
    });
 
    $( "#sortable2" ).sortable({
      cancel: ".ui-state-disabled"
    });
 
    $( "#sortable1 li, #sortable2 li" ).disableSelection();		
  });
  
  
  $(".ajax-modal").live('click', function() {
    var url = $(this).attr('url');
    var modal_id = $(this).attr('data-controls-modal');
    $("#" + modal_id).load(url).modal('show');
	
	
	
	
	
	
	
});
  </script>

</body>
</html>