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

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.kuali.common.util.Assert;
import org.kuali.common.util.enc.KeyPair;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;

/**
 * Generate a public key / private key pair
 * 
 * @goal keypair
 */
public class KeyPairMojo extends AbstractMojo {

	private static final String UTF8 = "UTF-8";

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
	 * @parameter expression="${enc.size}" default-value=2048
	 * @required
	 */
	private int size;

	/**
	 * 
	 * The key name
	 * 
	 * @parameter expression="${enc.name}" default-value=${user.name}
	 * @required
	 */
	private String name;

	@Override
	public void execute() throws MojoExecutionException {
		Assert.noBlanks(name);
		Assert.noNulls(algorithm, project);
		Assert.positive(size);
	}

	protected KeyPair getKeyPair(String name, int size, Algorithm algorithm) {
		int type = (Algorithm.RSA == algorithm) ? com.jcraft.jsch.KeyPair.RSA : com.jcraft.jsch.KeyPair.DSA;
		JSch jsch = new JSch();
		com.jcraft.jsch.KeyPair keyPair = getKeyPair(jsch, type, size);
		String publicKey = getPublicKey(keyPair, name).trim();
		String privateKey = getPrivateKey(keyPair);
		return new KeyPair.Builder(name).publicKey(publicKey).privateKey(privateKey).build();
	}

	protected com.jcraft.jsch.KeyPair getKeyPair(JSch jsch, int type, int size) {
		try {
			return com.jcraft.jsch.KeyPair.genKeyPair(jsch, type, size);
		} catch (JSchException e) {
			throw new IllegalStateException(e);
		}
	}

	protected String getPrivateKey(com.jcraft.jsch.KeyPair keyPair) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePrivateKey(out);
		return toUTF8String(out);
	}

	protected String getPublicKey(com.jcraft.jsch.KeyPair keyPair, String name) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		keyPair.writePublicKey(out, name);
		return toUTF8String(out);
	}

	protected String toUTF8String(ByteArrayOutputStream out) {
		try {
			return out.toString(UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

}
