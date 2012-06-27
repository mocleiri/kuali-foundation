#!/bin/sh

#
# This script is invoked during the Tomcat startup sequence BEFORE Tomcat starts
#

#
# All of the properties listed below default to a shell script comment, override them as necessary
#

# Environment setup that applies Kuali wide
${setenv.kuali.content}

# Environment setup specific to a Kuali project eg, Rice, Student, or OLE
${setenv.group.content}

# Environment setup specific to a Kuali application eg rice-sampleapp or ks-embedded
${setenv.app.content}

# Environment setup specific to a version of a Kuali application eg config for 1.0 might be very different from 2.0
${setenv.version.content}

# Environment setup specific to the environment the application is deployed to
${setenv.env.content}

