/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.codehaus.mojo.license.header.transformer;

/**
 * Implementation of {@link FileHeaderTransformer} for PHP format.
 *
 * @author tchemit <chemit@codelutin.com>
 * @author kmorin <kmorin@codelutin.com>
 * @plexus.component role-hint="php"
 * @since 1.0
 */
public class PhpFileHeaderTransformer
    extends AbstractFileHeaderTransformer
{

    public static final String NAME = "php";

    public static final String DESCRIPTION = "header transformer with php comment style";

    public static final String COMMENT_LINE_PREFIX = " * ";

    public static final String COMMENT_START_TAG = "<?php /*";

    public static final String COMMENT_END_TAG = " */ ?>";

    public PhpFileHeaderTransformer()
    {
        super( NAME, DESCRIPTION, COMMENT_START_TAG, COMMENT_END_TAG, COMMENT_LINE_PREFIX );
    }

    public String[] getDefaultAcceptedExtensions()
    {
        return new String[]{ NAME };
    }

}
