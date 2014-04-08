/**
 * Copyright 2011-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * 
 */
package org.kuali.maven.plugins.fusion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CheckoutResult;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.lib.CommitBuilder;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectInserter;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefUpdate;
import org.eclipse.jgit.lib.RefUpdate.Result;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.TreeFormatter;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Assert;
import org.kuali.student.git.model.GitRepositoryUtils;
import org.kuali.student.svn.model.ExternalModuleInfo;

/**
 * 
 * Creates a structure of /target/repository-name/{repo, main-wc, named-wc} to 
 * allow more than one working copy to be created for a given namespace.
 * 
 * @author ocleirig
 *
 */
public class GitTestRepositoryUtils {

	private static final String MAIN_WC = "main-wc";

	private static final String REPO_NAME = "repo";
	
	private static final File BUILD_DIR = new File (System.getProperty("user.dir"), "target");
	
	
	/**
	 * 
	 */
	private GitTestRepositoryUtils() {
		
	}
	private static File getRepositoryFile (String repositoryName) {
		File repositoryFile = new File (new File( BUILD_DIR, repositoryName), REPO_NAME);
		
		repositoryFile.mkdirs();
		
		return repositoryFile;
	}
	/**
	 * Create a new repository of the given name
	 * 
	 * @param repositoryName
	 * @return
	 * @throws SVNException
	 */
	public static Repository createRepository (String repositoryName) throws IOException {

	      return GitRepositoryUtils.buildFileRepository(getRepositoryFile(repositoryName), true , false);
	}
	
	/**
	 * Delete the named repository from the temporary directory
	 * 
	 * @param repositoryName
	 * @throws IOException
	 */
	public static void deleteRepository(String repositoryName) throws IOException {
		FileUtils.deleteDirectory(getRepositoryFile(repositoryName));
	}
	
	public static void deleteRepositoryWorkingCopy(String repositoryName, String workingCopyName) throws IOException {
		FileUtils.deleteDirectory(getWorkingCopyFile(repositoryName, workingCopyName));
	}
	
	public static void deleteRepositoryWorkingCopy(String testRespositoryName) throws IOException {

		deleteRepositoryWorkingCopy(testRespositoryName, testRespositoryName + "-wc");
	}
	
	private static File getWorkingCopyFile(String repositoryName,
			String workingCopyName) {
		
		File workingCopyFile = new File (new File( BUILD_DIR, repositoryName), workingCopyName);
		
		return workingCopyFile;
	}
	
	public static CheckoutResult checkoutOrphan (Repository repo, String branchName, String startingPoint) throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {
		
		CheckoutCommand co = new Git(repo).checkout();
		
		co.setName(branchName);
		
		co.setCreateBranch(true);
		
		co.setOrphan(true);
		
		co.setStartPoint(startingPoint);
		
		co.call();
		
		CheckoutResult result = co.getResult();
		
		return result;
	}
	
	public static List<Ref> listBranches (Repository repo, ListMode listMode) throws GitAPIException {
		ListBranchCommand listBranchCommand = new Git(repo).branchList();
		
		listBranchCommand.setListMode(listMode);
		
		return listBranchCommand.call();
	}
	
	
	public static List<ExternalModuleInfo> createFusionBaseStructure (Repository repo) throws IOException, RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {
		
		List<ExternalModuleInfo>externals = new ArrayList<>();
		
		createBaseCommit(repo, "base commit");
		
		// checkout at the root to create the initial structure
		CheckoutResult result = checkoutOrphan(repo, "aggregate", "master");
		
		Assert.assertNotEquals (CheckoutResult.ERROR_RESULT, result);
		
		File workingCopy = repo.getWorkTree();
		
		
		// create the pom
		createParentPomFile("student", workingCopy, new String[] {"module1", "module2"}, "2.0.0-FR1-SNAPSHOT", "2.0.0-FR1-SNAPSHOT", "1.0.0-FR1-SNAPSHOT");
		
		stageFiles(repo, "pom.xml");
		
		commitBranch(repo, "created the student pom file", true);
		
		result = checkoutOrphan(repo, "module1", "master");
		
		Assert.assertNotEquals (CheckoutResult.ERROR_RESULT, result);
		
		// create the module 1 pom
		createModulePomFile (workingCopy, "module1", "2.0.0-FR1-SNAPSHOT", "2.0.0-FR1-SNAPSHOT");
		
		stageFiles(repo, "pom.xml");
		
		RevCommit module1Commit = commitBranch(repo, "created the module 1 pom file", true);
		
		externals.add(new ExternalModuleInfo("module1", "module1", -1L, module1Commit.getId()));
		
		result = checkoutOrphan(repo, "module2", "master");
		
		Assert.assertNotEquals (CheckoutResult.ERROR_RESULT, result);
		
		// create the module 2 pom
		createModulePomFile (workingCopy, "module2", "2.0.0-FR1-SNAPSHOT", "1.0.0-FR1-SNAPSHOT");
		
		stageFiles(repo, "pom.xml");
		
		RevCommit module2Commit = commitBranch(repo, "created the module 2 pom file", true);
		
		externals.add(new ExternalModuleInfo("module2", "module2", -1L, module2Commit.getId()));
		
		
		checkOut(repo, "aggregate");
		
		return externals;
		
	}

