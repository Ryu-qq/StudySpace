package com.ryu.pt.springboot.web;

import com.ryu.pt.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; //웹 API테스트할 때 사용

    @WithMockUser(roles="USER")
    @Test
    public void hello_return() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) // hello 주소로 GET 요청
                .andExpect(status().isOk()) //결과 검증
                .andExpect(content().string(hello)); //헬로가 맞는지 검증
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto_return() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", is(name)))
        .andExpect(jsonPath("$.amount", is(amount)));
    }

}
