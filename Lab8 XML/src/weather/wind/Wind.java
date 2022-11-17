package weather.wind;

public class Wind {
    Speed speed;
    Gusts gusts;
    Direction direction;

    public Wind() {
    }

    public Wind(Speed speed, Gusts gusts, Direction direction) {
        this.speed = speed;
        this.gusts = gusts;
        this.direction = direction;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public Gusts getGusts() {
        return gusts;
    }

    public void setGusts(Gusts gusts) {
        this.gusts = gusts;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", gusts=" + gusts +
                ", direction=" + direction +
                '}';
    }
}
