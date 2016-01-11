package es.develex.saml;

import es.develex.saml.util.CertificateManager;
import play.Play;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class OktaManager extends Controller {

    public Result checkAuth() throws Exception {

        CertificateManager certificateManager = new CertificateManager();
        play.Configuration configuration = Play.application().configuration();

        String certificatePath = configuration.getString("okta.sertificate.file");
        certificateManager.setCertificatePath(certificatePath);
        certificateManager.loadCertificate();

        Map<String, String[]> params = getRequestParameters();
        String samlResponseParam = getRequestParameter("SAMLResponse");

        Response samlResponse = new Response(certificateManager,
                samlResponseParam,
                absoluteURL());

        if (samlResponse.isValid()) {
            String nameId = samlResponse.getNameId();
            session("user", nameId);

            String requiredUrl = flash("requiredUrl");
            if(requiredUrl != null) {
                return redirect(requiredUrl);
            }

            return redirect("/");

        } else {
            String unauthorizedPage = configuration.getString("okta.unauthorized.page");

            if (unauthorizedPage != null) {
                return redirect("/" + unauthorizedPage);
            } else {
                return unauthorized("Not Authorized");
            }
        }
    }

    public Result logout() {
        session().clear();

        String redirect = Play.application().configuration().getString("okta.page.after.logout");

        if (redirect != null) {
            return redirect(redirect);
        } else {
            return redirect("/");
        }

    }

    public String absoluteURL() {
        return "http" + (request().secure() ? "s" : "") + "://" + request().host() + request().uri();
    }

    public String getRequestParameter(final String name) {
        final Map<String, String[]> parameters = getRequestParameters();
        final String[] values = parameters.get(name);
        if (values != null && values.length > 0) {
            return values[0];
        }

        return null;
    }

    public Map<String, String[]> getRequestParameters() {
        final Http.RequestBody body = request().body();
        final Map<String, String[]> formParameters;
        if (body != null) {
            formParameters = body.asFormUrlEncoded();
        } else {
            formParameters = new HashMap<String, String[]>();
        }
        final Map<String, String[]> urlParameters = request().queryString();
        final Map<String, String[]> parameters = new HashMap<String, String[]>();
        if (formParameters != null) {
            parameters.putAll(formParameters);
        }
        if (urlParameters != null) {
            parameters.putAll(urlParameters);
        }

        return parameters;
    }

}
