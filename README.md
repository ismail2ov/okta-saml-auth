# okta-saml-auth
OKTA SAML 2.0 SSO authentication library for Play framework 2 in Java

## Usage

### Add authentication paths in your routes file

GET     /auth              @es.develex.saml.OktaManager.checkAuth()

POST    /auth              @es.develex.saml.OktaManager.checkAuth()

GET     /logout            @es.develex.saml.OktaManager.logout()


### Add configuration
okta.consumer.service.url = "http://localhost:9000/loginCallback"

okta.identity.sso.url = "https://dev-158522.oktapreview.com/app/develexdev158522_playframeworkjavademo_1/exk5m59xw1ulM0sbl0h7/sso/saml"

okta.identity.issuer = "http://www.okta.com/exk5m59xw1ulM0sbl0h7"

okta.sertificate.file = "conf/resources/okta.cert"


### Put annotations for controllers or methods to be protected
@OktaAuth
