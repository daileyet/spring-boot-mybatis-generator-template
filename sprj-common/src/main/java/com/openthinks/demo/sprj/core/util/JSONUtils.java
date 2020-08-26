package com.openthinks.demo.sprj.core.util;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * ClassName: JSONUtils </br>
 * 
 * @author dailey.dai@openthinks.com
 */
public final class JSONUtils {
  private JSONUtils() {}

  private final static Gson GSON;
  public static final Type MAP_STR_OBJ_TYPE = new TypeToken<HashMap<String, Object>>() {}.getType();
  static {
    GSON = new Gson();
  }

  public static final String stringify(Object jsonObject) {
    return GSON.toJson(jsonObject);
  }

  public static final byte[] bytes(Object jsonObject) {
    try {
      return stringify(jsonObject).getBytes("UTF-8");
    } catch (UnsupportedEncodingException e) {
      return stringify(jsonObject).getBytes();
    }
  }

  public static final <T> T fromJSON(String jsonStr, Class<T> type) {
    return GSON.fromJson(jsonStr, type);
  }

  public static final <T> T fromJSON(String jsonStr, Type type) {
    return GSON.fromJson(jsonStr, type);
  }

  public static final <T> T fromJSON(Reader reader, Class<T> type) {
    return GSON.fromJson(reader, type);
  }


  public static final Map<String, Object> fromJSON(String jsonStr) {
    return fromJSON(jsonStr, MAP_STR_OBJ_TYPE);
  }
}
