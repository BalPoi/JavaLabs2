package weather;

public class Gusts {
    float value;

    public Gusts() {
    }

    public Gusts(float value) {
        this.value = value;
    }

    public Gusts(String value) {
        try {
            this.value = Float.parseFloat(value);
        } catch (Exception e) {
            this.value = 0;
        }
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Gusts{" +
                "value=" + value +
                '}';
    }
}
