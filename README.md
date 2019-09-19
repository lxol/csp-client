CSP Client
==========
[ ![Download](https://api.bintray.com/packages/hmrc/releases/csp-client/images/download.svg) ](https://bintray.com/hmrc/releases/csp-client/_latestVersion)

Client library to ease implementation of Customer Service Platform products.
----------------------------------------------------------------------------

**NB. The last supported version for the 3.x range is 3.4**

**For Play 2.5:**
To use this in your project you will need to do the following steps:

1. Add `"uk.gov.hmrc" %% "csp-client" % "x.y.z"` to your project dependencies
2. Inject the client library into your controller where you will use the needed views and pass it into your view either directly or implicitly. 
3. Call the webchat client in the scala.html template where you want the webchat tag to appear
  * `@webchatClient.webchatOfferPartial()`
  * (this example is for the offer based webchat)
3. Get the offer urls setup on the eGain servers. The CSP team can help with this.     

**For Play 2.6:**
To use this in your project you will need to do the following steps:

1. Add `"uk.gov.hmrc" %% "csp-client" % "x.y.z"` to your project dependencies
2. Inject the client library into your views that need webchat
  * `@this(webchatClient: WebchatClient)`
3. Call the webchat client in the scala.html template where you want the webchat tag to appear
  * `@webchatClient.webchatOfferPartial()`
  * (this example is for the offer based webchat)
3. Get the offer urls setup on the eGain servers. The CSP team can help with this. 

Notes:
------
  * You will need  webchat-frontend and csp-partials sections to your services in application.conf in any implementing service
  * Please have a look a the webchat-frontend example application for implementation examples.
  * If you are intending to use cobrowse then it is strongly recommended that the tag appears on all pages to allow tracking sessions.
  In this case clearly the tag should be placed in your primary extension of hmrcGovUkTemplate. (pop ups will still only appear where configured on the eGain servers)
  * the client library contains typical config for the partial service endpoints.  Any entries in the application.conf file of your microservice will override them - for better or worse!
  * Consider wrapping the implementation in a feature flag to give you a config kill switch - just in case!
