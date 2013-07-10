package org.kuali.common.impex.spring;

import java.util.List;

import org.kuali.common.util.ScmRequest;
import org.kuali.common.util.ScmUtils;
import org.kuali.common.util.execute.BuildScmExecutable;
import org.kuali.common.util.execute.PrepareScmDirExecutable;
import org.kuali.common.util.service.ScmService;
import org.kuali.common.util.spring.SpringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Configuration
@Import({ ProjectPrepareScmConfig.class })
public class ImpexScmConfig {

	private static final String SCM_URL_KEY = "impex.scm.update.url";
	private static final String UPDATE_KEY = "impex.scm.update.skip";
	private static final String MESSAGE_KEY = "impex.scm.update.commitMessage";
	private static final String COMMITS_KEY = "impex.scm.update.commits";
	private static final String ADDS_KEY = "impex.scm.update.adds";
	private static final String DELETES_KEY = "impex.scm.update.deletes";

	@Autowired
	Environment env;

	@Autowired
	ProjectPrepareScmConfig projectPrepareScmConfig;

	@Bean
	public BuildScmExecutable buildScmExecutable() {

		boolean skip = SpringUtils.getBoolean(env, UPDATE_KEY, true);
		String commitMessage = SpringUtils.getProperty(env, MESSAGE_KEY);
		List<PrepareScmDirExecutable> preparers = projectPrepareScmConfig.prepareScmDirExecutables();
		ScmRequest request = getScmRequest();
		String url = SpringUtils.getProperty(env, SCM_URL_KEY);
		ScmService service = ScmUtils.getScmService(url);

		BuildScmExecutable exec = new BuildScmExecutable();
		exec.setExecutables(preparers);
		exec.setSkip(skip);
		exec.setCommitMessage(commitMessage);
		exec.setRequest(request);
		exec.setService(service);
		return exec;
	}

	protected ScmRequest getScmRequest() {
		ScmRequest request = new ScmRequest();
		request.setAdds(SpringUtils.getFilesFromCSV(env, ADDS_KEY));
		request.setCommits(SpringUtils.getFilesFromCSV(env, COMMITS_KEY));
		request.setDeletes(SpringUtils.getFilesFromCSV(env, DELETES_KEY));
		return request;
	}
}