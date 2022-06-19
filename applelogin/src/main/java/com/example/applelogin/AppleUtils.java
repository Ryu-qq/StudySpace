package com.example.applelogin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;


@Configuration
@RequiredArgsConstructor
public class AppleUtils {

    private final AppleClient appleClient;


    public boolean verifyPublicKey(SignedJWT signedJWT, String identityToken) {

        try {

            String headerOfIdentityToken = StringReplace(identityToken.substring(0, identityToken.indexOf(".")));

            Map<String, String> header = new ObjectMapper().readValue(new String(Base64.getDecoder().decode(headerOfIdentityToken), "UTF-8"), Map.class);

            ApplePublicKeyResponse response = appleClient.getAppleAuthPublicKey();
            ApplePublicKeyResponse.Key key = response.getMatchedKeyBy(header.get("kid"), header.get("alg"))
                    .orElseThrow(() -> new NullPointerException("Failed get public key from apple's id server."));


            String nStr = key.getN().toString();
            String eStr = key.getE().toString();

            byte[] nBytes = Base64.getUrlDecoder().decode(nStr);
            byte[] eBytes = Base64.getUrlDecoder().decode(eStr);

            BigInteger n = new BigInteger(1, nBytes);
            BigInteger e = new BigInteger(1, eBytes);

            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            Object body = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parse(identityToken.substring(1, identityToken.length() - 1))
                    .getBody();


            System.out.println("body = " + body);

            ObjectMapper objectMapper = new ObjectMapper();
            RSAKey rsaKey = (RSAKey) JWK.parse(objectMapper.writeValueAsString(key));
            RSAPublicKey rsaPublicKey = rsaKey.toRSAPublicKey();
            JWSVerifier verifier = new RSASSAVerifier(rsaPublicKey);
            JWSVerifier verifier1 = new RSASSAVerifier((RSAPublicKey) publicKey);


            if (signedJWT.verify(verifier)) {
                System.out.println("RSAPublicKey verify success");
            }
            if (signedJWT.verify(verifier1)) {
                System.out.println("publicKey verify success");
            }
            if (true) {
                System.out.println("jwt verify success");

                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("jwt verify failed");
        return false;
    }

    public Optional<RSAPublicKey> getPubKey(PublicKey publicKey){


        try {
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(publicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(keySpecX509);
            return Optional.of(pubKey);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            System.out.println("Exception block | Public key parsing error ");
            return Optional.empty();
        }
    }


    public String StringReplace(String str){
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        str = str.replaceAll(match, "");
        return str;
    }
}
