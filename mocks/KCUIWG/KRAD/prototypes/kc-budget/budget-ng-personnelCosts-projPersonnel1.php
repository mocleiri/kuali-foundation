<?php
$section = 'personnel';
$page = 'personnel-costs';
?>
<!DOCTYPE HTML>
<html lang=en>
<head>
<meta charset=UTF-8>
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
<style>
.dropdown-large {
 position:;
}
.dropdown-menu-large {
	margin-left: 16px;
	margin-right: 16px;
	padding: 20px 0px;
}
.dropdown-menu-large > li > ul {
	padding: 0;
	margin: 0;
}
.dropdown-menu-large > li > ul > li {
	list-style: none;
}
.dropdown-menu-large > li > ul > li > a {
	display: block;
	padding: 3px 20px;
	clear: both;
	font-weight: normal;
	line-height: 1.428571429;
	color: #333333;
	white-space: normal;
}
.dropdown-menu-large > li ul > li > a:hover, .dropdown-menu-large > li ul > li > a:focus {
	text-decoration: none;
	color: #262626;
	background-color: #f5f5f5;
}
.dropdown-menu-large .disabled > a, .dropdown-menu-large .disabled > a:hover, .dropdown-menu-large .disabled > a:focus {
	color: #999999;
}
.dropdown-menu-large .disabled > a:hover, .dropdown-menu-large .disabled > a:focus {
	text-decoration: none;
	background-color: transparent;
	background-image: none;
 filter: progid:DXImageTransform.Microsoft.gradient(enabled = false);
	cursor: not-allowed;
}
.dropdown-menu-large .dropdown-header {
	color: #428bca;
	font-size: 18px;
}
@media (max-width: 768px) {
.dropdown-menu-large {
	margin-left: 0;
	margin-right: 0;
}
.dropdown-menu-large > li {
	margin-bottom: 30px;
}
.dropdown-menu-large > li:last-child {
	margin-bottom: 0;
}
.dropdown-menu-large .dropdown-header {
	padding: 3px 15px !important;
}
}
</style>
</head>
<body id="Uif-Application" style="padding-bottom: 570px;">
<!-- APPLICATION HEADER -->
<header id="Uif-ApplicationHeader-Wrapper" data-sticky=true class="uif-sticky" style="position:fixed; left: 0; top: 0px;">
    <header id="u1xj79g4" class="uif-applicationHeader">
        <div class="container">
            <nav id="u1osy4lo" class="navbar" role=navigation>
                <div class="navbar-header">
                    <button type=button class="navbar-toggle" data-toggle=collapse data-target=.navbar-ex1-collapse><span class="sr-only">Toggle navigation</span> <span class="icon-reorder"></span></button>
                    <a class="navbar-brand" href="index.php">
                    <div class="logoBrand">
                        <h1><img id="u2elq10" src="http://ux.kuali.org/prototypes/kc/p2-devbranch/themes/kc/img/logo.png" alt="" class="uif-image"></h1>
                    </div>
                    </a></div>
                <div id="u1gk19wq" class="collapse navbar-collapse navbar-ex1-collapse uif-listGroup">
                    <ul class="nav navbar-nav navbar-right uif-listLayout">
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Researcher </a>
                            <div id="uyedx8c" class="dropdown-menu uif-cssGridGroup" style="min-width: 300px; right: -180px;">
                                <div class="row ">
                                    <div class="col-md-12">
                                        <section id="u9tj3ss" class="uif-listGroup">
                                            <h3 class="uif-headerText">Proposal Development</h3>
                                            <ul class="uif-listLayout">
                                                <li><a href="prop-start.php" class="uif-actionLink" id="umdwwyj" tabindex=0 data-role=Action> Create a Proposal </a></li>
                                                <li><a href="#" class="uif-actionLink" id="umdwwze" tabindex=0 data-role=Action> Created a Proposal Budget</a></li>
                                            </ul>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Unit </a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Central Admin </a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Maintenance </a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle=dropdown> System Admin </a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div id="upils8b" class="uif-cssGridGroup toolbar">
            <div class="container">
                <div class="row ">
                    <div class="col-md-12">
                        <div id="u1i3m5yh" class="uif-listGroup" data-parent=upils8b>
                            <ul class="uif-listLayout nav pull-right">
                                <li class="pull-right"><a href="#" class="uif-actionLink" id="u1o09qku" tabindex=0 data-role=Action data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-ActionListView&quot;);"> Action List </a></li>
                                <li class="pull-right"><a href="#" class="uif-actionLink" id="u1o09qlp" tabindex=0 data-role=Action data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-DocSearchView&quot;);"> Doc Search </a></li>
                                <li class="dropdown pull-right"><a href="#" class="dropdown-toggle" data-toggle=dropdown> Backdoor Login <span class="caret"></span> </a>
                                    <ul class="dropdown-menu uif-listLayout">
                                        <li><a href="#" class="uif-actionLink" id="u101xf6k" tabindex=0 data-role=Action data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-BootstrapComponents&quot;);"> Preferences </a></li>
                                        <li><a href="#" class="uif-actionLink" id="u101xf7f" tabindex=0 data-role=Action data-onclick="redirect(&quot;../kr-krad/labs?methodToCall=start&amp;viewId=Lab-BootstrapComponents&quot;);"> Logout </a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- Backdoor info (here to inherit stickyness with the header, if set) --></header>
