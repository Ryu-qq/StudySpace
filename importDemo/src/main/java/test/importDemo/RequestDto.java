package test.importDemo;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestDto {
    private String imp_uid;
    private String merchant_uid;
}
