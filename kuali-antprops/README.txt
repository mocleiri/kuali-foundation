This project is a copy of the source code from http://ant.apache.org/antlibs/props as of 11/16/2012.

It's only purpose is to add nested property expansion support to Ant build scripts

To build this project you must successfully run a combination 
of both Maven and Ant commands in the order specified

# Remove Maven's /target directory
mvn clean
# Use Ant to create the binary
ant clean antlib
# Create a jar from the build/classes directory Ant created
mvn install

To run this all in one command:

mvn clean; ant clean antlib javadoc; mvn install


To release the project:

mvn release:prepare release:perform -B
cd target/checkout
mvn clean; ant clean antlib javadoc; mvn deploy -Pkuali-release
mvn deploy nexus:staging-close nexus:staging-release -Pkuali-release,sonatype-oss-release -e


