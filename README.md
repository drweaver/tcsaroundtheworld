[![Build Status](https://travis-ci.org/drweaver/tcsaroundtheworld.png?branch=master)](https://travis-ci.org/drweaver/tcsaroundtheworld)

##TCS Around the World

A site for sharing location of people with Treacher Collins Syndrome

##Deploying

Add file 
```
src/submit/org/tcsaroundtheworld/submit/server/ReCaptchaPrivateKeys.properties
```
Edit email addresses in:
```
src/admin/org/tcsaroundtheworld/admin/server/AwaitingApprovalsServlet.java
src/submit/org/tcsaroundtheworld/submit/server/SubmissionServiceImpl.java
```
