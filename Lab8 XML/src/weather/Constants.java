package weather;

public class Constants {
    public static final String APP_ID = "8bf776b009b377ed3825a356c5b4d8dd";
    public static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&mode=xml&APPID=%s".formatted(APP_ID);
}
