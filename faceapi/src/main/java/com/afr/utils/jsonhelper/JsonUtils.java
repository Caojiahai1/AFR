package com.afr.utils.jsonhelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xyyz150 on 2016/1/6.
 */
public class JsonUtils {
    private static ObjectMapper mapper;

    static {
        if(mapper==null) {
            mapper = ObjectMapperUtils.InitObjectMapper();
        }
    }

    public static String writeValueAsString(Object o) throws JsonProcessingException {
            return mapper.writeValueAsString(o);
    }

    public static <T> T readObject(String json, Class<T> clazz) throws IOException {
            return mapper.readValue(json, clazz);
    }

    public static <T> T readObject(InputStream inputStream, Class<T> clazz) throws IOException {
            return mapper.readValue(inputStream, clazz);
    }

    //判断对象是否值相同
    public static <T> Boolean equals(Object src, Object desc) {
        try
        {
            return writeValueAsString(src).equals(writeValueAsString(desc));
        }
        catch (Exception ex)
        {return false;}
    }
}
