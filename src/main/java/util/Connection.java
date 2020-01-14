package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {
    public static String getData (String request) throws MalformedURLException {
        URL url = new URL(request);
        String data = "";
        try(InputStream inputStream = url.openStream()) {
            data = new String(inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
