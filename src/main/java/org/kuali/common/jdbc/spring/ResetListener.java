package org.kuali.common.jdbc.spring;

import java.util.Arrays;

import org.kuali.common.jdbc.listener.DataSummaryListener;
import org.kuali.common.jdbc.listener.MetaDataListener;
import org.kuali.common.jdbc.listener.NotifyingListener;
import org.kuali.common.jdbc.listener.ProgressListener;
import org.kuali.common.jdbc.listener.SqlListener;
import org.kuali.common.jdbc.listener.SummaryListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResetListener {

	@Bean
	public SqlListener jdbcSummaryListener() {
		return new SummaryListener();
	}

	@Bean
	public SqlListener jdbcDataSummaryListener() {
		return new DataSummaryListener();
	}

	@Bean
	public SqlListener jdbcProgressListener() {
		return new ProgressListener();
	}

	@Bean
	public SqlListener jdbcMetaDataSummaryListener() {
		return new MetaDataListener();
	}

	@Bean
	public SqlListener jdbcSummaryAndProgressListener() {
		return new NotifyingListener(Arrays.asList(jdbcSummaryListener(), jdbcProgressListener()));
	}

	@Bean
	public SqlListener jdbcSummaryAndMetaDataListener() {
		return new NotifyingListener(Arrays.asList(jdbcSummaryListener(), jdbcMetaDataSummaryListener()));
	}
}
