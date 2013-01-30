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

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.common.aws.s3.pojo.AccountSummary;
import org.kuali.common.aws.s3.pojo.AccountSummaryContext;
import org.kuali.common.aws.s3.pojo.BucketComparator;
import org.kuali.common.aws.s3.pojo.BucketDisplay;
import org.kuali.common.aws.s3.pojo.BucketPrefixSummary;
import org.kuali.common.aws.s3.pojo.BucketSummary;
import org.kuali.common.aws.s3.pojo.S3PrefixContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;

/**
 * Utility methods related to Amazon S3
 */


public class S3MetricsUtils {
        private static final String ACCESSKEY = "AKIAJFD5IM7IPVVUEBNA";
        private static final String SECRETYKEY = System.getProperty("secret.key");

	private static final Logger log = LoggerFactory.getLogger(S3Utils.class);
        AmazonS3Client client = getClient();
	private static S3MetricsUtils instance;

	public static synchronized S3MetricsUtils getInstance() {
		if (instance == null) {
			instance = new S3MetricsUtils();
		}
		return instance;
	}

	protected S3MetricsUtils() {
		super();
	}

/*	public AWSCredentials getCredentials(String accessKey, String secretKey) {
		return new BasicAWSCredentials(accessKey, secretKey);
	}

	public AmazonS3Client getClient(String accessKey, String secretKey) {
		AWSCredentials credentials = getCredentials(accessKey, secretKey);
		return new AmazonS3Client(credentials);
	}
*/

        private static final Logger log = LoggerFactory
                        .getLogger(S3MetricsUtils.class);

        private Process process;

        public Process getProcess() {
                return process;
        }
        public void setProcess(Process process) {
                this.process = process;}

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
                 appendToFile = false; }

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

       public String  GetBucketPolicyRulesTitle()
       {     String PolicyLineTitle = "RuleName,DirName,RulesExpiration(days),RulesStatus"; 
              return(PolicyLineTitle)};

       public String  GetBucketListATitle()
       {     String LineTitle = "RelArea,CandidateExpire GB,KeepSpace GB,Total GB"; 
              return(LineTitle)};
       public String  GetBucketListBTitle()
       {     String LineTitle = "RelArea,CandidateExpire GB,KeepSpace GB,Total GB"; 
              return(LineTitle)};
       public String  GetBucketListCTitle()
       {     String LineTitle = "RelArea,CandidateExpire GB,KeepSpace GB,Total GB"; 
              return(LineTitle)};
       public String  GetBucketListDTitle()
       {     String LineTitle = "RelArea,CandidateExpire GB,KeepSpace GB,Total GB"; 
              return(LineTitle)};
       public String  GetBucketListETitle()
       {     String LineTitle = "Area,CandidateExpire GB,KeepSpace GB"; 
              return(LineTitle)};
       public String  GetBucketChart01Title()
       {     String LineTitle = "RelArea,CandidateExpire GB,KeepSpace GB,Total GB"; 
              return(LineTitle)};
       public String  GetBucketChart01aTitle()
       {     String LineTitle = "RelArea,CandidateExpire GB,KeepSpace GB,Total GB"; 
              return(LineTitle)};
       public String  GetBucketChart02Title()
       {     String LineTitle = "RelArea,CandidateExpire GB,KeepSpace GB,Total GB"; 
              return(LineTitle)};


       public String GetBucketPolicyRulesLines(String bucketName);
       {   BucketLifecycleConfiguration bLC = client.getBucketLifecycleConfiguration(bucketName);
                 List<Rule> s3SnapshotRule = bLC.getRules();
             Rule s3_snapshot_rule = s3SnapshotRule.get(0);
             String RuleName       = s3_snapshot_rule.getId();
             String RulesStatus    = s3_snapshot_rule.getStatus();
                 int RulesExpiration   = s3_snapshot_rule.getExpirationInDays();
                 String DirName        = s3_snapshot_rule.getPrefix();
                 String PolicyLine = RuleName+","+DirName+","+RulesExpiration+","+RulesStatus;
            return(PolicyLine);
         }
    
    public SubAreaType  GatherSubAreaInfo (String relArea, String bucketName, )
  {
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
         } //until the area is exhausted
     return  new SubAreaType(SubAreaValue,key); 
    }

}

