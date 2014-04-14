#!/bin/bash
#
# Copyright 2004-2014 The Kuali Foundation
#
# Licensed under the Educational Community License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.opensource.org/licenses/ecl2.php
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

check_not_blank AES_PASSPHRASE $AES_PASSPHRASE

ACCESS_KEY=U2FsdGVkX1+8Ztcpl1oE1zvItuWZ+U/B1m1Y5ZfnPL1/RL3ytfTW0GPQ0T2I6f3x
SECRET_KEY=U2FsdGVkX19iFvmWrQbvBA+aoUjCW37k8hFzcRrDzjc6KDOfEYUd7m8/nyvy/YBZghfgT88GJhlQVk4aD02eWg==

ACCESS_KEY=$(decrypt $ACCESS_KEY $AES_PASSPHRASE)
SECRET_KEY=$(decrypt $SECRET_KEY $AES_PASSPHRASE)
