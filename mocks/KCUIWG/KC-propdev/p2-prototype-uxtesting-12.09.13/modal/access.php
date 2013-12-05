<?php

# Includes
require_once( 'inc/head.php' );
?>

<div class="modal-dialog">
	<div class="modal-content">
    <div class="modal-header">
      <h3 id="myModalLabel">Access</h3>
    </div>
    <form  class="form-horizontal" method="get" action="">
      <div class="modal-body" style="min-height:400px"><div class="uif-toolbar" style="padding-top:0px">
          <a href="access.adduser.php" class="btn btn-default btn-xs"><i class="icon icon-user"></i> Add User</a>
        </div>
         <table class="table table-condensed">
          <tr>
            <th scope="col" style="width:25%">User</th>
            <th scope="col" style="width:70%">Roles</th>
            <th scope="col" style="width:5%; ">Actions</th>
          </tr>
          <tr>
            <td>Edward H Haskell</td>
            <td><select name="keywords" id="roles1" class="form-control input-sm col-md-8" multiple>
                <option >Reviewer</option>
                <option >Budget Creator</option>
                <option>Narritive Writer</option>
                <option >Aggregator</option>
                <option >Delete Proposal</option>
                <option >View Institutional Salaries</option>
                <option >View Proposal Salaries</option>
                <option >Approver</option>
              </select></td>
            <td style=""> <a href="" class="btn btn-xs btn-default">Remove</a></td>
          </tr>
          <tr>
            <td>Ward Cleaver</td>
            <td><select name="keywords" id="roles2" class="form-control input-sm col-md-8" multiple>
                <option >Reviewer</option>
                <option >Budget Creator</option>
                <option>Narritive Writer</option>
                <option >Aggregator</option>
                <option >Delete Proposal</option>
                <option >View Institutional Salaries</option>
                <option >View Proposal Salaries</option>
                <option >Approver</option>
              </select></td>
                     <td style=""> <a href="" class="btn btn-xs btn-default">Remove</a></td>
          </tr>
          <tr>
           <td>Jane Smith</td>
            <td><select name="keywords" id="roles3" class="form-control input-sm col-md-8" multiple>
               <option >Reviewer</option>
               <option >Budget Creator</option>
               <option>Narritive Writer</option>
               <option >Aggregator</option>
               <option >Delete Proposal</option>
               <option >View Institutional Salaries</option>
               <option >View Proposal Salaries</option>
               <option >Approver</option>
             </select></td>
           <td style=""> <a href="" class="btn btn-xs btn-default">Remove</a></td>
         </tr>
        </table>
      </div>
      
       <!-- <div class="modal-footer"> <a class="btn btn-primary" href="access.adduser.php">Edit</a> </div>-->
     
    </form>
  </div>
</div>

<?php

# Includes
require_once( 'inc/footer.php' );
?>