package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogTraceFilterHandler implements InvocationHandler {

    private final Object target;
    private final LogTrace logTrace;
    private final String[] patterns;



    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {


        //메서드 이름 필터
        String methodName = method.getName();

        //save, request, reque*, *est
        if(!PatternMatchUtils.simpleMatch(patterns, methodName)){
            return method.invoke(target, objects);
        }

        TraceStatus status = null;

        try{
            String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            status = logTrace.begin(message);

            //target 호출
            Object invoke = method.invoke(target, objects);
            logTrace.end(status);
            return invoke;
        }catch (Exception e){
            logTrace.exception(status, e);
            throw e;
        }
    }
}
