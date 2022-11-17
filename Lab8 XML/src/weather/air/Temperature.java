package weather.air;

public class Temperature {
    private float value, min, max;
    private String unit;

    public Temperature() {
    }

    public Temperature(float value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public Temperature(String value, String unit) {
        this.value = Float.parseFloat(value);
        this.unit = unit;
    }

    public Temperature(float value, float min, float max, String unit) {
        this.value = value;
        this.min = min;
        this.max = max;
        this.unit = unit;
    }

    public Temperature(String value, String min, String max, String unit) {
        this.value = Float.parseFloat(value);
        this.min = Float.parseFloat(min);
        this.max = Float.parseFloat(max);
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "value=" + value +
                ", min=" + min +
                ", max=" + max +
                ", unit='" + unit + '\'' +
                '}';
    }
}