<form id="kualiForm" action="../kr-krad/uicomponents" method=post accept-charset=UTF-8>
    <!-- VIEW -->
    <div id="LabsProposal" class="clearfix uif-formView" data-role=View style="margin-top: 75px;"><!-- BREADCRUMBS --><!-- VIEW HEADER -->
	         <!-- VIEW HEADER --> 
	 		<?php include ('includes/uif-viewHeader.php') ?>
	 		<!-- // VIEW HEADER -->
	 <!-- VIEW CONTENT -->
        <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container"><!-- VIEW NAVIGATION -->
            <?php include ('includes/uif-navigation-budget.php') ?>
            <div id="Uif-BreadcrumbUpdate" style="display:;"></div>
            <main id="LabsProposal-Page" class="uif-page uif-hasLeftNav" data-server_messages="false" data-role="Page" data-parent="LabsProposal" style="margin-left: 235px;">
                	<header class="clearfix uif-header-contentWrapper">
                    <div id="u148pgf0" class="uif-pageHeader clearfix" data-header_for="LabsProposal-Page">
                        <div class="row">
                            <div class="col-md-6">
                                <h2 class="uif-headerText"> <span class="uif-headerText-span"> Project Personnel </span> </h2>
                            </div>
                            <div class="col-md-6 uif-pagetools"> <a href="#" class="btn btn-default btn-xs pull-right" data-toggle="modal" data-target="#modal-add-personnel1"><span aria-hidden="true" class="icon-plus"></span> Add People</a> </div>
                        </div>
                    </div>
                </header>
                <p id="u1iaxrzf" class="uif-message uif-boxLayoutVerticalItem clearfix">Personnel added to the proposal are shown below. Review, configure, and add additional personnel to the budget.</p>
                <br>
                <table class="table table-condensed table-bordered uif-lightTable dataTable" id="Demo-LightTableGroup1_lightTable" aria-describedby="Demo-LightTableGroup1_lightTable_info">
                    <thead>
                        <tr role="row">
                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="
 Field 1: : activate to sort column "><label id="urh9zx8" class="uif-label">Person</label></th>
                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 2: : activate to sort column ">Job Code</th>
                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 3: : activate to sort column ">Appointment Type</th>
                            <th class="" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column ascending">Base Salary</th>
                            <th class="" style="width:103px" role="columnheader" tabindex="0" aria-controls="Demo-LightTableGroup1_lightTable" rowspan="1" colspan="1" aria-label="
 Field 4: : activate to sort column "></th>
                    </tr></thead>
                    <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <tr class="not-deletable active">
                            <td colspan="5"><strong class="pull-left">From Proposal Development</strong> <a href="#" class="btn btn-default btn-xs pull-right" data-toggle="">Sync from Proposal </a></td>
                        </tr>
                        <tr class="not-deletable">
                            <td>Ward Cleaver <small class="text-muted">(PI)</small></td>
                            <td>Associate Provost <small class="text-muted">(AA014)</small></td>
                            <td>12 month</td>
                            <td class="text-right">$247,457</td>
                            <td><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a> <a href="#" class="btn btn-default btn-xs" data-toggle=""><span class="sr-only">Delete</span> <span aria-hidden="true" class="icon-trash"></span></a>
                                    <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;
