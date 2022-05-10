package mission.demo.util;

import javax.servlet.http.HttpServletRequest;

public class HeaderUtil {
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String PREFIX_LESSOR = "LESSOR";
    private final static String PREFIX_Realtor = "Realtor";
    private final static String PREFIX_Lessee = "Lessee";


    public static String getAccessToken(HttpServletRequest request) {
        String headerValue = request.getHeader(HEADER_AUTHORIZATION);

        if (headerValue == null) {
            return null;
        }

        if (headerValue.startsWith(PREFIX_LESSOR) || headerValue.startsWith(PREFIX_Realtor) || headerValue.startsWith(PREFIX_Lessee)) {
            return headerValue.split(" ")[1];
        }

        return null;
    }
}
