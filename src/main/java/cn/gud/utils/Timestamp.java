package cn.gud.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 张鲁燕
 * @since 2019/8/9 11:22
 */
public class Timestamp {
  public static String stampToDate(Integer timestamp) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date =  new Date(timestamp * 1000L);
    return simpleDateFormat.format(date);
  }
}
