- Updated JDBC drivers that are in sync with Amazon RDS
- The JDBC drivers checked into src/main/resources/drivers/*.jar are deleted before Maven packages this project
- The JDBC drivers in this directory are then copied into classes/drivers/*.jar so the Ant build scripting can find and use them
- The reason for this, is that Oracle blows up if you try and run this tooling against an Oracle 11g database using the ojdbc6.jar checked into SVN

- This whole setup is a completely hacked together bunch of ridiculousness.
