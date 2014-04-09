/**
 * 
 */
package org.kuali.maven.plugins.fusion.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.eclipse.jgit.lib.CommitBuilder;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectInserter;
import org.eclipse.jgit.lib.PersonIdent;

/**
 * @author ocleirig
 *
 */
public final class GitFusionUtils {

	/**
	 * 
	 */
	public GitFusionUtils() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Used to create the fused commit or any commit where we want to be able to set the parents and tree programatically instead of from the working copy.
	 * 
	 * @param objectInserter
	 * @param userName
	 * @param email
	 * @param commitMessage
	 * @param treeId
	 * @param mergeParents
	 * @return
	 * @throws IOException
	 */
	public static ObjectId commit(ObjectInserter objectInserter, String userName, String email, String commitMessage, ObjectId treeId, Set<ObjectId>mergeParents) throws IOException {
		
		CommitBuilder commitBuilder = new CommitBuilder();

		PersonIdent ident = new PersonIdent(userName, email);

		commitBuilder.setAuthor(ident);
		commitBuilder.setCommitter(ident);

		commitBuilder.setMessage(commitMessage);

		commitBuilder.setTreeId(treeId);

		commitBuilder.setParentIds(new ArrayList<>(mergeParents));

		commitBuilder.setEncoding("UTF-8");

		ObjectId commitId = objectInserter.insert(commitBuilder);
		
		return commitId;
	}
	

}
