package com.openthinks.demo.sprj.core.util;

import java.lang.reflect.Field;
import java.util.List;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONConverters;
import com.openthinks.libs.utilities.json.JSONObject;
import com.openthinks.libs.utilities.json.JSONObjectConverter;
import com.openthinks.libs.utilities.logger.ProcessLogger;

/**
 * ClassName: JSONConvertersUtil </br>
 * 
 * @author dailey.dai@openthinks.com
 */
public final class JSONConvertersUtil {

  static {
    JSONConverters.reload("/converterConfig.properties");
    JSONConverters.ativeDefaultConveterAsUnderline();
  }

  public static final JSONObject perparedAndGet(Object obj) {
    return JSONConverters.perparedAndGet(obj);
  }

  public static class PartialFieldsJSONObjectConverter implements JSONObjectConverter {
    final List<String> fieldNames;

    public PartialFieldsJSONObjectConverter(List<String> fieldNames) {
      this.fieldNames = fieldNames;
    }

    @Override
    public JSONObject convert(Object bizModel) {
      Class<?> bizModelClazz = bizModel.getClass();
      JSONObject jsonObj = JSON.object();
      for (String fieldName : fieldNames) {
        try {
          Field field = bizModelClazz.getDeclaredField(fieldName);
          field.setAccessible(true);
          Object propertyVal = field.get(bizModel);
          jsonObj.addProperty(fieldName, propertyVal);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException
            | SecurityException e) {
          ProcessLogger.warn("Faile to load property {0} value for {1}:", fieldName, e);
        }
      }
      return jsonObj;
    }

  }



}
