package test.importDemo;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/pay")
public class TestController {

    @GetMapping
    public String payment(){
        return "index";
    }

    @PostMapping("/complete")
    public ResponseEntity<HashMap<String, String>> payComplete(@RequestBody RequestDto requestDto){
        String imp_uid = requestDto.getImp_uid();
        String merchant_uid = requestDto.getMerchant_uid();

        HashMap<String, String> map = new HashMap<>();

        map.put(imp_uid, requestDto.getImp_uid());
        map.put(merchant_uid, requestDto.getMerchant_uid());

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
