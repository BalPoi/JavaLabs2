package weather;

public class Clouds {
    private int value;
    private String name;

    public Clouds() {
    }

    public Clouds(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public Clouds(String value, String name) {
        this.value = Integer.parseInt(value);
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clouds{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
