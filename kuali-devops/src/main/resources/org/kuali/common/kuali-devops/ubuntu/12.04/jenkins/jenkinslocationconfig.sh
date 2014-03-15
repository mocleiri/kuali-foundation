#
# Used to overwrite the DNS name bundled into the master.zip.gpg file
#

JENKINS_LOCATION_XML_LINE1="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
JENKINS_LOCATION_XML_LINE2="<jenkins.model.JenkinsLocationConfiguration>"
JENKINS_LOCATION_XML_LINE3="<adminAddress>Kuali CI &lt;$JENKINS_ADMIN_EMAIL_ADDRESS&gt;</adminAddress>"
JENKINS_LOCATION_XML_LINE4="<jenkinsUrl>https://$JENKINS_MASTER/</jenkinsUrl>"
JENKINS_LOCATION_XML_LINE5="</jenkins.model.JenkinsLocationConfiguration>"