left: -808px;
top: 26px;
width: 890px;
padding: 15px;
background: #ffffff;">
        <p class="lead">Ward Cleaver</p>
        <hr>
        <div class="row">
        
        
        
        
            <div class="col-md-6">
                <div class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-6 control-label">Role:</label>
                        <div class="col-sm-6">
                            <p class="form-control-static">Principle Investigator</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Job Code:</label>
                        <div class="col-sm-6">
                            <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                <div class="input-group">
                                    <input id="u11k8c4j_control" type="text" name="field3" value="Associate Provost (AA014)" size="30" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                    <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-search" tabindex="0" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act"></a> </span> </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Appointment Type:</label>
                        <div class="col-sm-6">
                            <select id="u11k8c5e_control" name="field4" size="1" class="form-control input-sm uif-dropdownControl " data-role="Control" data-control_for="u11k8c5e">
                                <option value="1">TEMPORARY EMPLOYEE</option>
                                <option value="2">SUMMER EMPLOYEE</option>
                                <option value="3">9M DURATION</option>
                                <option value="4">10M DURATION</option>
                                <option value="5">11M DURATION</option>
                                <option value="6" selected="selected">12M DURATION</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Salary Effective Date:</label>
                        <div class="col-sm-6">
                            <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                <div class="input-group">
                                    <input id="u11k8c4j_control" type="text" name="field3" value="12/15/2013" size="" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                    <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-calendar" tabindex="0" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act"></a> </span> </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Salary Annivesary Date:</label>
                        <div class="col-sm-6">
                            <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                <div class="input-group">
                                    <input id="u11k8c4j_control" type="text" name="field3" value="12/15/2013" size="" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                    <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-calendar" tabindex="0" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act"></a> </span> </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Base Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                   
                </div>
            </div>
            
            
            
            
            
            <div class="col-md-6">
                <div class="form-horizontal" role="form">
                
                
                      
                 <!--        <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                    
                    
                      
                         <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                      
                         <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                      
                         <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                      
                         <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                    -->
                    
                    
                    
                    
                    <div class="form-group by-period">
                        <div class="col-sm-6 control-label"> <strong>Salary by period</strong> <br>
<small class="uif-disabled">(optional) </small></div><small class="uif-disabled">
                        <div class="col-sm-6">
                        
                        
                        
                  
                    
                    
                    
                    
                    
                    
                    
                    
                            <table class="table">
                                <tbody><tr>
                                    <th scope="col">Period</th>
                                    <th scope="col">Salary</th>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><label for="period_p1_s1"><span class="sr-only">Period one salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td><label for="period_p1_s2"><span class="sr-only">Period two salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td><label for="period_p1_s3"><span class="sr-only">Period three salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td><label for="period_p1_s4"><span class="sr-only">Period four salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td><label for="period_p1_s5"><span class="sr-only">Period five salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                
                                 <tr> <td colspan="2"><a href="#" class="btn btn-xs btn-default pull-right">Calculate</a></td>
                                    
                                </tr></tbody></table>
                        </div>
                    </small></div><small class="uif-disabled">
                    
                    
                </small></div><small class="uif-disabled">
                
                    
            </small></div><small class="uif-disabled">                
               
                    

        </small></div><small class="uif-disabled">

<hr>
                   <div class="text-center"> <a href="#" class="btn btn-default btn-sm">Cancel</a> <a href="#" class="btn btn-primary btn-sm">Save Changes</a> </div>
    </small></div><small class="uif-disabled">
                                </small></div></td>
                        </tr>
                        <tr class="not-deletable">
                            <td>John Coltrane <small class="text-muted">(Co-PI)</small></td>
                            <td>Dean/Faculty <small class="text-muted">(AA042)</small></td>
                            <td>12 month</td>
                            <td class="text-right">$163,771</td>
                            <td><div class="dropdown dropdown-large"><a href="#" class="dropdown-toggle btn btn-default btn-xs" data-toggle="dropdown">Details <b class="caret"></b></a> <a href="#" class="btn btn-default btn-xs" data-toggle=""><span class="sr-only">Delete</span> <span aria-hidden="true" class="icon-trash"></span></a>
                                    <div class="dropdown-menu dropdown-menu-large row" style="position: absolute;
