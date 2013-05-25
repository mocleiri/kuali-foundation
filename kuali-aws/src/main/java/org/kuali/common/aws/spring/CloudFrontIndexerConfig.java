package org.kuali.common.aws.spring;

import org.kuali.common.aws.cloudfront.HtmlGeneratorContext;
import org.kuali.common.aws.cloudfront.ListingConverterContext;
import org.kuali.common.aws.s3.BucketContext;
import org.kuali.common.aws.s3.BucketServiceExecutable;
import org.kuali.common.aws.s3.DefaultBucketService;
import org.kuali.common.aws.s3.ListingRequest;
import org.kuali.common.aws.s3.ObjectListingsContext;
import org.kuali.common.util.PercentCompleteInformer;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
public class CloudFrontIndexerConfig {

	@Autowired
	Environment env;

	@Bean
	public AmazonS3Client awsS3Client() {

		String accessKey = SpringUtils.getProperty(env, "s3.accessKey");
		String secretKey = SpringUtils.getProperty(env, "s3.secretKey");

		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

		return new AmazonS3Client(credentials);
	}

	@Bean(initMethod = "execute")
	public BucketServiceExecutable bucketServiceExecutable() {

		ListingRequest request = new ListingRequest();
		request.setPrefix(SpringUtils.getProperty(env, "s3.prefix"));
		request.setRecursive(SpringUtils.getBoolean(env, "s3.recursive"));
		request.setIncludes(SpringUtils.getIncludes(env, "s3.includes"));
		request.setExcludes(SpringUtils.getExcludes(env, "s3.excludes"));
		request.setInformer(new PercentCompleteInformer(SpringUtils.getLong(env, "s3.prefixEstimate")));

		ObjectListingsContext context = new ObjectListingsContext();
		context.setBucketContext(awsBucketContext());
		context.setClient(awsS3Client());
		context.setRequest(request);

		BucketServiceExecutable bse = new BucketServiceExecutable();
		bse.setContext(context);
		bse.setService(SpringUtils.getInstance(env, "s3.bucketService", DefaultBucketService.class));
		return bse;
	}

	@Bean
	public BucketContext awsBucketContext() {

		String delimiter = SpringUtils.getProperty(env, "s3.delimiter");
		int maxKeys = SpringUtils.getInteger(env, "s3.maxKeys");
		String bucket = SpringUtils.getProperty(env, "s3.bucket");

		BucketContext context = new BucketContext();
		context.setDelimiter(delimiter);
		context.setMaxKeys(maxKeys);
		context.setName(bucket);
		return context;
	}

	@Bean
	public ListingConverterContext awsListingConverterContext() {

		String fileImage = SpringUtils.getProperty(env, "cloudfront.fileImage");
		String dirImage = SpringUtils.getProperty(env, "cloudfront.dirImage");
		String browseKey = SpringUtils.getProperty(env, "cloudfront.browseKey");
		String dateDisplayFormat = SpringUtils.getProperty(env, "cloudfront.dateDisplayFormat");
		String dateDisplayTimeZone = SpringUtils.getProperty(env, "cloudfront.dateDisplayTimeZone");

		ListingConverterContext context = new ListingConverterContext();
		context.setBrowseKey(browseKey);
		context.setDateDisplayFormat(dateDisplayFormat);
		context.setDateDisplayTimeZone(dateDisplayTimeZone);
		context.setDirImage(dirImage);
		context.setFileImage(fileImage);
		return context;
	}

	@Bean
	public HtmlGeneratorContext awsHtmlGeneratorContext() {

		String css = SpringUtils.getProperty(env, "cloudfront.css");
		String dateDisplayFormat = SpringUtils.getProperty(env, "cloudfront.dateDisplayFormat");
		String dateDisplayTimeZone = SpringUtils.getProperty(env, "cloudfront.dateDisplayTimeZone");
		String encoding = SpringUtils.getProperty(env, "cloudfront.encoding");
		String googleAnalyticsAccount = SpringUtils.getProperty(env, "cloudfront.googleAnalyticsAccount");
		String googleAnalyticsDomainName = SpringUtils.getProperty(env, "cloudfront.googleAnalyticsDomainName");

		HtmlGeneratorContext context = new HtmlGeneratorContext();
		context.setCss(css);
		context.setDateDisplayFormat(dateDisplayFormat);
		context.setDateDisplayTimeZone(dateDisplayTimeZone);
		context.setEncoding(encoding);
		context.setGoogleAnalyticsAccount(googleAnalyticsAccount);
		context.setGoogleAnalyticsDomainName(googleAnalyticsDomainName);
		return context;
	}

}
