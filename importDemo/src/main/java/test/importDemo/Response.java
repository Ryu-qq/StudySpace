package test.importDemo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Response {
    private String access_token;
    private String now;
    private String expired_at;

}
