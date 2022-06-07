package test.importDemo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class WebClientUtil {

    @Value("${WebClient.imp_key}")
    private String imp_key;

    @Value("${WebClient.imp_secret}")
    private String imp_secret;



    @Bean
    public Mono<ResponseDto> getAccessToken(){
        return WebClient.create().post()
                .uri("https://api.iamport.kr/users/getToken")
                .body(BodyInserters.fromFormData("imp_key",imp_key).with("imp_secret", imp_secret))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ResponseDto.class);

    }


}
