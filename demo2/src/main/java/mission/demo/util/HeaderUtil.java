package mission.demo.util;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String PREFIX_MEMBER = "Member";



    public static String getAccessToken(HttpServletRequest request) {
        String headerValue = request.getHeader(HEADER_AUTHORIZATION);

        if (headerValue == null) {
            return null;
        }

        if (headerValue.startsWith(PREFIX_MEMBER)) {
            return headerValue.split(" ")[1];
        }

        return null;
    }
}
