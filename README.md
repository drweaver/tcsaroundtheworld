[![Build Status](https://travis-ci.org/drweaver/tcsaroundtheworld.png?branch=master)](https://travis-ci.org/drweaver/tcsaroundtheworld)

##TCS Around the World

A site for sharing location of people with Treacher Collins Syndrome hosted at http://tcsaroundtheworld.appspot.com/

##Design & Technologies

 - Hosted at [Google App Engine](https://developers.google.com/appengine/docs/whatisgoogleappengine) making use of:
   - Admin Access
   - Datastore
   - Image Service
   - Job schedule (cron)
   - Mail Service
 - [Google Web Toolkit](http://www.gwtproject.org) (java to javascript, UI Widgets)
 - [ReCaptcha](https://www.google.com/recaptcha/intro/index.html)
 - [Google Maps API v2](https://developers.google.com/maps/documentation/javascript/v2/reference?csw=1)

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