	public static Ref checkOut(Repository repo, String branchName) throws RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException, GitAPIException {
	
		CheckoutCommand checkOutCommand = new Git (repo).checkout();
		
		checkOutCommand.setName(Constants.R_HEADS + branchName);
		
		checkOutCommand.setCreateBranch(false);
		
		checkOutCommand.setOrphan(false);

		return checkOutCommand.call();
		
	}
	
	private static DirCache stageFiles (Repository repo, String ...paths) throws NoFilepatternException, GitAPIException {
		AddCommand addCommand = new Git(repo).add();
		
		for (String path : paths) {
			
			addCommand.addFilepattern(path);
		}
		
		return addCommand.call();
	}
	private static RevCommit commitBranch(Repository repo, String message, boolean allKnownFiles) throws NoHeadException, NoMessageException, UnmergedPathsException, ConcurrentRefUpdateException, WrongRepositoryStateException, GitAPIException {
		
		CommitCommand commitCommand = new Git(repo).commit();
		
		PersonIdent ident;
		commitCommand.setAuthor(ident = new PersonIdent("admin", "admin@kuali.org"));
		
		commitCommand.setCommitter(ident);
		
		commitCommand.setAll(allKnownFiles);
		
		commitCommand.setMessage(message);
		
		return commitCommand.call();
		
	}
	
