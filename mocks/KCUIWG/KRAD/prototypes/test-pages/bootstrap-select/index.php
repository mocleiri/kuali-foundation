<?php
function randomText($numchar)
{
    $word = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
    $array=explode(",",$word);
    shuffle($array);
    $newstring = implode($array,"");
    return substr($newstring, 0, $numchar);
}

?>
<!DOCTYPE html>
<html>
    <head>
    <title></title>
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="bootstrap-select.css">
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="bootstrap-select.js"></script>
    <style>
.container {
	margin: 20px;
}
</style>
    <script type="text/javascript">
        jQuery(document).ready(function () {

            jQuery('.selectpicker').selectpicker({
				'size':'10'
            });

			jQuery(document).on('click', 'dt', function(event) {
				var opts = jQuery(this).parent().nextUntil(':has(dt)').andSelf(); 
				var inactive = opts.filter(':not(.selected)'); 
				var toggleMe = inactive;
				if (inactive.length == 0) { 
					toggleMe = opts;
				}
				toggleMe.children('a').click();
				event.preventDefault();
				return false;
			});
        });
    </script>
    </head>
    <body>
<div class="container">
<h1>Bootstrap Select Demo</h1>
<form class="form-horizontal" role="form">
        <div class="form-group">
        <label for="bs3Select" class="col-lg-2 control-label">Test bootstrap 3 form</label>
        <div class="col-lg-3">
                <select id="bs3Select" class="selectpicker show-tick form-control" multiple data-live-search="true">
                <option>cow</option>
                <option>bull</option>
                <option class="get-class" disabled>ox</option>
                <optgroup label="test" data-subtext="another test" data-icon="icon-ok">
                    <option>ASD</option>
                    <option selected>Bla</option>
                    <option>Ble</option>
                    </optgroup>
                <?php 
							foreach (range(1, 100) as $number) { 
								echo optGroup($number);
								foreach (range(0, rand(3, 15)) as $n) { 
									echo optionTag();
								}
								echo optGroup(null, true);
							}
							
							function optGroup($number=0, $close=false){
								if($close) return "</option>";
								return  '<optgroup label="Category' . $number . '" data-subtext="'. $number. '" data-icon="icon-ok">';
							}
							
							function optionTag(){
								return "<option>" . randomText(7) . "</option>";
							}
						?>
            </select>
            </div>
    </div>
    </form>
</body>
</html>
