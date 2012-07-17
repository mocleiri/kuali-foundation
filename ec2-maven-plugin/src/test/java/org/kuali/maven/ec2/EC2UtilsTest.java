package org.kuali.maven.ec2;

import java.util.Collections;

import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.ListIterator;

import org.apache.commons.lang.StringUtils;
import org.jasypt.util.text.BasicTextEncryptor;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.DescribeImageAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeImageAttributeResult;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Filter;

import com.amazonaws.services.ec2.model.ImageAttribute;
import com.amazonaws.services.ec2.model.RegisterImageRequest;
import com.amazonaws.services.ec2.model.RegisterImageResult;
import com.amazonaws.services.ec2.model.Tag;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Properties;

import org.kuali.maven.ec2.state.ImageStateRetriever;
import org.kuali.maven.ec2.state.InstanceStateRetriever;
import org.kuali.maven.ec2.state.SnapshotStateRetriever;
import org.kuali.maven.ec2.state.StateRetriever;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.ec2.model.CreateSnapshotRequest;
import com.amazonaws.services.ec2.model.CreateSnapshotResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;

import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsResult;
import com.amazonaws.services.ec2.model.Filter;

import com.amazonaws.services.ec2.model.Instance;

import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Snapshot;

import com.amazonaws.services.ec2.model.TerminateInstancesRequest;

public class EC2UtilsTest {

	public static final String ACCESS_KEY = "AKIAIZFPMJVCNOYYAZ2Q";
	public static final String SECRET_KEY_ENCRYPTED = "CUdUkYyNwfLUlNutPESFSI27k87kUiyMfmvdWyS4vsM2vH8UUms40LDmNi02qZiJmCP+cRCvsCQ=";
	private String retainImageID;
	private String retainImageIDLabel;

	protected EC2Utils getEC2Utils() {
		BasicTextEncryptor bte = new BasicTextEncryptor();
		bte.setPassword(System.getProperty("kuali.master.password"));
		String secretKey = bte.decrypt(SECRET_KEY_ENCRYPTED);
		return EC2Utils.getInstance(ACCESS_KEY, secretKey);
	}

	protected AmazonEC2Client getEC2Client() {
		BasicTextEncryptor bte = new BasicTextEncryptor();
		bte.setPassword(System.getProperty("kuali.master.password"));
		String secretKey = bte.decrypt(SECRET_KEY_ENCRYPTED);
		return EC2Utils.getEC2Client(ACCESS_KEY, secretKey);
	}

