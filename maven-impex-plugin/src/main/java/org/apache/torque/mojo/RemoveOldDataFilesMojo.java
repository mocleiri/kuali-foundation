package org.apache.torque.mojo;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.torque.engine.database.model.Database;
import org.apache.torque.engine.database.model.Table;
import org.kuali.core.db.torque.SetUtils;
import org.kuali.core.db.torque.Utils;

/**
 * @goal remove-old-data-files
 */
public class RemoveOldDataFilesMojo extends BaseMojo {
	private static final String FS = System.getProperty("file.separator");

	/**
	 * @parameter expression="${extension}" default-value=".xml"
	 * @required
	 */
	private String extension;

	/**
	 * @parameter expression="${dataDir}" default-value="${project.basedir}/src/main/impex"
	 * @required
	 */
	private File dataDir;

	/**
	 * @parameter expression="${dataDirIncludes}" default-value="*.xml"
	 */
	private String dataDirIncludes;

	/**
	 * @parameter expression="${dataDirExcludes}" default-value="schema.xml"
	 */
	private String dataDirExcludes;

	/**
	 * @parameter expression="${schemaXMLFile}" default-value="src/main/impex/schema.xml"
	 */
	private String schemaXMLFile;

	/**
	 * @parameter expression="${targetDatabase}" default-value="oracle"
	 */
	private String targetDatabase;

	@Override
	protected void executeMojo() throws MojoExecutionException, MojoFailureException {
		Utils utils = new Utils();
		try {
			Database db = utils.getDatabase(schemaXMLFile, targetDatabase);
			DirectoryScanner ds = getDirectoryScanner();
			Set<File> existing = getExistingFiles(ds);
			Set<File> allowed = getDatabaseFiles(db);
			Set<File> illegal = SetUtils.difference(existing, allowed);
			for (File file : illegal) {
				getLog().info("Removing " + file.getAbsolutePath());
			}

		} catch (Exception e) {
			throw new MojoExecutionException("Error executing mojo", e);
		}
	}

	protected Set<File> getDatabaseFiles(Database db) {
		List<?> tables = db.getTables();
		Set<File> files = new TreeSet<File>();
		for (Object object : tables) {
			Table table = (Table) object;
			String tableName = table.getName();
			String filename = dataDir.getAbsolutePath() + FS + tableName + extension;
			File file = new File(filename);
			files.add(file);
		}
		return files;
	}

	protected Set<File> getExistingFiles(DirectoryScanner ds) {
		ds.scan();
		String[] relativeFilenames = ds.getIncludedFiles();
		Set<File> files = new TreeSet<File>();
		for (int i = 0; i < relativeFilenames.length; i++) {
			String filename = ds.getBasedir().getAbsolutePath() + FS + relativeFilenames[i];
			File file = new File(filename);
			files.add(file);
		}
		return files;
	}

	protected DirectoryScanner getDirectoryScanner() {
		DirectoryScanner ds = new DirectoryScanner();
		ds.setBasedir(dataDir);
		ds.setIncludes(new String[] { dataDirIncludes });
		ds.setExcludes(new String[] { dataDirExcludes });
		return ds;
	}

	protected File getFile(Table table) {
		String tableName = table.getName();
		String filename = dataDir.getAbsolutePath() + FS + tableName + extension;
		File file = new File(filename);
		return file;
	}

	public File getDataDir() {
		return dataDir;
	}

	public void setDataDir(File dataDir) {
		this.dataDir = dataDir;
	}

	public String getDataDirIncludes() {
		return dataDirIncludes;
	}

	public void setDataDirIncludes(String dataDirIncludes) {
		this.dataDirIncludes = dataDirIncludes;
	}

	public String getDataDirExcludes() {
		return dataDirExcludes;
	}

	public void setDataDirExcludes(String dataDirExcludes) {
		this.dataDirExcludes = dataDirExcludes;
	}

	public String getSchemaXMLFile() {
		return schemaXMLFile;
	}

	public void setSchemaXMLFile(String schemaXMLFile) {
		this.schemaXMLFile = schemaXMLFile;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getTargetDatabase() {
		return targetDatabase;
	}

	public void setTargetDatabase(String targetDatabase) {
		this.targetDatabase = targetDatabase;
	}

}
