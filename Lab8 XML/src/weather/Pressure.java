package weather;

public class Pressure {
    private int value;
    private String unit;

    public Pressure() {
    }

    public Pressure(int value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Pressure(String value, String unit) {
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
        return "Pressure{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                '}';
    }
}
