package com.qa.api.gorest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {
    /**
     *This method is used to convert POJO Object to a JSON String...
     * @param obj
     * @return
     */

    public static String getSerialisedJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString=null;
        try {
            jsonString= mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
       return jsonString;
    }
}
