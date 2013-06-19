package org.kuali.maven.ec2;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteSnapshotRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsResult;
import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceBlockDeviceMapping;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.Snapshot;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.Filter;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

public class EC2CIBackup {
	   
	


	AmazonEC2Client ec2client;
	//public static void setUpBeforeClass() throws Exception {
	//}
    
//	private static final String ACCESSKEY = "AKIAJFD5IM7IPVVUEBNA";
//	private static final String SECRETYKEY = System.getProperty("secret.key");
	private static final String TagstoBackup  = System.getProperty("tags");  //arguments to pass
	
	public static final String ACCESS_KEY = "AKIAIZFPMJVCNOYYAZ2Q";
	public static final String SECRET_KEY_ENCRYPTED = "CUdUkYyNwfLUlNutPESFSI27k87kUiyMfmvdWyS4vsM2vH8UUms40LDmNi02qZiJmCP+cRCvsCQ=";
	
//	protected AWSCredentials getCredentials() {
//		//log.debug("access key: " + ACCESSKEY);
//		return new BasicAWSCredentials(ACCESSKEY, SECRETYKEY);
//	}
	
	/*public static AWSCredentials getEC2Credentials(String accessKey, String secretKey) {
        return new BasicAWSCredentials(accessKey, secretKey);
	}*/
        
	/* protected EC2Utils getEC2Utils() {
	        BasicTextEncryptor bte = new BasicTextEncryptor();
	        bte.setPassword(System.getProperty("kuali.master.password"));
	        String secretKey = bte.decrypt(SECRET_KEY_ENCRYPTED);
	        return EC2Utils.getInstance(ACCESS_KEY, secretKey);
	    }*/

	    protected AmazonEC2Client getEC2Client() {
	        BasicTextEncryptor bte = new BasicTextEncryptor();
	        bte.setPassword(System.getProperty("kuali.master.password"));
	       
	        String secretKey = bte.decrypt(SECRET_KEY_ENCRYPTED);
	        System.out.println("secretKey: "+secretKey);
	        return EC2Utils.getEC2Client(ACCESS_KEY, secretKey);
	    }

		
		 protected DescribeSnapshotsRequest getDescribeSnapshotsRequest(Tag tag) {
             DescribeSnapshotsRequest request = new DescribeSnapshotsRequest();
             System.out.println(request.getSnapshotIds());
             return request; }
             
		
		
		protected Filter getFilterFromTag(Tag tag) {
			Filter filter = new Filter();
			filter.setName("tag:" + tag.getKey());
			filter.setValues(Collections.singletonList(tag.getValue()));
			return filter;
		}
	
		public List<Snapshot> getSnapshotWithFilter(Filter filter){			
			//ec2client = getEC2Client();
		    DescribeSnapshotsRequest SnapshotRequest = new DescribeSnapshotsRequest();  
		    SnapshotRequest.setFilters(Collections.singletonList(filter));
	        DescribeSnapshotsResult SnapshotResult = ec2client.describeSnapshots(SnapshotRequest);
	        return SnapshotResult.getSnapshots();
		}
		
		 private  Tag CreateTag( String TagName, String TagValue) {
		      Tag ThisTag = new Tag();
			  ThisTag.setKey(TagName);
			  ThisTag.setValue(TagValue);		
			  return ( ThisTag);
	    }
		
	
		  
		 /* public  void addTagtoSnapshot( String TagName, String TagValue, String SnapshotID) {
			  ec2client = getEC2Client();
	        	List<Tag> tag =  new ArrayList<Tag>();
		        Tag ThisTag =  CreateTag(  TagName,  TagValue) ;	
				tag.add(ThisTag);
				CreateTagsRequest TagRequest = new CreateTagsRequest();
				TagRequest.setTags(tag);
				TagRequest.withResources(SnapshotID);
			    ec2client.createTags(TagRequest);
			   
		        }*/
		  