left: -808px;
top: 26px;
width: 890px;
padding: 15px;
background: #ffffff;">
        <p class="lead">John Coltrane</p>
        <hr>
        <div class="row">
        
        
        
        
            <div class="col-md-6">
                <div class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-6 control-label">Role:</label>
                        <div class="col-sm-6">
                            <p class="form-control-static">Principle Investigator</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Job Code:</label>
                        <div class="col-sm-6">
                            <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                <div class="input-group">
                                    <input id="u11k8c4j_control" type="text" name="field3" value="Associate Provost (AA014)" size="30" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                    <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-search" tabindex="0" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act"></a> </span> </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Appointment Type:</label>
                        <div class="col-sm-6">
                            <select id="u11k8c5e_control" name="field4" size="1" class="form-control input-sm uif-dropdownControl " data-role="Control" data-control_for="u11k8c5e">
                                <option value="1">TEMPORARY EMPLOYEE</option>
                                <option value="2">SUMMER EMPLOYEE</option>
                                <option value="3">9M DURATION</option>
                                <option value="4">10M DURATION</option>
                                <option value="5">11M DURATION</option>
                                <option value="6" selected="selected">12M DURATION</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Salary Effective Date:</label>
                        <div class="col-sm-6">
                            <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                <div class="input-group">
                                    <input id="u11k8c4j_control" type="text" name="field3" value="12/15/2013" size="" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                    <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-calendar" tabindex="0" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act"></a> </span> </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Salary Annivesary Date:</label>
                        <div class="col-sm-6">
                            <div id="u11k8c4j" class="uif-inputField" data-parent="u14jg6xp" data-role="InputField" data-label="Award ID">
                                <div class="input-group">
                                    <input id="u11k8c4j_control" type="text" name="field3" value="12/15/2013" size="" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                    <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-calendar" tabindex="0" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act"></a> </span> </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">Base Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                   
                </div>
            </div>
            
            
            
            
            
            <div class="col-md-6">
                <div class="form-horizontal" role="form">
                
                
                      
                 <!--        <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                    
                    
                      
                         <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                      
                         <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                      
                         <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                      
                         <div class="form-group">
                        <label for="inputPassword3" class="col-sm-6 control-label">P1 Salary:</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="inputPassword3" value="247457.00">
                        </div>
                    </div>
                    -->
                    
                    
                    
                    
                    <div class="form-group by-period">
                        <div class="col-sm-6 control-label"> <strong>Salary by period</strong> <br>
<small class="uif-disabled">(optional) </small></div><small class="uif-disabled">
                        <div class="col-sm-6">
                        
                        
                        
                  
                    
                    
                    
                    
                    
                    
                    
                    
                            <table class="table">
                                <tbody><tr>
                                    <th scope="col">Period</th>
                                    <th scope="col">Salary</th>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td><label for="period_p1_s1"><span class="sr-only">Period one salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td><label for="period_p1_s2"><span class="sr-only">Period two salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td><label for="period_p1_s3"><span class="sr-only">Period three salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td><label for="period_p1_s4"><span class="sr-only">Period four salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td><label for="period_p1_s5"><span class="sr-only">Period five salary</span></label>
                                        <input type="text" class="input-sm" value="$0.00"></td>
                                </tr>
                                
                                 <tr>  <td colspan="2"><a href="#" class="btn btn-xs btn-default pull-right">Calculate</a></td>
                                </tr></tbody></table>
                        </div>
                    </small></div><small class="uif-disabled">
                    
                    
                </small></div><small class="uif-disabled">
                
                    
            </small></div><small class="uif-disabled">                
               
                    

        </small></div><small class="uif-disabled">

