package test.importDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDetail {

    private String amount;
    private String imp_uid;
    private String merchant_uid;


}
