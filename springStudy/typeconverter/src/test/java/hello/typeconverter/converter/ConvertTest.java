package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConvertTest {

    @Test
    void stringToIpPort(){


        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }

    @Test
    void ipPortoString(){

        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);
        assertThat(result).isEqualTo("127.0.0.1:8080");

    }
}
