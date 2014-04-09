/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.CommitBuilder;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectInserter;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.kuali.maven.plugins.fusion.util.GitFusionUtils;
import org.kuali.student.git.model.GitRepositoryUtils;
import org.kuali.student.git.model.SvnExternalsUtils;
import org.kuali.student.git.model.ref.utils.GitRefUtils;
import org.kuali.student.svn.model.ExternalModuleInfo;

/**
 * @author ocleirig
 * 
 */
@Mojo(name = FusionMavenPluginConstants.SPLIT_MOJO)
@Execute(goal = FusionMavenPluginConstants.SPLIT_MOJO)
public class SplitMojo extends AbstractFusionMojo {

	/*
	 * true or false and is used to determine if we should commit the current branch before performing the split operation.
	 */
	@Parameter(property = FusionMavenPluginConstants.CHECKOUT_FUSED_BRANCH_PREFIX, defaultValue = FusionMavenPluginConstants.CHECKOUT_FUSED_BRANCH_PREFIX_DEFAULT)
	protected String commitBeforeSplit;

	
	/*
	 * true of false and is used to determine if we should amend the previous commit vs creating a new commit.  Only used when commitBeforeSplit is set.
	 */
	@Parameter(property = FusionMavenPluginConstants.CHECKOUT_FUSED_BRANCH_PREFIX, defaultValue = FusionMavenPluginConstants.CHECKOUT_FUSED_BRANCH_PREFIX_DEFAULT)
	protected String amend;
	
	
	/**
	 * 
	 */
	public SplitMojo() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.plugin.Mojo#execute()
	 */
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		try {
			/*
			 * Assume that we are running on the aggregate and that the
			 * configuration will tell us how to fuse with our modules.
			 */

			File baseDir = project.getBasedir();

			/*
			 * Assume that the pom is located in the working copy of the git
			 * repository on the branch we want to fuse into
			 */

			Repository repo = GitRepositoryUtils.buildFileRepository(baseDir,
					false, false);

			ObjectReader objectReader = repo.newObjectReader();

			ObjectInserter objectInserter = repo.newObjectInserter();

			RevWalk rw = new RevWalk(repo);

			String currentBranchName = repo.getBranch();

			Ref currentHead = repo.getRef(currentBranchName);
			
			RevCommit previousCommit = rw.parseCommit(currentHead.getObjectId());
			
			
			if (this.commitBeforeSplit != null && this.commitBeforeSplit.equals("true")) {
				
				CommitCommand commitCommand = new Git (repo).commit();
				
				String commitMessage = "Committing before splitting";
				
				if (this.amend != null && this.amend.equals("true")) {
					
					commitCommand.setAmend(true);
					
					commitMessage = previousCommit.getFullMessage() + "\n" +  "Ameded changes";
					
				}
				
				PersonIdent pi;
				commitCommand.setAuthor(pi = new PersonIdent("jcaddel", "jcaddel@kuali.org"));
				
				commitCommand.setCommitter(pi);
				
				commitCommand.setAll(true);
				
				commitCommand.setMessage(commitMessage);
				
				RevCommit commit = commitCommand.call();
				
				if (commit == null) {
					
					objectReader.release();
					objectInserter.release();
					rw.release();
					
					repo.close();
					throw new MojoFailureException(
							"Unable commit on current branch : " + currentBranchName);
				}
				
				currentHead = repo.getRef(currentBranchName);
			}

			RevCommit fusedCommit = rw.parseCommit(currentHead.getObjectId());

			List<ExternalModuleInfo> externals = new ArrayList<>();
			
			Map<String, Mapping>moduleNameToMapping = new HashMap<String, Mapping>();
			

			for (Mapping mapping : mappings) {

					Ref branchHead = repo.getRef(Constants.R_HEADS
							+ mapping.getBranchName());

					if (branchHead == null) {
						
						objectReader.release();
						objectInserter.release();
						rw.release();
						
						repo.close();
						
						throw new MojoFailureException(
								"Unable to find a branch head for : "
										+ mapping.getBranchName());
					}

					ObjectId headCommitId = branchHead.getObjectId();

					ExternalModuleInfo external = new ExternalModuleInfo(
							mapping.getModule(), mapping.getBranchName(), -1L,
							headCommitId);

					externals.add(external);
					
					moduleNameToMapping.put(mapping.getModule(), mapping);

				}
				
				
			Map<String, ObjectId> splitTreeData = SvnExternalsUtils.splitFusedTree(objectReader, objectInserter, rw, fusedCommit.getId(), externals);
			
			Set<String>branchesCreatedSet = new HashSet<>();
			
			for (Map.Entry<String, ObjectId> entry : splitTreeData.entrySet()) {
				
				String moduleName = entry.getKey();
				
				ObjectId splitTreeId = entry.getValue();
				
				Mapping mapping = moduleNameToMapping.get(moduleName);
				
				String splitBranchName = "split_" + mapping.getBranchName();
				
				branchesCreatedSet.add(splitBranchName);
				
				Set<ObjectId> parents = new HashSet<>();
				
				parents.add(fusedCommit.getId());
				
				ObjectId commitId = GitFusionUtils.commit(objectInserter, "jcaddel", "jcaddel@kuali.org", "Split out " + moduleName , splitTreeId, parents);
				
				Ref branchRef = GitRefUtils.createBranch(repo, Constants.R_HEADS + splitBranchName, commitId, true);
				
				if (branchRef == null)
					getLog().warn("Unable to set branch : " + splitBranchName + " to the split tree with id : " + commitId);
			}
			
			getLog().info("Splitting Branch: " + currentBranchName);
			
			getLog().info("Split Branches: " + StringUtils.join(branchesCreatedSet.iterator(), ", "));

			
			objectReader.release();
			objectInserter.release();
			rw.release();
			
			repo.close();

		} catch (Exception e) {

			if (e instanceof MojoExecutionException)
				throw (MojoExecutionException) e;
			else if (e instanceof MojoFailureException)
				throw (MojoFailureException) e;
			else
				throw new MojoExecutionException(
						"SplitMojo failed unexpectantly: ", e);
		}

	}

}
