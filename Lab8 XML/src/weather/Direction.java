package weather;

public class Direction {
    int value;
    String code;
    String name;

    public Direction() {
    }

    public Direction(int value, String code, String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }

    public Direction(String value, String code, String name) {
        this.value = Integer.parseInt(value);
        this.code = code;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "value=" + value +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
