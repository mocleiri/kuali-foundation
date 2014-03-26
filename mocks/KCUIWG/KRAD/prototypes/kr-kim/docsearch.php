<?php
$section = 'asdf';
$page = 'asdf';
?>

<!DOCTYPE HTML>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
<style type="text/css"></style>
</head>

<body id="Uif-Application">
<div style="width:1200px; margin-left:auto; margin-right:auto; margin-top:100px"> 
    <!-- Modal --> 
    
    <!-- Modal --> 
    
    <!--<div class="modal fade" id="switchdoc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">-->
    
    <div class="modal-dialog" style="width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"> Document Search</h4>
            </div>
            <div class="modal-body uif-cssGridSection">
                <div class="form-horizontal" role="form">
                    <div class="row ">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-2 control-label">Document Type</label>
                                <div class="col-md-10">
                                    <div class="input-group">
                                        <input id="u11k8c4j_control" type="text" name="field3" value="" size="30" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                       
                                        <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-search" tabindex="0"></a> </span> </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-2 control-label">Items per Page</label>
                                <div class="col-md-10">
                                    <select class="form-control">
                                        <option value="Primary Delegates only on Filter Page" selected>10</option>
                                        <option value="Primary Delegates on Action List Page">25</option>
                                        <option value="Primary Delegates on Action List Page">100</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-2 control-label">Delegator Filter</label>
                                <div class="col-md-4">
                                    <select class="form-control">
                                        <option value="Secondary Delegators only on Filter Page">Secondary Delegators only on Filter Page</option>
                                        <option value="Secondary Delegators on Action List Page" selected="selected">Secondary Delegators on Action List Page</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-2 control-label">Primary Delegate Filter</label>
                                <div class="col-md-4">
                                    <select class="form-control">
                                        <option value="Primary Delegates only on Filter Page">Primary Delegates only on Filter Page</option>
                                        <option value="Primary Delegates on Action List Page" selected="selected">Primary Delegates on Action List Page</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer"> <a href="" class="btn btn-primary" data-dismiss="">Save Changes</a> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> </div>
            </div>
        </div>
    </div>
</div>

<!--</div>--> 

<!-- end Modal --> 

<!-- end Modal -->
</div>
<?php include ('includes/footer-scripts.php') ?>
<!-- Modal --><!-- end Modal -->

</body>
</html>