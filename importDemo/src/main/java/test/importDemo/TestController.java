package test.importDemo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pay")
public class TestController {

    private final TestService testService;

    @GetMapping
    public String payment(){
        return "index";
    }

    @PostMapping("/complete")
    public ResponseEntity<HashMap<String, String>> payComplete(@RequestBody RequestDto requestDto) throws JsonProcessingException {
        String imp_uid = requestDto.getImp_uid();
        String merchant_uid = requestDto.getMerchant_uid();

        HashMap<String, String> map = new HashMap<>();

        map.put(imp_uid, requestDto.getImp_uid());
        map.put(merchant_uid, requestDto.getMerchant_uid());

        ResponseDto responseDto = testService.getAccessKey();

        String s = testService.checkValidation(imp_uid, responseDto.getResponse().getAccess_token());
        System.out.println("s = " + s);
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Object> stringObjectMap = objectMapper.readValue(s, new TypeReference<Map<String, Object>>() {
        });

        Map<String, Object>  response = (Map<String, Object>) stringObjectMap.get("response");
        System.out.println("response = " + response);

        Object amount = response.get("amount");
        System.out.println("amount = " + amount);


        return ResponseEntity.ok(map);
    }


    @PostMapping("/iamport-webhook")
    public ResponseEntity<WebhookRequestDto> webhookTest(@RequestBody WebhookRequestDto requestDto){

        System.out.println("requestDto.getImp_uid() = " + requestDto.getImp_uid());
        System.out.println("requestDto.getMerchant_uid() = " + requestDto.getMerchant_uid());
        System.out.println(" requestDto.getStatus() = " + requestDto.getStatus());
        return ResponseEntity.ok(requestDto);

    }
}
