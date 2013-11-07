/**
 * Copyright 2009-2012 The Kuali Foundation
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
package org.kuali.maven.plugins.enc;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.enc.Algorithm;
import org.kuali.common.util.enc.EncUtils;
import org.kuali.common.util.enc.KeyPair;
import org.kuali.common.util.file.CanonicalFile;

/**
 * Generate a public key / private key pair suitable for use with AWS (Amazon Web Services)
 * 
 * @goal keypair
 */
public class KeyPairMojo extends AbstractMojo {

	/**
	 * The Maven project object
	 * 
	 * @parameter expression="${project}"
	 * @readonly
	 */
	private MavenProject project;

	/**
	 * 
	 * The algorithm to use, RSA or DSA
	 * 
	 * @parameter expression="${enc.algorithm}" default-value="RSA"
	 * @required
	 */
	private Algorithm algorithm;

	/**
	 * 
	 * The key size
	 * 
	 * @parameter expression="${enc.size}" default-value="2048"
	 * @required
	 */
	private int size;

	/**
	 * 
	 * The key name
	 * 
	 * @parameter expression="${enc.name}" default-value="${user.name}"
	 * @required
	 */
	private String name;

	/**
	 * 
	 * The file where the public key is generated
	 * 
	 * @parameter expression="${enc.publicKey}" default-value="${project.build.directory}/enc/id_rsa.pub"
	 * @required
	 */
	private File publicKey;

	/**
	 * 
	 * The file where the private key is generated
	 * 
	 * @parameter expression="${enc.publicKey}" default-value="${project.build.directory}/enc/id_rsa"
	 * @required
	 */
	private File privateKey;

	@Override
	public void execute() {
		Assert.noBlanks(name);
		Assert.noNulls(algorithm, project, publicKey, privateKey);
		Assert.positive(size);
		KeyPair keyPair = EncUtils.getKeyPair(name, size, algorithm);
		write(keyPair);
	}

	protected void write(KeyPair keyPair) {
		try {
			getLog().info("Public  Key generated to -> " + getRelativePath(publicKey));
			FileUtils.write(publicKey, keyPair.getPublicKey().get());
			getLog().info("Private Key generated to -> " + getRelativePath(privateKey));
			FileUtils.write(privateKey, keyPair.getPrivateKey().get());
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
	}

	protected String getRelativePath(File file) {
		File parentDir = new CanonicalFile(project.getBasedir());
		return FileSystemUtils.getRelativePathQuietly(parentDir, file).substring(1);
	}
}
