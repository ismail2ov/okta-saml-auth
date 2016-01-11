package es.develex.saml;

import play.Play;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Map;

import static play.mvc.Controller.flash;
import static play.mvc.Controller.session;

public class OktaAuthAction extends Action<OktaAuth> {

    public F.Promise<Result> call(Http.Context ctx) throws Throwable {

        String user = session("user");
        if (session("user") != null) {

            return delegate.call(ctx);

        } else {
            Http.Request request = Controller.request();
            flash("requiredUrl", request.uri());

            play.Configuration config = Play.application().configuration();

            String consumerServiceUrl = config.getString("okta.consumer.service.url");
            String identitySsoUrl = config.getString("okta.identity.sso.url");
            String identityIssuer = config.getString("okta.identity.issuer");

            Configuration configuration = new Configuration();
            configuration.setConsumerServiceUrl(consumerServiceUrl);
            configuration.setIssuer(identityIssuer);
            configuration.setIdentitySsoUrl(identitySsoUrl);

            AuthRequest authReq = new AuthRequest(configuration);

            Map<String, String[]> params = request.queryString();
            String relayState = null;
            for (String parameter : params.keySet()) {
                if (parameter.equalsIgnoreCase("relaystate")) {
                    String[] values = params.get(parameter);
                    relayState = values[0];
                }
            }

            String reqString = authReq.getSSOurl(relayState);

            return F.Promise.pure(redirect(reqString));
        }

    }
}
