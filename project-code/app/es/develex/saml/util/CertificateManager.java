package es.develex.saml.util;

import play.Play;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;


public class CertificateManager {

    private String certificatePath;
    private Certificate certificate;

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public void loadCertificate() throws CertificateException, FileNotFoundException {

        File certificateFile = getCertificateFile();

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        BufferedInputStream imput = new BufferedInputStream(new FileInputStream(certificateFile));

        this.certificate = cf.generateCertificate(imput);
    }

    public File getCertificateFile() {

        return Play.application().getFile(this.certificatePath);
    }

    public Certificate getIdpCert() throws CertificateException, FileNotFoundException {
        if (this.certificate == null) {
            loadCertificate();
        }

        return this.certificate;
    }

}
