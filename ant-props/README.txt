To build this project you must successfully run a combination 
of both Maven and Ant commands in the order specified

# Remove any Maven residuals
mvn clean
# Use Ant to create the binary
ant clean antlib
# Create a jar from the build/classes directory Ant created
mvn install
