package weather;

import java.text.ParseException;

public class Precipitation {
    private float value;        // weather.Precipitation, mm
    private String mode;        // Possible values are "no", name of weather phenomena as 'rain', 'snow'
    private String unit;

    public Precipitation() {
    }

    public Precipitation(float value, String mode, String unit) {
        this.value = value;
        this.mode = mode;
        this.unit = unit;
    }

    public Precipitation(String value, String mode, String unit) {
        try {
            this.value = Float.parseFloat(value);
        } catch (Exception e) {
            this.value = 0;
        }
        this.mode = mode;
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setValue(String value) {
        try {
            this.value = Float.parseFloat(value);
        } catch(Exception e) {
            this.value = 0;
        }
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Precipitation{" +
                "value=" + value +
                ", mode='" + mode + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
