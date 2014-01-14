package org.kuali.maven.plugins.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.PropertyUtils;
import org.kuali.maven.plugins.spring.LoadMojo;
import org.kuali.maven.plugins.spring.MojoRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import edu.calpoly.records.spring.ThreadsafeTestConfig;

@Configuration
@Import(BaseMojoTestConfig.class)
public class LoadMojoTestConfig {

	@Autowired
	BaseMojoTestConfig baseConfig;

	@Bean
	public AbstractMojo mojo() {
		MavenProject project = baseConfig.mavenProject();

		List<Thread> threads = new ArrayList<Thread>();
		ThreadGroup group = new ThreadGroup("threads");
		for (int i = 0; i < 8; i++) {

			LoadMojo mojo = new LoadMojo();
			mojo.setProject(project);
			mojo.setProperties(PropertyUtils.EMPTY);
			mojo.setAnnotatedClass(ThreadsafeTestConfig.class.getName());

			Runnable target = new MojoRunner(mojo);
			Thread thread = new Thread(group, target, "mojo-runner-" + i);
			threads.add(thread);
		}
		start(threads);
		wait(threads);
		return null;
	}

	protected void start(List<Thread> threads) {
		for (Thread thread : threads) {
			thread.start();
		}
	}

	protected void wait(List<Thread> threads) {
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				throw new IllegalStateException(e);
			}
		}
	}
}
