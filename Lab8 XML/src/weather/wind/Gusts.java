package weather.wind;

public class Gusts {
    float value;
    String unit;

    public Gusts() {
    }

    public Gusts(float value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Gusts(String value, String unit) {
        try {
            this.value = Float.parseFloat(value);
        } catch (Exception e) {
            this.value = 0;
        }
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
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
        return "Gusts{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                '}';
    }
}
