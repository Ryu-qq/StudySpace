package hello.advanced.app.v3;


import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

    private final OrderServiceV3 orederService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId){
        TraceStatus status = null;

        try {
            status = trace.begin("OrderController.request()");
            orederService.orderItem(itemId);
            trace.end(status);

            return "ok";
        }catch (Exception e){
            trace.exception(status, e);
            throw e; // 예외를 던지지 않으면 바로 위에서 예외를 먹어버리고, 정상 흐름으로 동작함
        }


    }

}
