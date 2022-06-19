package com.example.applelogin;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/apple")
public class TestController {

    private final AppleJwtUtils appleJwtUtils;
    private final AppleService appleService;


    @PostMapping(value = "/appleLoginCallBack")
    public String snsApplelogin(@RequestBody MultiValueMap<String, Object> data) throws IOException {
        //전달 받은 data에서 token 값 저장
        //String id_token = data.get("id_token").toString();

        String code = data.get("code").toString();
        String client_secret = appleService.getAppleClientSecret(data.get("id_token").toString());

        log.debug("================================");
        log.debug("id_token ‣ " + data.get("id_token").toString());
        //log.debug("payload ‣ " + appleService.getPayload(data.get("id_token").toString()));
        log.debug("client_secret ‣ " + client_secret);
        log.debug("================================");

        //String clientSecret = appleService.createClientSecret();
        //appleJwtUtils.getClaimsBy(id_token);

        appleService.requestCodeValidations(client_secret, code, null);

        return "ok";

    }


}
