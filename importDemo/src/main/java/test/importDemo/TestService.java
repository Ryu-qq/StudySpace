package test.importDemo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TestService {

    private final WebClientUtil webClientUtil;

    public ResponseDto getAccessKey(){
        Mono<ResponseDto> accessToken = webClientUtil.getAccessToken();
        return accessToken.block();
    }

    public String checkValidation(String imp_uid, String accessToken){


        Mono<String> stringMono = WebClient.create().get()
                .uri("https://api.iamport.kr/payments/" + imp_uid)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class);

        return stringMono.block();
    }
}
