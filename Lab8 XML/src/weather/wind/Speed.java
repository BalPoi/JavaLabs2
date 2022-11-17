package weather.wind;

public class Speed {
    float value;
    String unit;
    String name;

    public Speed() {
    }

    public Speed(float value, String unit, String name) {
        this.value = value;
        this.unit = unit;
        this.name = name;
    }

    public Speed(String value, String unit, String name) {
        this.value = Float.parseFloat(value);
        this.unit = unit;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Speed{" +
                "value=" + value +
                ", unit='" + unit + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
