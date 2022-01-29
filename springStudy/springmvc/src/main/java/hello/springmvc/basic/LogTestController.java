package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//문자열 반환하게 해주는거
//Controller는 뷰를 찾고 랜더링함
@Slf4j
@RestController
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    private String logTest(){
        String name = "Spring";

        log.trace("trace log={}", name);
        log.debug("trace debug={}", name);
        log.info("trace info={}", name);
        log.warn("trace warn={}", name);
        log.error("info error={}", name);

        return "ok";

    }
}