	private static Result createBaseCommit(Repository repo, String message) throws IOException {
		
		CommitBuilder builder = new CommitBuilder();
		
		PersonIdent ident;
		builder.setAuthor(ident = new PersonIdent("admin", "admin@kuali.org"));
		
		builder.setCommitter(ident);
		
		TreeFormatter tf = new TreeFormatter();
		
		ObjectInserter inserter = repo.newObjectInserter();

		ObjectId treeId = inserter.insert(tf);
		
		builder.setMessage(message);
		
		builder.setTreeId(treeId);
		
		ObjectId commit = inserter.insert(builder);
		
		RefUpdate update = repo.getRefDatabase().newUpdate(Constants.R_HEADS + "master", false);
		
		update.setNewObjectId(commit);
		
		Result result = update.update();
		
		return result;
		
	}
	private static void createModulePomFile(File targetPath, String moduleName, String parentPomVersion, String pomVersion) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new File (targetPath, "pom.xml"));
		
		pw.println("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\r\n" + 
				"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" + 
				"    <modelVersion>4.0.0</modelVersion>\r\n" + 
				"    <parent>\r\n" + 
				"    <groupId>org.kuali.maven.plugins</groupId>\r\n" + 
				"    <artifactId>externals-test</artifactId>\r\n" + 
				"        <version>"+parentPomVersion+"</version>\r\n" + 
				"    </parent>\r\n" + 
				"    <artifactId>" + moduleName + "</artifactId>\n" +
				"    <version>"+pomVersion+"</version>\r\n" + 
				"    <packaging>jar</packaging>");
	
		pw.println("</project>");
		
		pw.close();
	}

	private static void createParentPomFile(String repositoryName, File targetPath, String[]moduleNames, String pomVersion, String module1PomVersion, String module2PomVersion) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new File (targetPath, "pom.xml"));
		
		pw.println("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\r\n" + 
				"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n" + 
				"    <modelVersion>4.0.0</modelVersion>\r\n" + 
				"    <parent>\r\n" + 
				"        <groupId>org.kuali.pom</groupId>\r\n" + 
				"        <artifactId>kuali-common</artifactId>\r\n" + 
				"        <version>3.3.19</version>\r\n" + 
				"    </parent>\r\n" + 
				"    <groupId>org.kuali.maven.plugins</groupId>\r\n" + 
				"    <artifactId>externals-test</artifactId>\r\n" + 
				"    <version>"+pomVersion+"</version>\r\n" + 
				"    <packaging>pom</packaging>");
		

		// write out the properties
		pw.println("\t<properties>");
		pw.println("\t\t<plugin.externals.version>");
		pw.print("\t\t\t");
		
		
		String pluginVersion = MojoHelper.class.getPackage().getImplementationVersion();
		
		if (pluginVersion == null) {
			// assume running tests locally
			pluginVersion = "1.0.11-SNAPSHOT";
		}
		pw.println(pluginVersion);
		pw.println("\t\t</plugin.externals.version>");
		
		// module1.version
		pw.println("\t\t<module1.version>");
		pw.print("\t\t\t");
		pw.println(module1PomVersion);
		pw.println("\t\t</module1.version>");
		
		// module2.version
		pw.println("\t\t<module2.version>");
		pw.print("\t\t\t");
		pw.println(module2PomVersion);
		pw.println("\t\t</module2.version>");
		
		pw.println("\t</properties>");
		
		// scm section
		
		File repository = new File (BUILD_DIR, repositoryName);
		
		String scmString = "scm:git:file://localhost/" + repository.getAbsolutePath();
		
		pw.println("\t<scm>");

		pw.print("\t\t<connection>");
		pw.print(scmString);
		pw.println("</connection>");
		
		pw.print("\t\t<developerConnection>");
		pw.print(scmString);
		pw.println("</developerConnection>");
		
		pw.print("\t\t<url>");
		pw.print(scmString);
		pw.println("</url>");
        
        pw.println("\t</scm>");
        
		// write out the linkage to this version of the externals maven plugin
		// build -> plugin management -> plugins
		
		pw.println("\t<build>\n\t\t\t\t\t<plugins>");
		
		pw.println("<plugin>\r\n" + 
				"                    <groupId>org.kuali.maven.plugins</groupId>\r\n" + 
				"                    <artifactId>fusion-maven-plugin</artifactId>\r\n" + 
				"                    <configuration>\r\n" + 
				"                        <mappings>\r\n");
		
		
		for (String moduleName : moduleNames) {
			pw.print("<mapping>\r\n" + 
			"                                <module>"+moduleName+"</module>\r\n" + 
					"<branchName>" + moduleName + "</branchName>" +
			"                                <versionProperty>" + moduleName.replaceAll("-", ".") + ".version</versionProperty>\r\n" + 
			"                            </mapping>"); 
		}
		
		
				pw.println("</mappings>\r\n" + 
				"                    </configuration>\r\n" + 
				"                </plugin>");
		
		pw.println("\t\t\t</plugins>\n\t\t</build>");
		
		// write out the modules
		pw.println("\t<modules>");
		
		for (String moduleName : moduleNames) {
			pw.print("\t\t<module>\t\t\t");
			
			pw.println(moduleName);
			
			pw.println("\t\t</module>");
		}
		
		pw.println("\t</modules>");
		
		pw.println("</project>");
		
		pw.close();
	}
	
	public static String getRepositoryPath (String repositoryName, String repositorySubPath) {
		String repo = getRepositoryFile(repositoryName).getAbsolutePath();
		
		String repoPath = repo;
		
		if (repositorySubPath != null && !repositorySubPath.isEmpty()) {
			repoPath = repo + "/" + repositorySubPath;
		}
		
		return repoPath;
		
	}

	public static File checkOut(String repositoryName, String repositorySubPath, String workingCopyName, String userName, String password) {
		
		File workingCopy = getWorkingCopyFile(repositoryName, workingCopyName);
		
		String repoPath = getRepositoryPath(repositoryName, repositorySubPath);
		
//		long rev = SVNUtils.getInstance().checkout(repoPath, workingCopy, null, null);
		
		return workingCopy;
	}
	public static File checkOut(String repositoryName, String repositorySubPath, String	userName, String password) {
		return checkOut(repositoryName, repositorySubPath, repositoryName + "-wc", userName, password);
	}

}
