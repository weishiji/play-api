package utils;

import java.util.Date;

/**
 * Created by lxg on 20/04/2017.
 */
public class FormatSqlTime {
    private String currentTime;

    public String format(Date d){
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dt.setTime(d.getTime());
        currentTime = sdf.format(dt);
        return currentTime;
    }
    public String format(Date d,String format){
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
        dt.setTime(d.getTime());
        currentTime = sdf.format(dt);
        return currentTime;
    }
}
