package org.kuali.common.devops.aws.sysadmin;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.devops.aws.sysadmin.model.Heap;
import org.kuali.common.util.FormatUtils;

import com.google.common.collect.ImmutableList;

public class HeapUtils {

	// opts.add("-Djava.security.egd=file:/dev/./urandom");

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