		  public  void addTagtoSnapshot( String SnapshotID, Tag tagToAdd ) {
			//  ec2client = getEC2Client();		 
			 List<Tag> tag = new ArrayList<Tag>();
			 tag.add(tagToAdd);
	          System.out.println("In addTagtoSnapshot");
				CreateTagsRequest TagRequest = new CreateTagsRequest();
				TagRequest.withTags(tag);
				TagRequest.withResources(SnapshotID);
			    ec2client.createTags(TagRequest);
			   
		        }
		  
		  
		    
		    public  void ReplaceSnapshotTag( String SnapshotID, Tag TagtoAdd) { 
		     	CreateTagsRequest TagRequest = new CreateTagsRequest();
				TagRequest.withTags(TagtoAdd);
				TagRequest.withResources(SnapshotID);
			    ec2client.createTags(TagRequest);
   		        System.out.println("ReplaceSnapshotTag: "+ SnapshotID+","+TagtoAdd);
		    	
		    }
				
		  
	       public String CreateBackUpSnapshot(String CI_volume, String Current_Snapshot_Description){
		        CreateSnapshotRequest NewSnapshotRequest = new CreateSnapshotRequest();
		        NewSnapshotRequest.setDescription(Current_Snapshot_Description);
		        NewSnapshotRequest.setVolumeId(CI_volume);
		        CreateSnapshotResult NewSnapshotResult = ec2client.createSnapshot(NewSnapshotRequest);
		        Snapshot NewSnapshot = NewSnapshotResult.getSnapshot();
		        String NewSnapshotID = NewSnapshot.getSnapshotId();
		        System.out.println("Creating New Snapshot "+NewSnapshotID);
		        return( NewSnapshotID);
	        }
	       
	       public void DeleteCIBackup02(String OldSnapshot){
		        DeleteSnapshotRequest deleteSnapshotRequest = new DeleteSnapshotRequest();
		        deleteSnapshotRequest.setSnapshotId(OldSnapshot);
		        ec2client.deleteSnapshot(deleteSnapshotRequest);
		        System.out.println("Deleting Old Snapshot "+OldSnapshot);
	        }
	       
	       public String  GetCIVolumeID(Filter ci_riceFilter){
	   	     int ThereCanBeOnlyOne= 0; //There is only one instance which has this tag
	         DescribeInstancesRequest request = new DescribeInstancesRequest();
	         DescribeInstancesResult result = ec2client.describeInstances(request.withFilters(ci_riceFilter));
	         List<Reservation> reservations = result.getReservations();
	         Reservation reservation = reservations.get(ThereCanBeOnlyOne);
	         List<Instance> instances = reservation.getInstances();
	         List<InstanceBlockDeviceMapping> BDM = instances.get(ThereCanBeOnlyOne).getBlockDeviceMappings();
	       	 InstanceBlockDeviceMapping CI_BDM = BDM.get(ThereCanBeOnlyOne);
	         String CI_volume = CI_BDM.getEbs().getVolumeId();
	         System.out.println("Instance Volume "+CI_volume);
	         return (CI_volume);
	           }
	        
