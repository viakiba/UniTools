package com.paste.cloud.compoent;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtil implements CommandLineRunner {

    @Value("${security.key}")
    private String aesKey ;

    private byte[] aesKeyByte ;

    public String getToken(String payLoad){
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .expirationTime(new Date(new Date().getTime() + 24 * 60 * 60 * 1000))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256),claimsSet);
        try {
            signedJWT.sign( new MACSigner(aesKeyByte));
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return signedJWT.serialize();
    }

    public boolean verifyToken(String jwtStr){
        try {
            SignedJWT parse = SignedJWT.parse(jwtStr);
            JWSVerifier verifier = new MACVerifier(aesKeyByte);
            return parse.verify(verifier);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void run(String... args) throws Exception {
        aesKeyByte = aesKey.getBytes(Constants.UTF_8);
    }
}
