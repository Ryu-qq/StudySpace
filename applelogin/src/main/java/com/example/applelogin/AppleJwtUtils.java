package com.example.applelogin;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;

import com.nimbusds.jose.crypto.RSASSAVerifier;

import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;

import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class AppleJwtUtils  {

    private final AppleClient appleClient;
    private final AppConfig appConfig;

    public void getClaimsBy(String identityToken) {

        //애플에서 가져온 정보
        try {

            ApplePublicKeyResponse response = appleClient.getAppleAuthPublicKey();

            String headerOfIdentityToken = StringReplace(identityToken.substring(0, identityToken.indexOf(".")));

            Map<String, String> header = new ObjectMapper().readValue(new String(Base64.getDecoder().decode(headerOfIdentityToken), "UTF-8"), Map.class);
            //public key 구성요소를 조회한 뒤 JWT의 서명을 검증한 후 Claim을 응답
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

            Claims claims = Jwts.parser().setSigningKey(publicKey.getEncoded()).parseClaimsJws(identityToken).getBody();

            String audience = claims.getAudience();
            System.out.println("audience = " + audience);


            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);
            SignedJWT signedJWT = SignedJWT.parse(identityToken);

            if (signedJWT.verify(verifier)) {
                System.out.println("signedJWT 시벌 참이야? = " + signedJWT);
            }else{
                System.out.println("아아아 제바아아알!");
            }





        } catch (MalformedJwtException e) {
            //토큰 서명 검증 or 구조 문제 (Invalid token)
            System.out.println("MalformedJwtException = " + e);
        } catch (ExpiredJwtException e) {
            System.out.println("ExpiredJwtException = " + e);
            //토큰이 만료됐기 때문에 클라이언트는 토큰을 refresh 해야함.
        } catch (Exception ignored) {
            System.out.println("Exception = " + ignored);

        }

    }

    public ArrayList<PublicKey> getPublicKeyS(ApplePublicKeyResponse response) {

        ArrayList<PublicKey> list = new ArrayList<>();

        List<ApplePublicKeyResponse.Key> keys = response.getKeys();
        for(ApplePublicKeyResponse.Key key : keys){
            String nStr = key.getN().toString();
            String eStr = key.getE().toString();

            byte[] nBytes = Base64.getUrlDecoder().decode(nStr);
            byte[] eBytes = Base64.getUrlDecoder().decode(eStr);

            BigInteger n = new BigInteger(1, nBytes);
            BigInteger e = new BigInteger(1, eBytes);

            try {
                RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
                KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
                list.add(keyFactory.generatePublic(publicKeySpec));
            } catch (Exception exception) {
                throw new RuntimeException();
            }

        }

        return list;

    }





    //특수문자 제거용
    public String StringReplace(String str){
        String match = "[^\uAC00-\uD7A30-9a-zA-Z]";
        str = str.replaceAll(match, "");
        return str;
    }







}
