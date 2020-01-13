package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Connection {
    public static String getData (String request) throws IOException {
        URL url = new URL(request);
        InputStream inputStream = url.openStream();
        String data = new String(inputStream.readAllBytes());
        inputStream.close();
        return data;
    }
}
