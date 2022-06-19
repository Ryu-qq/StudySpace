package com.example.applelogin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.nimbusds.jose.*;

import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jcajce.provider.asymmetric.RSA;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AppleService {

    private final AppConfig appConfig;
    private final AppleClient appleClient;
    private final AppleUtils appleUtils;




    public String getAppleClientSecret(String id_token) throws IOException {

        if (verifyIdentityToken(id_token)) {
            return createClientSecret();
        }

        return null;
    }

    public String createClientSecret() throws IOException {


        ClassPathResource resource = new ClassPathResource("static/AuthKey_PLS38L38PA.p8");
        String privateKey = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        Reader pemReader = new StringReader(privateKey);
        PEMParser pemParser = new PEMParser(pemReader);
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();



        Date now = new Date();


        return Jwts.builder()
                .setHeaderParam("kid", "PLS38L38PA")
                .setHeaderParam("alg", JWSAlgorithm.ES256.getName())
                .setIssuer(appConfig.getKeyTeamId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(now.getTime() + 3600000))
                .setAudience("https://appleid.apple.com")
                .setSubject(appConfig.getAud())
                .signWith(converter.getPrivateKey(object), SignatureAlgorithm.ES256)
                .compact();

    }

    public boolean verifyIdentityToken(String id_token) {

        String nonce = "20B20D-0S8-1K8";

        try {
              SignedJWT signedJWT = SignedJWT.parse(id_token);
//            ReadOnlyJWTClaimsSet payload = signedJWT.getJWTClaimsSet();
//
//            // EXP
//            Date currentTime = new Date(System.currentTimeMillis());
//            if (!currentTime.before(payload.getExpirationTime())) {
//                return false;
//            }
//
//            if(!nonce.equals(payload.getClaim("nonce"))){
//                System.out.println("nonce1 = " + nonce);
//                System.out.println("nonce2 = " + payload.getClaim("nonce"));
//                return false;
//            }
//
//            if(!appConfig.getIss().equals(payload.getIssuer())){
//                System.out.println("appConfig.getIss() = " + appConfig.getIss());
//                System.out.println("payload.getIssuer() = " + payload.getIssuer());
//                return false;
//
//            }
//
//            // NONCE(Test value), ISS, AUD
//            if (!appConfig.getAud().equals(payload.getAudience().get(0))) {
//                System.out.println("appConfig.getAud() = " + appConfig.getAud());
//                System.out.println("payload.getAudience().get(0) = " + payload.getAudience().get(0));
//                return false;
//            }

            //RSA
            if (appleUtils.verifyPublicKey(signedJWT, id_token)) {
                System.out.println("success verify signedJWT");
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

//    private boolean verifyPublicKey(SignedJWT signedJWT, String id_token) {
//
//        try {
//
//            ApplePublicKeyResponse response = appleClient.getAppleAuthPublicKey();
//            String headerOfIdentityToken = StringReplace(id_token.substring(0, id_token.indexOf("."))).trim();
//
//            Map<String, String> header = new ObjectMapper().readValue(new String(Base64.getDecoder().decode(headerOfIdentityToken), "UTF-8"), Map.class);
//            ApplePublicKeyResponse.Key key = response.getMatchedKeyBy(header.get("kid"), header.get("alg"))
//                    .orElseThrow(() -> new NullPointerException("Failed get public key from apple's id server."));
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            RSAKey rsaKey = (RSAKey) JWK.parse(objectMapper.writeValueAsString(key));
//            RSAPublicKey publicKey = rsaKey.toRSAPublicKey();
//            JWSVerifier verifier = new RSASSAVerifier(publicKey);
//
//            if (signedJWT.verify(verifier)) {
//                System.out.println("success verify signedJWT");
//
//                return true;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("success verify failed");
//
//        return false;
//    }



    public TokenResponse requestCodeValidations(String client_secret, String code, String refresh_token) {

        TokenResponse tokenResponse = new TokenResponse();

        if (client_secret != null && code != null && refresh_token == null) {
            tokenResponse = validateAuthorizationGrantCode(client_secret, code);
        } else if (client_secret != null && code == null && refresh_token != null) {
            tokenResponse = validateAnExistingRefreshToken(client_secret, refresh_token);
        }

        return tokenResponse;
    }


    public TokenResponse validateAuthorizationGrantCode(String client_secret, String code) {

        Map<String, String> tokenRequest = new HashMap<>();

        tokenRequest.put("client_id", appConfig.getAud());
        tokenRequest.put("client_secret", client_secret);
        tokenRequest.put("code", code.substring(1, code.length()-1));
        tokenRequest.put("grant_type", "authorization_code");

        return getTokenResponse(tokenRequest);
    }

    public TokenResponse validateAnExistingRefreshToken(String client_secret, String refresh_token) {

        Map<String, String> tokenRequest = new HashMap<>();

        tokenRequest.put("client_id", appConfig.getAud());
        tokenRequest.put("client_secret", client_secret);
        tokenRequest.put("grant_type", "refresh_token");
        tokenRequest.put("refresh_token", refresh_token);

        return getTokenResponse(tokenRequest);
    }

    private TokenResponse getTokenResponse(Map<String, String> tokenRequest) {

        try {
            String response =  HttpClientUtils.doPost(appConfig.getAuthTokenUrl(), tokenRequest);
            System.out.println("response = " + response);
            ObjectMapper objectMapper = new ObjectMapper();
            TokenResponse tokenResponse = objectMapper.readValue(response, TokenResponse.class);

            if (tokenRequest != null) {
                return tokenResponse;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }




    public PublicKey getPublicKey(JsonObject object) {
        String nStr = object.get("n").toString();
        String eStr = object.get("e").toString();

        byte[] nBytes = Base64.getUrlDecoder().decode(nStr.substring(1, nStr.length() - 1));
        byte[] eBytes = Base64.getUrlDecoder().decode(eStr.substring(1, eStr.length() - 1));

        BigInteger n = new BigInteger(1, nBytes);
        BigInteger e = new BigInteger(1, eBytes);

        try {


            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
            return publicKey;
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }



    public String StringReplace(String str){
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        str = str.replaceAll(match, " ");
        return str;
    }






}
