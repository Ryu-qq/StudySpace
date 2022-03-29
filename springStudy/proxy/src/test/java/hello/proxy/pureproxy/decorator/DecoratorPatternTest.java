package hello.proxy.pureproxy.decorator;


import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

    @Test
    void noDecorated(){
        RealComponent realComponent = new RealComponent();
        DecoratorpatternClient client = new DecoratorpatternClient(realComponent);

        client.execute();
    }

    @Test
    void decorator1(){
        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        DecoratorpatternClient client = new DecoratorpatternClient(messageDecorator);
        client.execute();
    }

    @Test
    void decorator2(){
        Component component = new RealComponent();
        Component messageDecorator = new MessageDecorator(component);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorpatternClient client = new DecoratorpatternClient(timeDecorator);
        client.execute();
    }
}