	@Test
	public void execute(  ) {
		
		ec2client = getEC2Client();
		
	String args[] = TagstoBackup.split(",");  //This is the number of directories returned.
    String tag;
    String CIBACKUP;

	for (String arg : args) {
       tag = arg;   // name tag value
       CIBACKUP = arg.toUpperCase(); //CIBACKUP TAG value
       //CIBACKUP = "Backup";
       System.out.println("tag: "+tag);
       System.out.println("CIBACKUP: " +CIBACKUP);
	
		
	 //Get the Week of the Year value.  Used in NewSnapshot Description
	 Calendar now = Calendar.getInstance();
     int WOY = now.get(Calendar.WEEK_OF_YEAR);
     System.out.println("Current week of year is : " + now.get(Calendar.WEEK_OF_YEAR));
      
    // String  Current_Snapshot_Description = "ci.rice.backup - week "+WOY; 
     String  Current_Snapshot_Description = tag + ".backup - week "+WOY; 
    // String  Current_Snapshot_Description = "test only-ci.rice.backup - week "+WOY; 

      //Tag Names or Keys
		
      //String  TagName  = CIBACKUP+".Backup";     
      //String  TagName  = CIBACKUP;   
      String  TagName  = "Backup";   
      String  W0Value  = CIBACKUP+".Backup.0";     
      String  W1Value  = CIBACKUP+".Backup.1";     
      String  W2Value  = CIBACKUP+".Backup.2";
      
      Tag W0Tag = CreateTag(TagName , W0Value);
      Tag W1Tag = CreateTag(TagName , W1Value);
      Tag W2Tag = CreateTag(TagName , W2Value);
     
      String CIName = "Name";
  //    String CIValue = "ci.rice"; replaced with arg
      String CIValue = tag;

      Tag CITag = CreateTag(CIName, CIValue);
      Filter CIFilter = getFilterFromTag(CITag);
	
	  System.out.println("filter1 "+CIFilter.toString());	
      String CI_volume = GetCIVolumeID(CIFilter); 
      System.out.println(CI_volume); 
       //Testing purpose, remove after tests.. its a 25G volume 
       // CI_volume =  "vol-eb506a81";  //Test purpose
       String NewSnapshotID = CreateBackUpSnapshot(CI_volume,  Current_Snapshot_Description);
       String NewSnapshotTagName = "Name";
 	   String NewSnapshotNameValue = Current_Snapshot_Description;
 	   System.out.println(NewSnapshotTagName +" "+ NewSnapshotNameValue +" "+ NewSnapshotID );
        //addTagtoSnapshot( NewSnapshotTagName, NewSnapshotNameValue,NewSnapshotID );
       addTagtoSnapshot( NewSnapshotID, CITag );


	   
	    //Remove Snapshot Backup.2 
        Filter W2Filter = getFilterFromTag(W2Tag);
	    List<Snapshot> Snapshot_W2 = getSnapshotWithFilter( W2Filter );
	    for (Snapshot snapshotw2 : Snapshot_W2) {
	      System.out.println("Snapshot ID "+  snapshotw2.getSnapshotId()+" Tags: "+snapshotw2.getTags());
	      System.out.println("DeleteCIBackup02: "+ snapshotw2.getSnapshotId() );
	      DeleteCIBackup02(snapshotw2.getSnapshotId() );

	    }
		
	   //Replace Snapshot Tag Backup.1 with Backup.2
	    Filter W1Filter = getFilterFromTag(W1Tag);
	    List<Snapshot> Snapshot_W1 = getSnapshotWithFilter( W1Filter);
	    for (Snapshot snapshotw1: Snapshot_W1) {
	    	List<Tag> Snapshot_W1_Tags= snapshotw1.getTags();
	    	for ( Tag w1tag: Snapshot_W1_Tags){
	    		if ( w1tag.equals(W1Tag)){
	    		    w1tag.setKey(TagName);
	    		    w1tag.setValue(W2Value);
	    		    ReplaceSnapshotTag(snapshotw1.getSnapshotId(), w1tag);
	    		}
	    	}

	    }
	    
	  //Replace Snapshot Tag Backup.0 with Backup.1
	    Filter W0Filter = getFilterFromTag(W0Tag);
	    List<Snapshot> Snapshot_W0 = getSnapshotWithFilter( W0Filter);
	    for (Snapshot snapshotw0 : Snapshot_W0) {
	    	List<Tag> Snapshot_W0_Tags= snapshotw0.getTags();
	    	for ( Tag w0tag: Snapshot_W0_Tags){
	    		if ( w0tag.equals(W0Tag)){
	    			 w0tag.setKey(TagName);
		    		 w0tag.setValue(W1Value);
					 ReplaceSnapshotTag(snapshotw0.getSnapshotId(), w0tag);
	    	     	}
	          	}
	        }
	   
	   // Add Tag Backup.0 to the New Snapshot
	    System.out.println("addTagtoSnapshot( "+NewSnapshotID+","+ W0Tag );
	    addTagtoSnapshot(NewSnapshotID,W0Tag );
	    System.out.println("Done. Monitor Snapshot Progress." );
	 

	} // each instance to back up 
  }  //execute

} //class
