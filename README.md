# decora-backend-usuario

**Dependencies**

* Maven
* >= JDK7
* MongoDB (I'm using docker to run mongodb in another process)

**Pre-configurations**

Change the Resources.java to reflect MongoDB host.

**Running**

To run you need to run this command:

```bash
mvn clean wildfly:run
```

If needs a package use this command:

```bash
mvn clean package
```

**Improvements**

* The password is saved in plain text, needs to be changed.
* MongoDB host is hardcoded, needs to use 12factor concept using external variables.
* Not using JWT, for better authentication, needs to use JWT.
* Using wildfly maven plugin, but can use external wildfly.
