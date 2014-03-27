/**
 * Copyright 2004-2014 The Kuali Foundation
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

public class S3Types {
	  String build;
	  String key;
	  String [] group;
	  String [][] SubAreaValue;
	  long Total;
	  long KeepValue;
	  long ExpireValue;
	  long lineValue;
	  long expireValueGB;
	  long keepValueGB;
	  long KeepTotalGB;
	  long ExpireTotalGB ;
	  long ReleaseAreaTotal = 0;
	  long ReleaseAreaTotalE = 0;
	  long ReleaseAreaTotalK =  0;
	  long ReleaseAreaTotalEGB;
	  long ReleaseAreaTotalKGB;
	  long ReleaseAreaTotalGB;
	  
	  public S3Types(String build,long[][][] SubAreaValue, int key,  int bindex)
	  {
	    setBuild(build);
	    CalculateMajorSubAreas(key,SubAreaValue, bindex);
	   }
	  
	  public void CalculateMajorSubAreas( int key, long[][][] SubAreaValue,  int bindex)
	  {
	   int size = key;
	   long  ReleaseAreaTotal     = 0L;
	   long  ReleaseAreaTotalE    = 0L;
	   long  ReleaseAreaTotalK    = 0L;
	            
	   for (int keyx = 0; keyx <= size; keyx++)
	   {
	     KeepValue   = SubAreaValue[bindex][keyx][1];
	     ExpireValue = SubAreaValue[bindex][keyx][0];
	     lineValue   = KeepValue+ExpireValue;
	     ReleaseAreaTotal = ReleaseAreaTotal + lineValue;
	     ReleaseAreaTotalE = ReleaseAreaTotalE + ExpireValue;
	     ReleaseAreaTotalK = ReleaseAreaTotalK + KeepValue;
	                 

	     // Put in GBs
	     expireValueGB    = ExpireValue/1000/1000L;
	     keepValueGB      = KeepValue/1000/1000L;
	     //lineValueGB      = lineValue/1000/1000L;
	     ReleaseAreaTotalGB = ReleaseAreaTotal/1000/1000L;
	     ReleaseAreaTotalEGB = ReleaseAreaTotalE/1000/1000L;
	     ReleaseAreaTotalKGB  = ReleaseAreaTotalK/1000/1000L;
        } //for loop to sum and print info
	            
	   setReleaseAreaTotalEGB(ReleaseAreaTotalEGB);
	   setReleaseAreaTotalKGB(ReleaseAreaTotalKGB);
	   setReleaseAreaTotalGB(ReleaseAreaTotalGB);

	  }

	 public void setReleaseAreaTotalGB(long ReleaseAreaTotalGB) {
	     this.ReleaseAreaTotalGB = ReleaseAreaTotalGB;
	              }

	 public long getReleaseAreaTotalGB() {
	     return ReleaseAreaTotalGB;
	              }
	 public void setReleaseAreaTotalKGB(long ReleaseAreaTotalKGB) {
	      this.ReleaseAreaTotalKGB = ReleaseAreaTotalKGB;
	              }

	 public long getReleaseAreaTotalKGB() {
	      return ReleaseAreaTotalKGB;
	              }
	 public void setReleaseAreaTotalEGB(long ReleaseAreaTotalEGB) {
	      this.ReleaseAreaTotalEGB = ReleaseAreaTotalEGB;
	              }

	public long getReleaseAreaTotalEGB() {
	      return ReleaseAreaTotalEGB;
	              }
	public long getTotal() {
	     return Total;
	      }

	public void setTotal(long Total) {
	     this.Total = Total;
	      }
	public void setBuild(String build) {
	          this.build = build;
	      }
	public void setGroups(String [] group) {
	          this.group = group;
	      }
	public long getKeepValue() {
	          return KeepValue;
	      }

	public void setKeepValue(long KeepValue) {
	          this.KeepValue = KeepValue;
	      }
	public long getExpireValue() {
	          return ExpireValue;
	      }

	public void setExpireValue(long ExpireValue) {
	          this.ExpireValue = ExpireValue;
	      }
	public long getlineValue() {
	          return lineValue;
	      }

	public void setlineValue(long lineValue) {
	          this.lineValue = lineValue;
	      }

	public long getexpireValueGB() {
	          return expireValueGB;
	      }

	public void setexpireValueGB(long expireValueGB) {
	          this.expireValueGB = expireValueGB;
	      }

	public long getkeepValueGB() {
	          return keepValueGB;
	      }

	public void setkeepValueGB(long keepValueGB) {
	          this.keepValueGB = keepValueGB;
	      }
	public long getKeepTotalGB() {
	          return KeepTotalGB;
	      }
	public void setKeepTotalGB(long KeepTotalGB) {
	          this.KeepTotalGB = KeepTotalGB;
	      }

	public long getExpireTotalGB() {
	          return ExpireTotalGB;
	      }

	public void setExpireTotalGB(long ExpireTotalGB) {
	          this.ExpireTotalGB = ExpireTotalGB;
	      }
	      
	      
}
