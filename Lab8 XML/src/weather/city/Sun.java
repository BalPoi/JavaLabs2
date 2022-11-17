package weather.city;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Sun {
    Date riseTime, setTime;

    public Sun() {
    }

    public Sun(Date riseTime, Date setTime) {
        this.riseTime = riseTime;
        this.setTime = setTime;
    }

    public Sun(String riseTime, String  setTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        try {
            this.riseTime = formatter.parse(riseTime);
            this.setTime = formatter.parse(setTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Sun{" +
                "riseTime=" + riseTime +
                ", setTime=" + setTime +
                '}';
    }
}
