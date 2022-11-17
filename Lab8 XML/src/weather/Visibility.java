package weather;

public class Visibility {
    private int value;      // weather.Visibility, meter. The maximum value of the visibility is 10km

    public Visibility() {
    }

    public Visibility(int value) {
        this.value = value;
    }

    public Visibility(String value) {
        this.value = Integer.parseInt(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Visibility{" +
                "value=" + value +
                '}';
    }
}
