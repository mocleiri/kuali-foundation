#!/bin/sh
#
# Copyright 2004-2013 The Kuali Foundation
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


#
# This script is invoked during the Tomcat startup sequence BEFORE Tomcat starts
#

#
# All of the properties listed below default to a shell script comment, override them as necessary
#

# Environment setup that applies Kuali wide
${setenv.kuali.content}

# Environment setup specific to a Kuali group eg, Rice, Student, or OLE
${setenv.group.content}

# Environment setup specific to a Kuali application eg rice-sampleapp or ks-embedded
${setenv.app.content}

# Environment setup specific to a version of a Kuali application eg config for 1.0 might be different from 2.0
${setenv.version.content}

# Environment setup specific to the environment the application is deployed to
${setenv.env.content}

