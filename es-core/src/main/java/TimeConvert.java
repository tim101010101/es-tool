import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConvert {
    public static String getTodayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    public static String getTodayDate(long timestamp) {
        return timestamp == 0
                ? ""
                : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp * 1000);
    }

    public static String getTime(long timeStamp) {
        if (timeStamp == 0) return null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(timeStamp * 1000);
        String[] split = date.split("\\s");

        return split.length > 1 ? split[1] : null;
    }

    public static String stampToString(long timestamp) {
        return timestamp == 0
                ? ""
                : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp * 1000);
    }

    public static Timestamp stringToStamp(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return time.isEmpty()
                ? null
//                : sdf.parse(time).getTime();
                : new Timestamp(sdf.parse(time).getTime());
    }
}
