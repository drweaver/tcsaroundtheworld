[![Build Status](https://travis-ci.org/drweaver/tcsaroundtheworld.png?branch=master)](https://travis-ci.org/drweaver/tcsaroundtheworld)

##TCS Around the World

A site for sharing location of people with Treacher Collins Syndrome

##Deploying

Add file:
```
src/submit/org/tcsaroundtheworld/submit/server/ReCaptchaPrivateKeys.properties
```

Add admin email address in:
```
src/common/org/tcsaroundtheworld/common/server/email.properties
```
e.g.:
```
admin = myemail@domain.com
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
