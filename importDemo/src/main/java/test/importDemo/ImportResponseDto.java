package test.importDemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportResponseDto {

    private String code;
    private String message;
    private ResponseDetail responseDetail;

}
