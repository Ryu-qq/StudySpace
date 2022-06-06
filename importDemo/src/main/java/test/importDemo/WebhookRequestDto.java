package test.importDemo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebhookRequestDto {

    private String imp_uid; //결제번호
    private String merchant_uid; //주문번호
    private String status; //결제 결과


}
