package org.kuali.maven.ec2;

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

    @Test
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

    @Test
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

        }
    }
}
