package org.kuali.maven.ec2;

import java.util.Collections;
import java.util.List;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Image;

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
    public void testDescribeImages() {
        AmazonEC2Client client = getEC2Client();
        DescribeImagesRequest request = new DescribeImagesRequest();
        request.setOwners(Collections.singletonList("self"));
        DescribeImagesResult result = client.describeImages(request);
        List<Image> images = result.getImages();
        System.out.println(images.size());
        System.out.println("------------ --------------");
        System.out.println("Image Id     Image Location");
        System.out.println("------------ --------------");
        for (Image image : images) {
            System.out.println(image.getImageId() + " " + image.getImageLocation());
        }
    }
}
