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
package org.codehaus.mojo.license.header;

import java.io.IOException;

/**
 * Exception to be thrown when a file header could not be read or transformed
 * back to a {@link FileHeader}
 *
 * @author tchemit <chemit@codelutin.com>
 * @since 1.0
 */
public class InvalideFileHeaderException
    extends IOException
{
    private static final long serialVersionUID = 1L;

    public InvalideFileHeaderException()
    {
    }

    public InvalideFileHeaderException( String message )
    {
        super( message );
    }
}