<hr>
                   <div class="text-center"> <a href="#" class="btn btn-default btn-sm">Cancel</a> <a href="#" class="btn btn-primary btn-sm">Save Changes</a> </div>
    </small></div>
                                </div></td>
                        </tr>
                    </tbody>
                </table>
                <!--<a href="#" class="btn btn-default btn-xs pull-right" data-toggle="modal" data-target="#modal-add-personnel"><span aria-hidden="true" class="icon-plus"></span> Add More People</a>-->
              
                </main>
        </div>

    <div id="" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer="true" data-parent="LabsProposal" style="position:fixed; left: 0; bottom: 0px;">
        <div class="uif-footer-centered-control-group clearfix">
            <div class="global-navigate btn-group">
                <button type="button" href="budget-ng-rates.php" id="" class="btn btn-default"><span class="icon-chevron-left"></span> Back</button>
                    <button type="button" href="budget-ng-personnelCosts-persPeriod.php" id="save-continue" class="btn btn-primary">Continue <span class="icon-chevron-right"></span></button>
                </div>

                <div class="global-actions btn-group">

                <button type="button" id="" class="btn btn-default">Save</button>
                <button type="button" id="" class="btn btn-default">Reload</button>
                <button type="button" id="" class="btn btn-default">Complete Budget</button>
            </div>
        </div>
    </div>

        <!-- DIALOGS/Placeholders --></div>
    <span id="formInfo">
    <input type=hidden name="viewId" value=LabsProposal>
    <input type=hidden name="formKey" value=2e468a13-a495-44cc-acd7-aac6b2ed97a0>
    <input type=hidden name="requestedFormKey" value=2e468a13-a495-44cc-acd7-aac6b2ed97a0>
    <input type=hidden name="sessionId" value=CAFCAB4387CB97D8567359A8C37D7712>
    <input type=hidden name="flowKey" value="">
    <input type=hidden name="view.applyDirtyCheck" value=true>
    <input type=hidden name="dirtyForm" value=false>
    <input type=hidden name="renderedInLightBox" value=false>
    <input type=hidden name="view.singlePageView" value=true>
    <input type=hidden name="view.disableBrowserCache" value=true>
    </span>
    
</form>
<?php include ('includes/footer-scripts.php') ?>
<!-- MODAL  budget summary -->
<?php include ('includes/modal-budget-summary.php') ?>
<!-- MODAL budget settings  -->
<?php include ('includes/modal-budget-settings.php') ?>
<!-- MODAL  budget open propsal -->
<?php include ('includes/modal-budget-open-prop.php') ?>


<script>

//replacing the "version 5"

(function($){
  		

			
 $("#save-continue").click(function (e) {	
	  //e.preventDefault();		
			
		
  });

	  

}(jQuery))


</script>









  <!-- MODAL ADD PERSONNEL -->
<?php include ('includes/modal-budget-add-personnel.php') ?>
<?php include ('includes/modal-budget-add-personnel1.php') ?>
<?php include ('includes/modal-budget-add-personnel1-results.php') ?>
<?php include ('includes/modal-budget-add-personnel1-results-tbn.php') ?>
    

<!-- MODAL BUDGET VERSIONS -->
<?php include ('includes/modal-budget-versions.php') ?>
 

