package utils;

import java.util.UUID;

public class AssembleerUtils {

    public static String genRandomToken() {
        String token = UUID.randomUUID().toString().replace("-", "");
        return token;

    }

}
