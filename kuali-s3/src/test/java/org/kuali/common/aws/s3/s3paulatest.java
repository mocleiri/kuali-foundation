/**
 * Copyright 2004-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.aws.s3;


import java.util.Date;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;


import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration;
import com.amazonaws.services.s3.model.BucketLifecycleConfiguration.Rule;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;




public class s3paulatest {
	private static final String ACCESSKEY = "AKIAJFD5IM7IPVVUEBNA";
	private static final String SECRETYKEY = System.getProperty("secret.key");
    
	
	private static final Logger log = LoggerFactory
			.getLogger(s3paulatest.class);
	
	private Process process;
	


	protected AWSCredentials getCredentials() {
		log.debug("access key: " + ACCESSKEY);
		return new BasicAWSCredentials(ACCESSKEY, SECRETYKEY);
	}
		protected AmazonS3Client getClient() {
			AWSCredentials credentials = getCredentials();
			return new AmazonS3Client(credentials);
		}
	
		
	public Boolean FileExists( String FNAME){
		File f = new File(FNAME);
		
		
			 return(f.exists()); }
	
	
	public void writeLinesToFile(String filename,
            String linesToWrite, Boolean ForceNew){
		 PrintWriter pw = null;
		 boolean appendToFile;
		 appendToFile = false;
         String [] ltw = {linesToWrite};
         
        
         if ( FileExists( filename) ){
        	 appendToFile = true; }
         
         if ( ForceNew){
        	 appendToFile = false;
         }
         
         try {

           if (appendToFile) {

             //If the file already exists, start writing at the end of it.
             pw = new PrintWriter(new FileWriter(filename, true));
         	

           

           }
           else {

             pw = new PrintWriter(new FileWriter(filename));
             //this is equal to:
             //pw = new PrintWriter(new FileWriter(filename, false));

           }

           for (int i = 0; i < ltw.length; i++) {

             pw.println(ltw[i]);

           }
           pw.flush();

         }
         catch (IOException e) {
           e.printStackTrace();
         }
         finally {

           //Close the PrintWriter
           if (pw != null)
             pw.close();

         }
	
	}
	
	//@Test
	
	public void s3Metrics() {
		String bucketName = "maven.kuali.org";
	
		AmazonS3Client client = getClient();
		
		Date now=new Date();
		String Date = DateFormat.getInstance().format(now);
		long nowmm=now.getTime();
		long yearmm=60*60*365*24*1000L;
		// millaseconds in a day 86,400,000
		// millaseconds in a year 31536000000 need the "L"
		
		long cmpdate=nowmm - yearmm;
	
		
		String BucketChart01  = "/tmp/BucketChart1.cvs";   //metric for Build,Release,Snapshot
		String BucketChart01a = "/tmp/BucketChart1a.cvs";  //Metric for Maven
		String BucketChart02  = "/tmp/BucketChart2.cvs";   //Data for Snapshot
		String BucketChart02a = "/tmp/BucketChart2a.cvs";  //Data for External
		String BucketChart02b = "/tmp/BucketChart2b.cvs";  //Data for Private
		String BucketChart03  = "/tmp/BucketChart3.cvs";   //Data for Builds
		String BucketChart04  = "/tmp/BucketChart4.cvs";   //Data for Release
		String BucketChart05  = "/tmp/BucketChart5.cvs";   //Snapshot, pie, Keep Data
		String BucketChart06  = "/tmp/BucketChart6.cvs";  //Snapshot, pie, Expire Data

		String BucketChart05a = "/tmp/BucketChart5a.cvs";  //Release, pie, Keep Data
		String BucketChart06a = "/tmp/BucketChart6a.cvs";  //Release, pie, Expire Data

		
		String BucketListA = "/tmp/BucketListA.cvs";  //Table Listing of Release Areas Summary
		String BucketListB = "/tmp/BucketListB.cvs";  //Snapshot Table Listing
		String BucketListC = "/tmp/BucketListC.cvs";  //Release Table Listing
		String BucketListD = "/tmp/BucketListD.cvs";  //Captures The Date of the Report

		String BucketListExpired= "/tmp/ExpiredList.txt";
	   	writeLinesToFile(BucketListExpired,  "ExpiredFile", true); 

		String CaptureDate = "/tmp/CaptureDate.cvs"; // The date the report was ran
		writeLinesToFile( CaptureDate, "Captured "+Date, true);
		
		
		
		
		 BucketLifecycleConfiguration bLC = client.getBucketLifecycleConfiguration(bucketName);
		 List<Rule> s3SnapshotRule = bLC.getRules();
	     Rule s3_snapshot_rule = s3SnapshotRule.get(0);
	     String RuleName       = s3_snapshot_rule.getId();
	     String RulesStatus    = s3_snapshot_rule.getStatus();
		 int RulesExpiration   = s3_snapshot_rule.getExpirationInDays();
		 String DirName        = s3_snapshot_rule.getPrefix();
			
		 String PolicyLineTitle = "RuleName,DirName,RulesExpiration(days),RulesStatus";
		 writeLinesToFile( BucketListD, PolicyLineTitle, true);
		 System.out.println(PolicyLineTitle);
		 String PolicyLine = RuleName+","+DirName+","+RulesExpiration+","+RulesStatus;
		 writeLinesToFile( BucketListD, PolicyLine, false);
		 System.out.println(PolicyLine);

	     String PrintLine;
	     long Total = 0L;     
	     long KeepValue  = 0L;
		 long ExpireValue= 0L;
		 long lineValue  = 0L;
		 long expireValueGB = 0L;
		 long keepValueGB   = 0L;
		  	             
		 String relAreaName = "";
		 //String BuildLine;
		 String sbuildtotalGB = "";
		 String sreleasetotalGB = "";
		 String sexternaltotalGB = "";
		 String sprivatetotalGB = "";
		 String ssnapshottotalGB = "";
	 
		 //BucketListA - Total of the build Areas
		 PrintLine =  ( "RelArea,CandidateExpire GB,KeepSpace GB,Total GB");
		 writeLinesToFile(BucketListA,  PrintLine, true);
	            
	    String relAreaArray[] = { 
	      "builds/", 
	      "release/",
	      "external/",
	      "private/",
	      "snapshot/"
	    }; 
              
   for ( String relArea : relAreaArray){   
	 System.out.println(relArea);
     String delimiter = "/";
     int expireindex = 0;
     int keepindex   = 1;
     long[][] SubAreaValue = new long[1000][100]; 
     
     for ( int i = 0; i<1000; i++){ SubAreaValue[i][expireindex] = 0L; SubAreaValue[i][keepindex] = 0L; }  //default the SubAreas
     
     String[] SubAreaName = new String[1000];
     String dirfile;
     String previous = "";
     int key = -1;             
     int i = 0;
     
                 
     ObjectListing listing = client.listObjects(bucketName, relArea);
     List<S3ObjectSummary> list = listing.getObjectSummaries();	
       
     S3ObjectSummary file;
	 int SizeList = list.size();

     while ( listing.isTruncated() || (!list.isEmpty())) // loop until the relArea index is exhausted, next index is introduced
     { 
      	
  	   file = list.get(i);
  	   i = i + 1;  //reset after 1000 records or end of the list
  	   dirfile = file.getKey();
  	   String tmpdir[] = dirfile.split(delimiter, 0);
  	   int NoOfDirs = tmpdir.length;
  	   NoOfDirs--; //re adjusted for size


  	    if ( NoOfDirs > 2 ){   //don't do unless the the tree structure is at least 3 dirs deep, which is the sub-area value, if it exists.
		  String lastdir = tmpdir[3];
		  if ( ! (lastdir.equals(previous)) ){ key = key + 1; SubAreaName[key] = lastdir; previous = lastdir;} // gets the next major directory
		  	                
		  	 Date fileLastModified= file.getLastModified();
		     long filemm = fileLastModified.getTime();
		     long fileSize = file.getSize();
			 if ( filemm < cmpdate)
			 { 
			  	  SubAreaValue[key][expireindex] =  SubAreaValue[key][expireindex] + fileSize; // used in Charts to collect details under Snapshot, Release 
			  	                                                                               // for directories such as rice, student, ole
			 	 //BucketListListExpired        
			   	 PrintLine =  ( dirfile);
			   	 writeLinesToFile(BucketListExpired,  PrintLine, false); 
			  	  
			  			  
			  	      
			  	}  //if
			  	else { 
				  	    SubAreaValue[key][keepindex] =  SubAreaValue[key][keepindex] + fileSize; }    	
		   }//if directories are deeper than 2

		 if ( (i == SizeList)) {  // get the next bundle of listings
		  	                	
		  listing = client.listNextBatchOfObjects(listing);
		  list.clear();
		  list.addAll(listing.getObjectSummaries());
		  SizeList = list.size();
		  //System.out.println("Size of List: "+SizeList);
		  i = 0;  //reset record counter
	     }
   } // until the area is exhausted
  	 
  	
  	               
	  //Zero out for each RelArea	             
	  int size = key;
	  String TitleK = "";
	  String ValueK = "";
	  String commaK = "";
	  String TitleE = "";
	  String ValueE = "";
	  String commaE = "";
	  long  ReleaseAreaTotal     = 0L;  
	  long  ReleaseAreaTotalE    = 0L;          
	  long  ReleaseAreaTotalK    = 0L; 
	  long  ReleaseAreaTotalGB   = 0L;  
	  long  ReleaseAreaTotalEGB  = 0L;          
	  long  ReleaseAreaTotalKGB  = 0L;        

  
  
	  
  //for each sub group Total the Keep and Expired Values
  for (int keyx = 0; keyx <= size; keyx++)
  { 	 
	  	             
  	KeepValue   = SubAreaValue[keyx][1];
  	ExpireValue = SubAreaValue[keyx][0];
  	lineValue   = KeepValue+ExpireValue;
	ReleaseAreaTotal = ReleaseAreaTotal + lineValue;
	ReleaseAreaTotalE = ReleaseAreaTotalE + ExpireValue;
	ReleaseAreaTotalK = ReleaseAreaTotalK + KeepValue;
  	relAreaName = SubAreaName[keyx];
  	             

  	// Put in GBs
  	expireValueGB    = ExpireValue/1000/1000L;
  	keepValueGB      = KeepValue/1000/1000L;
  	//lineValueGB      = lineValue/1000/1000L;
  	ReleaseAreaTotalGB = ReleaseAreaTotal/1000/1000L;
    ReleaseAreaTotalEGB = ReleaseAreaTotalE/1000/1000L;
  	ReleaseAreaTotalKGB  = ReleaseAreaTotalK/1000/1000L;
  	             
   
   } //for loop to sum and print info
  
  Total = Total + lineValue;
  
  //Null all these 
  TitleK = "";
  ValueK = "";
  commaK = "";
  TitleE = "";
  ValueE = "";
  commaE = "";  
  
  	            
   // Fall through here after the summary, and lets do totals and printing for each major area            
   if (relArea == "builds/")
	{
  	 //BucketChart03
  	 PrintLine =  ( " A, Expire-GB, Keep-GB");
  	 writeLinesToFile(BucketChart03,PrintLine,true);
  	 PrintLine =  ( "builds"+","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB));
  	 writeLinesToFile(BucketChart03,PrintLine,false);
  	                
  	 //BucketListA
  	 PrintLine  = ("builds" +","+ Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB)+","+Long.toString(ReleaseAreaTotalGB));
     writeLinesToFile(BucketListA,  PrintLine, false);
  	          
  	//For Totals
  	sbuildtotalGB   = Long.toString(ReleaseAreaTotalGB);
    System.out.println(relArea+" "+sbuildtotalGB);
	             
	 }
              
  	            
  	if (relArea == "release/")
	{
  		  	  
  	//BucketChart04
	 PrintLine =  ( " A, Expire-GB, Keep-GB");
	 writeLinesToFile(BucketChart04,PrintLine,true);
	 PrintLine =  ( "release"+","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB));
	 writeLinesToFile(BucketChart04,PrintLine,false);
	                
	          
	 //BucketListA
  	 PrintLine  = ("release" +","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB)+","+Long.toString(ReleaseAreaTotalGB));		  	        
  	 writeLinesToFile(BucketListA,  PrintLine, false);
	          
	//For Totals
	sreleasetotalGB = Long.toString(ReleaseAreaTotalGB);
    System.out.println(relArea+" "+sreleasetotalGB);

	
	 //BucketListC        
  	 PrintLine =  ( "Date,RelArea,CandidateExpire,KeepSpace,Total");
  	 writeLinesToFile(BucketListC,  PrintLine, true); 
  	
	 
	
	 for (int keyx = 0; keyx <= size; keyx++)
	  { 
	  	
		 KeepValue   = SubAreaValue[keyx][1];
		 ExpireValue = SubAreaValue[keyx][0];
		 lineValue   = KeepValue+ExpireValue;
	
		 relAreaName = SubAreaName[keyx];
		  	             

		// Put in GBs
		expireValueGB    = ExpireValue/1000/1000L;
		keepValueGB      = KeepValue/1000/1000L;
		//lineValueGB      = lineValue/1000/1000L;
	  	             
	   //Do this for each area
	   //This is for pie charts, don't want a lot of zero entries        
	   if ( KeepValue != 0L) {
	      TitleK =  TitleK + commaK + relAreaName;
	      ValueK =  ValueK + commaK + Long.toString(keepValueGB) ;
	      commaK =",";
	     }
	   if ( ExpireValue != 0L)
	    {
	       TitleE =  TitleE + commaE + relAreaName;
	       ValueE =  ValueE + commaE + Long.toString(expireValueGB) ;
	      commaE =",";
	     }
	   
	     PrintLine  = (Date +","+relAreaName+","+ Long.toString(expireValueGB) +","+Long.toString(keepValueGB)+","+Long.toString(keepValueGB+expireValueGB));		  	    
		 writeLinesToFile(BucketListC,     PrintLine, false);
	   } //for loop to sum and print info
	 
	 
	 
	 writeLinesToFile(BucketChart05a,  TitleK, true); 
     writeLinesToFile(BucketChart06a,  TitleE, true); 
     writeLinesToFile(BucketChart05a,  ValueK, false); 
     writeLinesToFile(BucketChart06a,  ValueE, false); 
	 }
                 
	if (relArea == "snapshot/")
        {
	//BucketChart02
	PrintLine =  ( " A, Expire-GB, Keep-GB");
	writeLinesToFile(BucketChart02,PrintLine,true);
	PrintLine =  ( "snapshot"+","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB));
	writeLinesToFile(BucketChart02,PrintLine,false);
		                
		          
	//BucketListA - details
	PrintLine  = ("snapshots" +","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB)+","+Long.toString(ReleaseAreaTotalGB));
    writeLinesToFile(BucketListA,  PrintLine, false);
		          
	//For Totals
	ssnapshottotalGB = Long.toString(ReleaseAreaTotalGB);
    System.out.println(relArea+" "+ssnapshottotalGB);

			  	        
	//BucketListB          
	PrintLine =  ( "Date,RelArea,CandidateExpire,KeepSpace,Total");
	writeLinesToFile(BucketListB,  PrintLine, true); 
 	
 	
 	 for (int keyx = 0; keyx <= size; keyx++)
	  { 
	  	      
 		 KeepValue   = SubAreaValue[keyx][1];
		 ExpireValue = SubAreaValue[keyx][0];
		 lineValue   = KeepValue+ExpireValue;
			
		 relAreaName = SubAreaName[keyx];
		  	             

		 // Put in GBs
		 expireValueGB    = ExpireValue/1000/1000L;
		 keepValueGB      = KeepValue/1000/1000L;
		 //lineValueGB      = lineValue/1000/1000L;
		  
		  	
	   //Do this for each area
	   //This is for pie charts, don't want a lot of zero entries        
	   if ( KeepValue != 0L) {
	      TitleK =  TitleK + commaK + relAreaName;
	      ValueK =  ValueK + commaK + Long.toString(keepValueGB) ;
	      commaK =",";
	     }
	   if ( ExpireValue != 0L)
	    {
	       TitleE =  TitleE + commaE + relAreaName;
	       ValueE =  ValueE + commaE + Long.toString(expireValueGB) ;
	      commaE =",";
	     }
	   
	   PrintLine  = (Date +","+relAreaName+","+ Long.toString(expireValueGB) +","+Long.toString(keepValueGB)+","+Long.toString(keepValueGB+expireValueGB));		
	 	writeLinesToFile(BucketListB,  PrintLine, false);
	   } //for loop to sum and print info
	  

	//Print Pie Charts for Keep and Expire
	writeLinesToFile(BucketChart05,  TitleK, true); 
	writeLinesToFile(BucketChart05,  ValueK, false); 
	writeLinesToFile(BucketChart06,  TitleE, true); 
	writeLinesToFile(BucketChart06,  ValueE, false);
	
	 }
	                        	        
   
     if (relArea == "private/")
      {
      //BucketChart02b
      PrintLine =  ( " A, Expire-GB, Keep-GB");
      writeLinesToFile(BucketChart02b,PrintLine,true);
      PrintLine =  ( "private"+","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB));
      writeLinesToFile(BucketChart02b,PrintLine,false);
                      
      //BucketListA
      PrintLine  = ("private" +","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB)+","+Long.toString(ReleaseAreaTotalGB));
 	  writeLinesToFile(BucketListA,  PrintLine, false);
     
 	  //For Totals
 	  sprivatetotalGB = Long.toString(ReleaseAreaTotalGB);
	  System.out.println(relArea+" "+sprivatetotalGB);
      }
     
     if (relArea == "external/")
     {
      //BucketChart02a
      PrintLine =  ( " A, Expire-GB, Keep-GB");
      writeLinesToFile(BucketChart02a,PrintLine,true);
      PrintLine =  ( "external"+","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB));
      writeLinesToFile(BucketChart02a,PrintLine,false);
             
      //BucketListA
      PrintLine  = ("external" +","+Long.toString(ReleaseAreaTotalEGB) +","+Long.toString(ReleaseAreaTotalKGB)+","+Long.toString(ReleaseAreaTotalGB));
	  writeLinesToFile(BucketListA,  PrintLine, false);
       
	  //For Totals
	  sexternaltotalGB = Long.toString(ReleaseAreaTotalGB);
	  System.out.println(relArea+" "+sexternaltotalGB);

	    
    } //if external

   } //for each major area
	  	       
   //Chart01
	PrintLine =  ( " RelArea, Builds, Snapshot, Release");
	writeLinesToFile(BucketChart01,  PrintLine, true);
	PrintLine  = ("A, "+sbuildtotalGB +","+ssnapshottotalGB +","+sreleasetotalGB);
	writeLinesToFile(BucketChart01,  PrintLine, false);
          
	  	 
  //Chart01a
	PrintLine =  ( " RelArea, Builds, Snapshot, Release, External, Private");
	writeLinesToFile(BucketChart01a,  PrintLine, true);
	PrintLine  = ("A, "+sbuildtotalGB +","+ssnapshottotalGB +","+sreleasetotalGB+","+sexternaltotalGB +","+sprivatetotalGB);
	writeLinesToFile(BucketChart01a,  PrintLine, false);
	System.out.println("BucketChart01a: "+ PrintLine);
	  	
	 try{
 		String cmdlinux = "scp /tmp/"+"*"+".cvs"+ " root"+ "@" +"50.19.200.109" +":"+  "/usr/local/tomcat";

 		Runtime runtime = Runtime.getRuntime();
 	    process = runtime.exec(cmdlinux);
  }
  catch(IOException ex){
      System.out.println (ex.toString());
		System.out.println("Could not find file " );
  }
	}; //testDateListing
	
	 

     
	
	
}; //subroutine
