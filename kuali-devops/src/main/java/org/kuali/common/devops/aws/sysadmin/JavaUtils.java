package org.kuali.common.devops.aws.sysadmin;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.Heap;
import org.kuali.common.devops.aws.sysadmin.model.Java;
import org.kuali.common.util.FormatUtils;

import com.google.common.collect.ImmutableList;

public class JavaUtils {

	public static List<String> getJavaOpts(Java java, String logDir, Heap heap) {
		List<String> opts = new ArrayList<String>();
		opts.addAll(getJavaOpts(java));
		opts.addAll(getJavaOpts(logDir, heap));
		return ImmutableList.copyOf(opts);
	}

	public static List<String> getJavaOpts(Java java) {
		List<String> opts = new ArrayList<String>();
		opts.addAll(java.getOptions());
		if (java.isUseNonBlockingEntropyGatheringDevice()) {
			opts.add("-Djava.security.egd=file:/dev/./urandom");
		}
		return ImmutableList.copyOf(opts);
	}

	public static List<String> getJavaOpts(String logDir, Heap heap) {
		List<String> opts = new ArrayList<String>();
		opts.add("-Xms" + FormatUtils.getIntegerSize(heap.getMinSizeInBytes()));
		opts.add("-Xmx" + FormatUtils.getIntegerSize(heap.getMaxSizeInBytes()));
		opts.add("-XX:MaxPermSize=" + FormatUtils.getIntegerSize(heap.getMaxPermSizeInBytes()));
		if (heap.isEnableLogging()) {
			opts.add("-verbose:gc");
			opts.add("-XX:+PrintGCDetails");
			opts.add("-XX:+PrintGCDateStamps");
			opts.add("-XX:+PrintHeapAtGC");
			opts.add("-XX:+PrintTenuringDistribution");
			opts.add("-Xloggc:$" + logDir + "/heap.log");
		}
		if (heap.isDumpOnOutOfMemoryError()) {
			opts.add("-XX:HeapDumpPath=$" + logDir);
			opts.add("-XX:+HeapDumpOnOutOfMemoryError");
			opts.add("-agentlib:hprof=file=$" + logDir + "/snapshot.hprof,format=b");
		}
		return ImmutableList.copyOf(opts);
	}

}
