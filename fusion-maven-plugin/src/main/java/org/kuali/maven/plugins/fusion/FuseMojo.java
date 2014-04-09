/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.eclipse.jgit.api.CheckoutCommand;
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
@Mojo(name = FusionMavenPluginConstants.FUSE_MOJO)
@Execute(goal = FusionMavenPluginConstants.FUSE_MOJO)
public class FuseMojo extends AbstractFusionMojo {

	/*
	 * true or false and is used to determine if we should check out the newly
	 * created fused branch.
	 */
	@Parameter(property = FusionMavenPluginConstants.CHECKOUT_FUSED_BRANCH_PREFIX, defaultValue = FusionMavenPluginConstants.CHECKOUT_FUSED_BRANCH_PREFIX_DEFAULT)
	protected String checkoutFusedBranch;

	/**
	 * 
	 */
	public FuseMojo() {
		// TODO Auto-generated constructor stub
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

			Ref currentHead = repo
					.getRef(Constants.R_HEADS + currentBranchName);

			RevCommit commit = rw.parseCommit(currentHead.getObjectId());

			List<ExternalModuleInfo> externals = new ArrayList<>();

			Set<ObjectId> mergeParents = new HashSet<>();

			Set<String> mergedModules = new HashSet<>();

			mergeParents.add(currentHead.getObjectId());

			if (this.fusionDataFile != null) {
				
				FileInputStream inputStream;
				/*
				 * Source the externals from the file not the pom.
				 */
				externals.addAll(SvnExternalsUtils.extractFusionMavenPluginData(inputStream = new FileInputStream (this.fusionDataFile)));
				
				inputStream.close();
				
				for (ExternalModuleInfo external : externals) {
					
					mergeParents.add(external.getBranchHeadId());
					
					mergedModules.add(external.getModuleName());
				}
				
			} else {

				for (Mapping mapping : mappings) {

					Ref branchHead = repo.getRef(Constants.R_HEADS
							+ mapping.getBranchName());

					if (branchHead == null) {
						
						objectReader.release();
						objectInserter.flush();
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

					mergeParents.add(headCommitId);

					mergedModules.add(mapping.getModule());

				}
			}

			AnyObjectId fusedTree = SvnExternalsUtils.createFusedTree(
					objectReader, objectInserter, rw, commit, externals);

			/*
			 * Who should we commit as? and where should that be specified?
			 * 
			 * I think we might be able to use something like how the build
			 * number is set.
			 */

			ObjectId commitId = GitFusionUtils.commit(objectInserter, "jcaddel", "jcaddel@kuali.org", "Fused " + currentBranchName, fusedTree.toObjectId(), mergeParents);

			String fusedBranchName = "fused_" + currentBranchName;

			Ref branch = GitRefUtils.createOrUpdateBranch(repo,
					Constants.R_HEADS + fusedBranchName, commitId);

			if (branch == null)
				throw new MojoFailureException(
						"Unable to create fused branch: " + fusedBranchName);

			getLog().info(
					"created fused branch : " + fusedBranchName
							+ " from modules : "
							+ StringUtils.join(mergedModules.iterator(), ", "));

			if (this.checkoutFusedBranch != null
					&& this.checkoutFusedBranch.equals("true")) {

				CheckoutCommand checkOutCommand = new Git(repo).checkout();

				checkOutCommand.setName(fusedBranchName);
				checkOutCommand.setCreateBranch(false);

				Ref ref = checkOutCommand.call();

				if (ref == null)
					throw new MojoFailureException(
							"Unable to checkout fused branch: "
									+ fusedBranchName);

				getLog().info("checkedout fused branch : " + fusedBranchName);
			}
			
			objectReader.release();
			objectInserter.flush();
			rw.release();

			repo.close();
			
		} catch (Exception e) {

			if (e instanceof MojoExecutionException)
				throw (MojoExecutionException) e;
			else if (e instanceof MojoFailureException)
				throw (MojoFailureException) e;
			else
				throw new MojoExecutionException(
						"FuseMojo failed unexpectantly: ", e);
		}

	}

}
