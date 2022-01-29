package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV2Test {

    @Test
    void begin() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");
        trace.end(status1);
        trace.end(status2);
    }

    @Test
    void exception() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");
        trace.exception(status1, new IllegalStateException());
        trace.exception(status2, new IllegalStateException( ));
    }
}