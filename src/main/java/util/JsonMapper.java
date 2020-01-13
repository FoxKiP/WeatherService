package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonMapper {
    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getNode(String jsonObj) throws IOException {
        return mapper.readTree(jsonObj);
    }

    public static String getJson(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }
}
