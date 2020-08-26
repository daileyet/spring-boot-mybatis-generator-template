package com.openthinks.demo.sprj.core.util;

import java.util.Date;
import com.openthinks.demo.sprj.core.AppConfig;
import com.openthinks.libs.utilities.DateFormatUtil;
import com.openthinks.libs.utilities.DateUtils;

/**
 * ClassName: WebDateUtils </br>
 * 
 * @author dailey.dai@openthinks.com
 */
public final class WebDateUtils extends DateUtils {

  public static final String formatLong(Date date) {
    return DateFormatUtil.format(getAppDateFormatPattern(), date);
  }
  
  public static final Date parse(String pattern, String dateStr) {
    try {
      return DateFormatUtil.parse(pattern, dateStr, DateUtils.getTimeZone());
    } catch (Exception e) {
      return null;
    }
  }

  public static final Date parse(String dateStr) {
    return parse(getAppDateFormatPattern(), dateStr);
  }

  public static final String getAppDateFormatPattern() {
    return SpringContextUtil.getContext().getBean(AppConfig.class).getJSONDateFormatPattern();
  }

  public static String getPastTimeFromNow(Date pastDate) {
		long nowTime = System.currentTimeMillis(); // 获取当前时间的毫秒数
		long reset = pastDate.getTime(); // 获取指定时间的毫秒数
		long dateDiff = nowTime - reset;
		String msg="";
		if (dateDiff < 0) {
			msg = "N/A";
		} else {
			long dateTemp1 = dateDiff / 1000; // 秒
			long dateTemp2 = dateTemp1 / 60; // 分钟
			long dateTemp3 = dateTemp2 / 60; // 小时
			long dateTemp4 = dateTemp3 / 24; // 天数
			long dateTemp5 = dateTemp4 / 30; // 月数
			long dateTemp6 = dateTemp5 / 12; // 年数
			if (dateTemp6 > 0) {
				msg = dateTemp6 + "年前";
			} else if (dateTemp5 > 0) {
				msg = dateTemp5 + "个月前";
			} else if (dateTemp4 > 0) {
				msg = dateTemp4 + "天前";
			} else if (dateTemp3 > 0) {
				msg = dateTemp3 + "小时前";
			} else if (dateTemp2 > 0) {
				msg = dateTemp2 + "分钟前";
			} else if (dateTemp1 > 10) {
				msg = dateTemp1 + "秒前";
			} else if (dateTemp1 >= 0) {
				msg = "刚刚";
			}
		}
		return msg;
	}
  
  public static String getPastTimeFromNowEng(Date pastDate) {
		long nowTime = System.currentTimeMillis(); // 获取当前时间的毫秒数
		long reset = pastDate.getTime(); // 获取指定时间的毫秒数
		long dateDiff = nowTime - reset;
		String msg="";
		if (dateDiff < 0) {
			msg = "N/A";
		} else {
			long dateTemp1 = dateDiff / 1000; // 秒
			long dateTemp2 = dateTemp1 / 60; // 分钟
			long dateTemp3 = dateTemp2 / 60; // 小时
			long dateTemp4 = dateTemp3 / 24; // 天数
			long dateTemp5 = dateTemp4 / 30; // 月数
			long dateTemp6 = dateTemp5 / 12; // 年数
			if (dateTemp6 > 0) {
				msg = dateTemp6 + "year ago";
			} else if (dateTemp5 > 0) {
				msg = dateTemp5 + "month ago";
			} else if (dateTemp4 > 0) {
				msg = dateTemp4 + "day ago";
			} else if (dateTemp3 > 0) {
				msg = dateTemp3 + "hour ago";
			} else if (dateTemp2 > 0) {
				msg = dateTemp2 + "minute ago";
			} else if (dateTemp1 > 10) {
				msg = dateTemp1 + "second ago";
			} else if (dateTemp1 >= 0) {
				msg = "now";
			}
		}
		return msg;
	}
}
