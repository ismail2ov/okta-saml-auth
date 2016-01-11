package es.develex.saml;

public class Configuration {
    private String consumerServiceUrl;
    private String issuer;
    private String identitySsoUrl;

    public String getConsumerServiceUrl() {
        return consumerServiceUrl;
    }

    public void setConsumerServiceUrl(String assertionConsumerServiceUrl) {
        this.consumerServiceUrl = assertionConsumerServiceUrl;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIdentitySsoUrl() {
        return identitySsoUrl;
    }

    public void setIdentitySsoUrl(String identitySsoUrl) {
        this.identitySsoUrl = identitySsoUrl;
    }
}