	public void testRegisterImage() {
		AmazonEC2Client client = getEC2Client();

		try {
			RegisterImageRequest request = new RegisterImageRequest();
			request.setName("ci-slave-2012-02-22-test");
			request.setDescription("ci.slave.test.image");
			request.setArchitecture("x86_64");
			request.setRootDeviceName("/dev/sda1");
			request.setKernelId("aki-825ea7eb");
			BlockDeviceMapping bdm = new BlockDeviceMapping();
			bdm.setDeviceName("/dev/sda1");
			EbsBlockDevice ebs = new EbsBlockDevice();
			ebs.setDeleteOnTermination(true);
			ebs.setSnapshotId("snap-a6dc42d8");
			ebs.setVolumeSize(128);
			bdm.setEbs(ebs);
			request.setBlockDeviceMappings(Collections.singletonList(bdm));

			RegisterImageResult result = client.registerImage(request);
			String id = result.getImageId();
			System.out.println(id);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	@Test
	public void testTagPaulaImage() {        

		EC2Utils utils = getEC2Utils();
		List<Image> images = utils.getEC2ImagesOwnedByMe();
		int size = images.size();
		SlaveTag[] slaveTagArray = new SlaveTag[size];
        
		System.out.println("size: " + size);
		int numberCandidates;

<<<<<<< .mine
		int i = 0;
		for (Image image : images) {
			List<Tag> imageTags = image.getTags();	
			String imageId = image.getImageId();
			for (Tag tag : imageTags) {
				String key = tag.getKey();
				String value = tag.getValue();
				if (key.equals("Name")) {
					if (value.contains("CI Slave")) {
=======
    // @Test
    public void testRegisterImage() {
        AmazonEC2Client client = getEC2Client();
        try {
            RegisterImageRequest request = new RegisterImageRequest();
            request.setName("ci-slave-2012-02-22-test");
            request.setDescription("ci.slave.test.image");
            request.setArchitecture("x86_64");
            request.setRootDeviceName("/dev/sda1");
            request.setKernelId("aki-825ea7eb");
            BlockDeviceMapping bdm = new BlockDeviceMapping();
            bdm.setDeviceName("/dev/sda1");
            EbsBlockDevice ebs = new EbsBlockDevice();
            ebs.setDeleteOnTermination(true);
            ebs.setSnapshotId("snap-a6dc42d8");
            ebs.setVolumeSize(128);
            bdm.setEbs(ebs);
            request.setBlockDeviceMappings(Collections.singletonList(bdm));
>>>>>>> .r10271

						SlaveTag slaveTag = new SlaveTag();
						slaveTag.setImageId(imageId);
						slaveTag.setTag(tag);
						String[] tokens = StringUtils.splitByWholeSeparator(
								value, " - ");
						slaveTag.setLabel(tokens[0]);
						slaveTag.setDate(tokens[1]);
						slaveTag.setSequence(Integer.parseInt(tokens[2]));
						slaveTagArray[i] = slaveTag;
						
					     List<BlockDeviceMapping> bdm = image
                                  .getBlockDeviceMappings();
                  // System.out.println("BDM: "+bdm);
						for (BlockDeviceMapping e : bdm) {
							String item = e.toString();

<<<<<<< .mine
							String[] ebs = StringUtils.splitByWholeSeparator(
									item, "Ebs: {SnapshotId: ");
							String[] snapshot = StringUtils
									.splitByWholeSeparator(ebs[1], ",");
							//System.out.println("Snapshot " + snapshot[0]);
							slaveTag.setSnapshot(snapshot[0]);
=======
    // @Test
    public void testDescribeImages() {
        AmazonEC2Client client = getEC2Client();
        DescribeImagesRequest request = new DescribeImagesRequest();
        request.setOwners(Collections.singletonList("self"));
        DescribeImagesResult result = client.describeImages(request);
        List<Image> images = result.getImages();
        Collections.sort(images, new ImageComparator());
        System.out.println(images.size());
        System.out.println("------------ --------------");
        System.out.println("Image Id     Image Location");
        System.out.println("------------ --------------");
        for (Image image : images) {
            System.out.print(image.getImageId() + " " + image.getImageLocation());
            System.out.print(" ");
            List<BlockDeviceMapping> bdms = image.getBlockDeviceMappings();
            for (BlockDeviceMapping bdm : bdms) {
                System.out.print(bdm.getDeviceName());
                EbsBlockDevice ebs = bdm.getEbs();
                System.out.print("=" + ebs.getSnapshotId() + ":" + ebs.getVolumeSize() + ":"
                        + ebs.getDeleteOnTermination());
                System.out.print(" ");
            }
            System.out.println();
>>>>>>> .r10271

						}
						slaveTagArray[i] = slaveTag;
						i++;
					}
				}
			}
		}
		numberCandidates = (i - 1);

		System.out.println("numberCandidates: " + numberCandidates);

		SlaveTag[] sta = Arrays.copyOf(slaveTagArray, numberCandidates);
		Arrays.sort(sta);
		String retainImageID = sta[sta.length - 1].imageId;
		System.out.println("Retain Image: " + retainImageID + " Label: "
				+ sta[sta.length - 1].tag);

		for (SlaveTag slavetag : sta) {
			if (!(slavetag.getImageId().matches(retainImageID))) {
			    String imageID = slavetag.getImageId();
				String snapshotID = slavetag.getSnapshot();
				System.out.println("Deregister ImageID: "
				        + imageID + " : "
						+ slavetag.getSequence() + "  : Delete snapshot: "
						+ snapshotID);
				System.out.println("utils.DeRegisterImagebyImageId("+imageID+     " )");
				System.out.println("utils.DeleteEC2SnapshotbySnapshotID("+snapshotID+")");
			}
		}

	}
}
