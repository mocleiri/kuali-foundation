package org.kuali.common.devops.status;

import static org.kuali.common.devops.logic.Examiner.getPropertiesFromString;
import static org.kuali.common.devops.logic.Examiner.getSystemPropertiesFromHtml;

import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.kuali.common.devops.cache.PersistToFileSystemLoader;
import org.kuali.common.devops.cache.PersistToFileSystemLoaderFactory;
import org.kuali.common.devops.logic.Applications;
import org.kuali.common.devops.logic.Manifests;
import org.kuali.common.devops.logic.Projects;
import org.kuali.common.devops.model.DeployEnvironmentUrls;
import org.kuali.common.http.model.HttpContext;
import org.kuali.common.util.log.LoggerUtils;
import org.kuali.common.util.project.ProjectUtils;
import org.kuali.common.util.project.model.Project;
import org.slf4j.Logger;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

public class DeployEnvironmentUrlsTest {

	private static final Logger logger = LoggerUtils.make();

	@Test
	public void test() {
		LoadingCache<String, Optional<String>> httpContentCache = getCache();
		String fqdn = "env1.rice.kuali.org";
		DeployEnvironmentUrls.Builder builder = DeployEnvironmentUrls.builder(fqdn);
		fillIn(builder, httpContentCache);
		DeployEnvironmentUrls urls = builder.build();
	}

	protected static void fillIn(DeployEnvironmentUrls.Builder builder, LoadingCache<String, Optional<String>> httpContentCache) {
		Optional<String> manifestContent = httpContentCache.getUnchecked(builder.getApplicationManifest());
		if (!manifestContent.isPresent()) {
			return;
		}
		Map<String, String> manifest = Manifests.getManifestMapFromString(manifestContent.get());
		Optional<String> fragment = Projects.getProjectPropertiesUrlFragment(manifest);
		if (fragment.isPresent()) {
			String url = DeployEnvironmentUrls.Builder.DEFAULT_PREFIX + builder.getFqdn() + fragment.get();
			builder.projectProperties(Optional.of(url));
		}
		if (!builder.getProjectProperties().isPresent()) {
			return;
		}
		String url = builder.getProjectProperties().get();
		Optional<String> projectPropertiesContent = httpContentCache.getUnchecked(url);
		Optional<String> envJspContent = httpContentCache.getUnchecked(builder.getSystemPropertiesJsp());
		if (!projectPropertiesContent.isPresent() || !envJspContent.isPresent()) {
			return;
		}
		Properties projectProperties = getPropertiesFromString(projectPropertiesContent.get());
		Properties system = getSystemPropertiesFromHtml(envJspContent.get());
		Project project = ProjectUtils.getProject(projectProperties);
		Optional<String> configFragment = Applications.getConfigFragment(project, system);
		if (configFragment.isPresent()) {
			String configUrl = DeployEnvironmentUrls.Builder.DEFAULT_PREFIX + builder.getFqdn() + configFragment.get();
			builder.projectConfiguration(Optional.of(configUrl));
		}
	}

	protected static LoadingCache<String, Optional<String>> getCache() {
		HttpContext context = HttpContext.builder().quiet(true).asynchronousClose(true).maxBytes("25k").maxRetries(0).overallTimeout("5s").build();
		PersistToFileSystemLoader<String, String> loader = PersistToFileSystemLoaderFactory.createHttpUrlCacher(context);
		return CacheBuilder.newBuilder().build(loader);
	}

}
