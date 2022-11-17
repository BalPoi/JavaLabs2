package weather.air;

public class Humidity {
    private int value;
    private String unit;

    public Humidity() {
    }

    public Humidity(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Humidity(String value, String unit) {
        this.value = Integer.parseInt(value);
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                '}';
    }
}
