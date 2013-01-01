/**
 * Copyright 2010-2013 The Kuali Foundation
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
file = new File(basedir, 'LICENSE.html');
assert file.exists();

content = file.text;
assert !content.contains(';; This line will be removed!1');
assert content.contains('<html>');
assert content.contains('My license!');
assert content.contains('</html>');

file = new File(basedir, 'target/generated-sources/license/LICENSE.html');
assert file.exists();

content = file.text;
assert !content.contains(';; This line will be removed!2');
assert content.contains('<html>');
assert content.contains('My license!');
assert content.contains('</html>');

file = new File(basedir, 'target/generated-sources/license/META-INF/bundleLicense.html');
assert file.exists();

content = file.text;
assert !content.contains(';; This line will be removed!3');
assert content.contains('<html>');
assert content.contains('My license!');
assert content.contains('</html>');

file = new File(basedir, 'target/classes/LICENSE.html');
assert file.exists();

content = file.text;
assert !content.contains(';; This line will be removed!4');
assert content.contains('<html>');
assert content.contains('My license!');
assert content.contains('</html>');


file = new File(basedir, 'target/classes/META-INF/bundleLicense.html');
assert file.exists();

content = file.text;
assert !content.contains(';; This line will be removed!5');
assert content.contains('<html>');
assert content.contains('My license!');
assert content.contains('</html>');



return true;
