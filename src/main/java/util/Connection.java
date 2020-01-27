package util;

import dao.DAOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {
    private static final Logger LOGGER = LoggerFactory.getLogger(Connection.class);

    public static String getData (String request) {
        String data = null;
        URL url = null;
        try {
            url = new URL(request);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        try(InputStream inputStream = url.openStream()) {
            data = new String(inputStream.readAllBytes());
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return data;
    }
}
