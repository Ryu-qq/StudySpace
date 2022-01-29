package hello.itemservice.messages;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage(){
        String result =messageSource.getMessage("hello",null,null);
        Assertions.assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = messageSource.getMessage("no code", null, "기본 메세지", null);
        Assertions.assertThat(result).isEqualTo("기본 메세지");
    }

    @Test
    void argumentMessage(){
        String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        Assertions.assertThat(message).isEqualTo("안녕 Spring");

    }

    @Test
    void defaultLang(){
        Assertions.assertThat(messageSource.getMessage("hello", null, null));
        Assertions.assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");


    }

    @Test
    void enLang(){
        Assertions.assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");

    }

}
