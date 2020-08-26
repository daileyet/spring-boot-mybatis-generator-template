/**
 * 
 */
package com.openthinks.demo.sprj.web.util;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.openthinks.demo.sprj.core.AppConfig;
import com.openthinks.demo.sprj.core.util.SpringContextUtil;
import com.openthinks.libs.utilities.DateFormatUtil;

/**
 * @author dailey.dai@openthinks.com
 *
 */
public abstract class WebUtils {


  public static final String getBaseUrl(HttpServletRequest req) {
    StringBuffer buffer = new StringBuffer();
    buffer.append(req.getScheme()).append("://").append(req.getServerName()).append(":")
        .append(req.getServerPort());
    return buffer.toString();
  }

  public static final String getFullUrl(HttpServletRequest req, String relativePath) {
    return getBaseUrl(req) + "/" + StringUtils.removeStart(relativePath, "/");
  }

  /**
   * 获取静态资源的url路径
   * 
   * @param req
   * @param relativePath static resource relative path
   * @return
   */
  public static final String getStaticUrl(HttpServletRequest req, String relativePath) {
    final String STATIC_URL =
        SpringContextUtil.getContext().getBean(AppConfig.class).getStaticUrl();
    if (!AppConfig.DEFAULT_STATIC_URL.equalsIgnoreCase(STATIC_URL)) {// return directly for custom
                                                                     // setting of static url
      return StringUtils.removeEnd(STATIC_URL, "/") + "/"
          + StringUtils.removeStart(relativePath, "/");
    }
    StringBuffer buffer = new StringBuffer();
    buffer.append(req.getScheme()).append("://").append(req.getServerName()).append(":")
        .append(req.getServerPort()).append(StringUtils.removeEnd(req.getContextPath(), "/"))
        .append("/").append(StringUtils.removeStart(relativePath, "/"));
    return buffer.toString();
  }


  public static final String formatDateByAppDatePattern(AppConfig appConfig, Date date) {
    return DateFormatUtil.format(appConfig.getAppDatePattern(), date);
  }

  public static final String formatDateByAppDateShortPattern(AppConfig appConfig, Date date) {
    return DateFormatUtil.format(appConfig.getAppDateShortPattern(), date);
  }
}
