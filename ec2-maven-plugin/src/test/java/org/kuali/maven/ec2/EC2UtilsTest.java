package org.kuali.maven.ec2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.RegisterImageRequest;
import com.amazonaws.services.ec2.model.RegisterImageResult;
import com.amazonaws.services.ec2.model.Tag;

public class EC2UtilsTest {

    public static final String ACCESS_KEY = "AKIAIZFPMJVCNOYYAZ2Q";
    public static final String SECRET_KEY_ENCRYPTED = "CUdUkYyNwfLUlNutPESFSI27k87kUiyMfmvdWyS4vsM2vH8UUms40LDmNi02qZiJmCP+cRCvsCQ=";

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

    public void testRegisterImage2() {
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

    @Test
    public void testDescribeImages() {
        try {
            EC2Utils ec2Utils = getEC2Utils();
            AmazonEC2Client client = getEC2Client();
            DescribeImagesRequest request = new DescribeImagesRequest();
            request.setOwners(Collections.singletonList("self"));
            DescribeImagesResult result = client.describeImages(request);
            List<Image> images = result.getImages();
            Collections.sort(images, new ImageComparator());
            List<SlaveTag> slaveTags = new ArrayList<SlaveTag>();
            String key = "Name";
            String prefix = "CI Slave";
            for (Image image : images) {
                if (ec2Utils.contains(image.getTags(), key, prefix)) {
                    Tag tag = ec2Utils.getTag(image.getTags(), key, prefix);
                    SlaveTag slaveTag = ec2Utils.getSlaveTag(image, tag);
                    slaveTags.add(slaveTag);
                }
            }
            Collections.sort(slaveTags);
            for (SlaveTag slaveTag : slaveTags) {
                System.out.println(slaveTag.getImageId() + " " + slaveTag.getDate() + " " + slaveTag.getSequence());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
