package com.openthinks.demo.sprj.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
  private HttpUtils() {

  }

  public static final String requestAsStringJDK(String url, String method) throws Exception {
    return requestAsStringJDK(url, method, null);
  }

  public static final String requestAsStringJDK(String url, String method, String params)
      throws Exception {
    StringBuffer bufferRes = new StringBuffer();
    URL realUrl = new URL(url);
    HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

    conn.setConnectTimeout(25000);
    conn.setReadTimeout(25000);

    HttpURLConnection.setFollowRedirects(true);
    conn.setRequestMethod(method);
    conn.setDoOutput(true);
    conn.setDoInput(true);
    conn.setRequestProperty("User-Agent",
        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.93 Safari/537.36 OPR/32.0.1948.69");
    conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
    conn.connect();
    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
    // out.write(URLEncoder.encode(params,"UTF-8"));
    if (params != null) {
      out.write(params);
    }
    out.flush();
    out.close();

    InputStream in = conn.getInputStream();
    BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));
    String valueString = null;
    while ((valueString = read.readLine()) != null) {
      bufferRes.append(valueString);
    }

    in.close();
    if (conn != null) {
      conn.disconnect();
    }
    return bufferRes.toString();
  }

}
