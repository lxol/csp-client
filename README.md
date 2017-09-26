CSP Client
==========

Client library to ease implementation of Customer Service Platform products.
----------------------------------------------------------------------------

To use this in your project you will need to do the following steps:

1. include the client library
  * add `"uk.gov.hmrc" %% "csp-client" % "x.y.z"` to your project dependencies
2. place the following code in the scala.html template where you want the webchat tag to appear
  * `@uk.gov.hmrc.csp.WebchatClient.webchatOfferPartial()`
  * (this example is for the offer based webchat)
3. Get the offer urls setup on the eGain servers. The CSP team can help with this.     


Notes:
------

  * Please have a look a the webchat-frontend example application for implementation examples.
  * If you are intending to use cobrowse then it is strongly recommended that the tag appears on all pages to allow tracking sessions.
  In this case clearly the tag should be placed in your primary extension of hmrcGovUkTemplate. (pop ups will still only appear where configured on the eGain servers)
  * the client library contains typical config for the partial service endpoints.  Any entries in the application.conf file of your microservice will override them - for better or worse!
  * Consider wrapping the implementation in a feature flag to give you a config kill switch - just in case!
