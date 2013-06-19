package org.kuali.maven.ec2;

import java.util.Collections;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.EbsBlockDevice;
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
            String key = "Name";
            String prefix = "CI Slave";
            String device = "/dev/sda1";
            int min = 14;
            ec2Utils.cleanupSlaveImages(key, prefix, device, min);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
