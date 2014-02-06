All of the XML files and .properties files under META-INF should be considered deprecated.

They are replaced by more sophisticated configuration techniques, eg Spring Annotated Java classes.

They remain here only for backwards compatibility reasons.

Any code that works with these files *must* be marked with @Deprecated as a warning.