<!--
<div id="modal-add-personnel" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true" aria-labbeledby="create-new-title">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="#" method="#">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="create-new-label">Add budget personnel</h4>
                </div>
                <div class="modal-body">
                    <form action="#" method="#" class="form-horizontal">
                        <div class="clearfix">
                            <div class="row uif-bottom-shadow uif-indicator-arrow-bottom-center">
                                <div class="col-md-3">
                                    <div class="uif-personnel-search-for">
                                        <fieldset>
                                            <legend>Search for:</legend>
                                            <label for="employee"><input type="radio" name="personnel-search-for" id="personnel-search-for-employee"> Employee</label>
                                            <label for="nonemployee"><input type="radio" name="personnel-search-for" id="personnel-search-for-nonemployee"> Non-Employee</label>
                                            <label for="tbn"><input type="radio" name="personnel-search-for" id="personnel-search-for-tbn"> TBN</label>
                                        </fieldset>
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="uif-personnel-search-form active" id="personnel-search-form-employee">
                                        <fieldset>
                                            <legend>Search by:</legend>
                                            <div class="col-md-3">
                                                <div class="form-group">
                                                    <label for="personnel-search-by-type" class="control-label"><span class="sr-only">Search by:</span></label>
                                                    <select name="personnel-search-by-type" id="personnel-search-by-type" class="form-control">
                                                        <option value="first">First name</option>
                                                        <option value="last">Last name</option>
                                                        <option value="email">Email address</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-9">
                                                <div class="form-group">
                                                    <label for="personnel-search-by-text" class="control-label"><span class="sr-only">Search for</span></label>
                                                    <input type="text" name="personnel-search-by-text" id="personnel-search-by-text" class="form-control">
                                                    <button class="btn btn-default">Search</button>
                                                    <p class="clearfix text-faded">Results from your search will appear in the box below.</p>
                                                </div>
                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                            </div>
                            <div class="row uif-bg-shaded">
                                <div class="col-md-5">
                                    <div class="uif-padding-top uif-padding-top-2x uif-padding-left uif-padding-right uif-padding-bottom">
                                        <h5>We found <span id="found-total">13 <span id="search-type">Employee</span>'s' with the first name "<span id="search-text">john</span>":</h5>
                                        <p class="text-faded">Select personnel to add to the budget.</p>
                                        <textarea class="sr-only" id="personnel-add-box-1"></textarea>
                                        <div class="uif-collection-box" id="personnel-add-box-1-visible" data-dom-update="personnel-add-box-1">
                                            <ul>
                                                <li>
                                                    <label for="personnel-result-1">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-1">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-2">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-2">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-3">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-3">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-4">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-4">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-5">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-5">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-6">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-6">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-7">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-7">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <button class="btn btn-default btn-sm"><span class="icon icon-plus"></span><span class="sr-only">Add to list</span></button>
                                    <button class="btn btn-default btn-sm"><span class="icon icon-minus"></span><span class="sr-only">Remove from list</span></button>
                                </div>
                                <div class="col-md-5">
                                    <div class="uif-padding-top uif-padding-top-2x uif-padding-left uif-padding-right uif-padding-bottom">
                                        <h5>You are adding the following additional personnel to this budget:</h5>
                                        <p class="text-faded">You will be able to modify them later.</p>
                                        <textarea class="sr-only" id="personnel-add-box-1"></textarea>
                                        <div class="uif-collection-box" id="personnel-add-box-1-visible" data-dom-update="personnel-add-box-1">
                                            <ul>
                                                <li>
                                                    <label for="personnel-result-1">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-1">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                                <li>
                                                    <label for="personnel-result-2">
                                                        <input type="checkbox" name="personnel-result" id="personnel-result-2">
                                                        <span class="result-name">John Smith</span>
                                                        <span class="result-title text-faded">Research Scientist</span>
                                                        <span class="pull-right result-type">Employee</span>
                                                    </label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div id="u19v7dpm" class="uif-footer clearfix uif-stickyFooter uif-stickyButtonFooter" data-sticky_footer=true data-parent=LabsProposal style="position:fixed; left: 0; bottom: 0px;">
                    <a href="budget-ng-rates.php" id="ufuknm4" class="btn btn-default uif-secondaryActionButton uif-boxLayoutHorizontalItem"><span class="icon-chevron-left"></span> Back</a>
                    <a href="budget-ng-personnelCosts-persPeriod1.php" id="ufuknm4" class="btn btn-primary uif-primaryActionButton uif-boxLayoutHorizontalItem">Continue <span class="icon-chevron-right"></span></a>
                </div>
            </form>
        </div>
    </div>
