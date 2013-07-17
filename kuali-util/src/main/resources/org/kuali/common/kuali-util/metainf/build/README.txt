Any files containing properties that can only be resolved at build time must go here.

These are typically project properties that have the word "build" in them, eg "project.build.directory".

A project property like "project.version" is ok to use in a properties file outside this directory.

"project.version" is included in the project.properties file bundled into every Kuali jar.

It is thus available in a transparent way to both runtime and build time processes.

