/**
 * Copyright 2013-2013 The Kuali Foundation
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
package org.kuali.maven.plugins.ssh;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.kuali.common.util.Assert;
import org.kuali.common.util.FileSystemUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.kuali.common.util.ssh.api.SshService;
import org.kuali.common.util.ssh.api.impl.DefaultSshService;
import org.kuali.common.util.ssh.model.Algorithm;
import org.kuali.common.util.ssh.model.GenerateKeyPairContext;
import org.kuali.common.util.ssh.model.KeyPair;

/**
 * Generate a public key / private key pair in the format desired by AWS (Amazon Web Services)
 */
@Mojo(name = "keypair", threadSafe = true)
@Execute(goal = "keypair")
public class KeyPairMojo extends AbstractMojo {

	/**
	 * The algorithm to use, RSA or DSA
	 */
	@Parameter(property = "ssh.algorithm", required = true, defaultValue = "RSA")
	private Algorithm algorithm;

	/**
	 * The key size
	 */
	@Parameter(property = "ssh.keySize", required = true, defaultValue = "2048")
	private int size;

	/**
	 * The key name
	 */
	@Parameter(property = "ssh.keyName", required = true, defaultValue = "${user.name}")
	private String keyName;

	/**
	 * The file where the public key is generated
	 */
	@Parameter(property = "ssh.publicKey", required = true, defaultValue = "./target/ssh/id_rsa.pub")
	private File publicKey;

	/**
	 * The file where the private key is generated
	 */
	@Parameter(property = "ssh.privateKey", required = true, defaultValue = "./target/ssh/id_rsa")
	private File privateKey;

	@Override
	public void execute() {
		Assert.noNulls(publicKey, privateKey);
		GenerateKeyPairContext context = new GenerateKeyPairContext.Builder(keyName).algorithm(algorithm).size(size).build();
		SshService service = new DefaultSshService();
		KeyPair keyPair = service.generateKeyPair(context);
		Assert.present(keyPair.getPrivateKey());
		Assert.present(keyPair.getPublicKey());
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
		return FileSystemUtils.getRelativePathQuietly(CanonicalFile.cwd(), file).substring(1);
	}
}
