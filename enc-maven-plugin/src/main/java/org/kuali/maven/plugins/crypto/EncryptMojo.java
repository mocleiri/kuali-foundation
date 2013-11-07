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
package org.kuali.maven.plugins.crypto;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.jasypt.util.text.BasicTextEncryptor;

/**
 * Encrypt the specified text using the specified password
 *
 * @goal encrypt
 */
public class EncryptMojo extends AbstractMojo {

    /**
     *
     * The password for encrypting text. This same password can be used to to decrypt the encrypted text
     *
     * @parameter expression="${crypto.password}"
     * @required
     */
    private String password;

    /**
     *
     * The text to encrypt.
     *
     * @parameter expression="${crypto.text}"
     * @required
     */
    private String text;

    @Override
    public void execute() throws MojoExecutionException {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(password);
        String encrypted = encryptor.encrypt(text);
        getLog().info(text + " -> " + encrypted);
    }

}
