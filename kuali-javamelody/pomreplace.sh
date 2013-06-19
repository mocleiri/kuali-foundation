find . -name 'pom.xml' -print0 | xargs -0 perl -pi -e 's|1.43.0-SNAPSHOT|1.43-patch1|g'

