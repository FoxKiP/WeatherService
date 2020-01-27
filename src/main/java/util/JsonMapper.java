package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonMapper.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static JsonNode getNode(String jsonObj){
        JsonNode node = null;
        try {
            node = mapper.readTree(jsonObj);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return node;
    }

    public static String getJson(Object obj){
        String json = null;
        try {
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }
        return json;
    }
}
