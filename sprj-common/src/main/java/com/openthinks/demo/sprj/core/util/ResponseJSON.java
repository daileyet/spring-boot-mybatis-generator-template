package com.openthinks.demo.sprj.core.util;

import com.openthinks.libs.utilities.exception.CheckerNoPassException;
import com.openthinks.libs.utilities.json.JSON;
import com.openthinks.libs.utilities.json.JSONObject;

/**
 * ClassName: ResponseJSON </br>
 * date: Aug 17, 2018 9:24:38 AM </br>
 * {@link JSONObject} wrapper help class
 * 
 * @author dailey.dai@openthinks.com
 */
public final class ResponseJSON {
  public static final String RST_FAIL = "fail";
  public static final String RST_SUCCESS = "success";
  private final JSONObject jsonObj;

  public ResponseJSON(JSONObject jsonObj) {
    super();
    this.jsonObj = jsonObj;
  }

  public ResponseJSON() {
    super();
    this.jsonObj = JSON.object();
    this.jsonObj.addProperty("result", RST_SUCCESS);
  }

  public JSONObject get() {
    return this.jsonObj;
  }

  public ResponseJSON error(String message) {
    jsonObj.addProperty("result", RST_FAIL);
    jsonObj.addProperty("message", message);
    return this;
  }

  public ResponseJSON error() {
    jsonObj.addProperty("result", RST_FAIL);
    return this;
  }

  public ResponseJSON success(String message) {
    jsonObj.addProperty("result", RST_SUCCESS);
    jsonObj.addProperty("message", message);
    return this;
  }

  public ResponseJSON success() {
    jsonObj.addProperty("result", RST_SUCCESS);
    return this;
  }

  public boolean isError() {
    return "fail".equalsIgnoreCase(jsonObj.getProperty("result", String.class));
  }

  public String getResult() {
    String rst = jsonObj.getProperty("result", String.class);
    return rst == null ? RST_SUCCESS : rst;
  }

  public String getMessage() {
    return jsonObj.getProperty("message", String.class);
  }

  public CheckerNoPassException createException(Throwable... es) {
    Throwable e = (es == null || es.length == 0) ? null : es[0];
    throw new CheckerNoPassException(getMessage(), e);
  }
}
