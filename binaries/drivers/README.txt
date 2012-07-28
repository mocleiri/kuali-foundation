ojdbc6.jar = ojdbc6_g-11.2.0.2.jar

The reason this is here is that the Ant scripting blows up when connecting to an Oracle 11g db using the old driver.
We overlay the new jar on top of the old jar (thus replacing it) so the Ant scripting will function correctly