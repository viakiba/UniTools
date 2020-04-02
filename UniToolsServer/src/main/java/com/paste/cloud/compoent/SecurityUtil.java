package com.paste.cloud.compoent;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SecurityUtil {

    public static String hash256(String payload) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(payload.getBytes(Constants.UTF_8));
            byte[] digest = md.digest();
            String hex = String.format("%064x", new BigInteger(1, digest));
            return hex;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
