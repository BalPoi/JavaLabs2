package weather;

public class Weather {
    private int number;     // weather.Weather condition id
    private String value;   // weather.Weather condition name
    private String icon;    // weather.Weather icon id

    public Weather() {
    }

    public Weather(int number, String value, String icon) {
        this.number = number;
        this.value = value;
        this.icon = icon;
    }

    public Weather(String number, String value, String icon) {
        this.number = Integer.parseInt(number);
        this.value = value;
        this.icon = icon;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "number=" + number +
                ", value='" + value + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
