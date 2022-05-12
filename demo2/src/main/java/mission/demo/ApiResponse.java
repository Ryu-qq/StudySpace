package mission.demo;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    private final Map<String, T> body;

    public static <T> ApiResponse <T> success(String type, T body)  {
        Map<String, T> map = new HashMap<>();
        map.put(type, body);
        return new ApiResponse(map);
    }

    public static <T> ApiResponse<T> fail() {
        return new ApiResponse(null);
    }

}
