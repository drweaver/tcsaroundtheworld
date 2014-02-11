[![Build Status](https://travis-ci.org/drweaver/tcsaroundtheworld.png?branch=master)](https://travis-ci.org/drweaver/tcsaroundtheworld)

##TCS Around the World

A site for sharing location of people with Treacher Collins Syndrome

##Deploying

Add file:
```
src/submit/org/tcsaroundtheworld/submit/server/ReCaptchaPrivateKeys.properties
```

Edit email addresses in:
```
src/admin/org/tcsaroundtheworld/admin/server/AwaitingApprovalsServlet.java
src/submit/org/tcsaroundtheworld/submit/server/SubmissionServiceImpl.java
```

Install Ant and JDK7

Test and build the files:
```bash
ant test.dev build
```

Update the version number in:
```
war/WEB-INF/appengine-web.xml
```

Upload to appengine:
```bash
appengine-java-sdk-<version>/bin/appcfg.sh update war
```
