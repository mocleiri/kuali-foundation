<?php
# Variables
$page = 'basics-sponsor';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<body style="padding-top:0px; background:#FFF">
<script type='text/javascript'>
    //<![CDATA[ 
    $(window).load(function () {
        $("#link1").click(function () {
            $("#menu1").slideDown(200);
            $("#menu2").slideUp(200);
        });
        $("#menu1").mouseleave(function () {
            $("#menu1").slideUp(200);
        });
       
       
    });
	
	

	
	
	 //]]>
</script> 
<script type="text/javascript">
function toggleDiv(divId) {
   $("#"+divId).toggle();
   
   
   
   
}
</script>
<section>
  <div class="sectionContents">
  <div class="container"> 
        <h2>Breadcrumb Visual Styling</h2>
    
    <ol class="breadcrumbs">
      <li class="home"><a href="#"><span>Home</span></a></li>
      <li class="dropdown"><a href="#"><span>With dropdown</span></a><a class="toggle" href="javascript:toggleDiv('menu1');" data-toggle=""><span class="caret"></span></a>
        <div class="breadcrumbSubnav" id="menu1"> The content in this div will hide and show (toggle) when the toggle is pressed. This div will also go away on mouseOut. </div>
      </li>
      <li class="dropdown"><a href="#"><span>With dropdown</span></a><a class="toggle" href="javascript:toggleDiv('menu2');" data-toggle=""><span class="caret"></span></a>
        <div class="breadcrumbSubnav" id="menu2"> The content in this div will hide and show (toggle) when the toggle is pressed. This div will also go away on mouseOut. </div>
      </li>
      <li class=""><a href="#"><span>Without dropdown</span></a></li>
      <li><a href="#" class="current"><span>Current crumb</span></a></li>
    </ol>

  </div>
</section>

<!-- /container -->
</body>
</html>
