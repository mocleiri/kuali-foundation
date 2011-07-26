package org.codehaus.mojo.exec;

/*
 * Copyright 2005 The Codehaus.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * @author Jerome Lacoste <jerome@coffeebreaks.org>
 * @version $Id: NoMain.java 6588 2008-03-28 12:22:57Z bentmann $
 */
public class NoMain
{
    // MEXEC-29 wrong interface for main method causes NPE
    public static void main( ) throws Exception {
 
    }
}