-->
<!-- NO QUOTES
<div class="modal fade" id="summary" tabindex=-1 role=dialog aria-labelledby=myModalLabel aria-hidden=true><div class="modal-dialog"><div class="modal-content"><div class="modal-header"><button type=button class="close" data-dismiss=modal aria-hidden=true>&times;</button><h4 class="modal-title" id="myModalLabel">Summary</h4></div><div class="modal-body"><p>Here's a summary of your current budget.</p><table class="table table-condensed credit-allocation"><tbody><tr><th>&nbsp;</th><th>P1</th><th>P2</th><th>P3</th><th>P4</th><th>P5</th><th>Totals</th></tr><tr class="active"><td><strong> Personnel</strong></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr class=""><td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Salary</a></td><td>156934</td><td>156934</td><td>156934</td><td>156934</td><td>156934</td><td>784670</td></tr><tr class=""><td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Fringe</a></td><td>37345</td><td>37345</td><td>37345</td><td>37345</td><td>37345</td><td>186725</td></tr><tr class=""><td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Calculated Direct Costs</a></td><td>28284</td><td>28284</td><td>28284</td><td>28284</td><td>28284</td><td>141420</td></tr><tr class=""><td>Personnel Subtotal</td><td><strong>222563</strong></td><td><strong>222563</strong></td><td><strong>222563</strong></td><td><strong>222563</strong></td><td><strong>222563</strong></td><td><strong>837456</strong></td></tr><tr class="active"><td><strong> Non-personnel</strong></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr class=""><td><a href="#"> <span aria-hidden=true class="icon-chevron-right"></span> Calculated Direct Costs</a></td><td>38546</td><td>38546</td><td>38546</td><td>38546</td><td>38546</td><td>219348</td></tr><tr class=""><td>Nonpersonnel Subtotal</td><td><strong>38546</strong></td><td><strong>38546</strong></td><td><strong>38546</strong></td><td><strong>38546</strong></td><td><strong>38546</strong></td><td><strong>219348</strong></td></tr><tr class="active"><td><strong> Totals</strong></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>Total Direct Cost</td><td>723454</td><td>723454</td><td>723454</td><td>723454</td><td>723454</td><td>496432</td></tr><tr><td>Total F&amp;A Costs</td><td>34537</td><td>34537</td><td>34537</td><td>34537</td><td>34537</td><td>154578</td></tr><!-- tr>
            <td colspan="5" >Totals</td>
          </tr> --><!-- 
                        <tr class="active">
                            <td>Total Costs</td>
                            <td class=""><strong>238546</strong></td>
                            <td class=""><strong>238546</strong></td>
                            <td class=""><strong>238546</strong></td>
                            <td class=""><strong>238546</strong></td>
                            <td class=""><strong>238546</strong></td>
                            <td class=""><strong>2219348</strong></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type=button class="btn btn-default" data-dismiss=modal>Close</button>
            </div>
        </div>
    </div>
</div>
 --><!-- NO QUOTES
<div class="modal fade" id="budgetSettings" tabindex=-1 role=dialog aria-labelledby=myModalLabel aria-hidden=true>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type=button class="close" data-dismiss=modal aria-hidden=true>&times;</button>
                <h4 class="modal-title" id="myModalLabel">Budget Settings</h4>
            </div>
            <div class="modal-body">
                <div class="form-horizontal uif-cssGridGroup" role=form>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Project Start</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">10/21/2014</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Project End</label>
                        <div class="col-sm-9">
                            <p class="form-control-static">10/20/2019</p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Total Direct Cost Limit</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Budget Status</label>
                        <div class="col-sm-9">
                            <select class="form-control">
                                <option>Complete</option>
                                <option>Incomplete</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">On/Off Campus</label>
                        <div class="col-sm-9">
                            <select class="form-control">
                                <option>All On</option>
                                <option>All Off</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Residual Funds</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Total Cost Limit</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">Unrecovered F&A Rate Type</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for=inputEmail3 class="col-sm-3 control-label">F&A Rate Type:</label>
                        <div class="col-sm-9">
                            <input type=text class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type=checkbox>
                                    Modular Budget </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type=checkbox>
                                    Submit Cost Sharing </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <label>
                                    <input type=checkbox>
                                    Submit this budget with your proposal </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type=button class="btn btn-default" data-dismiss=modal>Close</button>
                <button type=button class="btn btn-primary">Apply Changes</button>
            </div>
        </div>
    </div>
</div>
 --><!--NO QUOTES
<div class="modal fade" id="switchdoc" tabindex=-1 role=dialog aria-labelledby=myModalLabel aria-hidden=true>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type=button class="close" data-dismiss=modal aria-hidden=true>&times;</button>
                <h4 class="modal-title" id="myModalLabel">Open Proposal</h4>
            </div>
            <div class="modal-body">
                <p>You are about to open the proposal doument for this budget. Are you sure you want to do this?</p>
                <small>
                <label style=font-weight:normal>
                    <input type=checkbox>
                    Dont ask me this again </label>
                </small></div>
            <div class="modal-footer"><a href="" class="btn btn-default" data-dismiss=modal>Cancel</a> <a href="prop-basics-details.php" class="btn btn-primary">Open Proposal Development Document</a></div>
        </div>
    </div>
</div>

Modal -->
</body>
</html>