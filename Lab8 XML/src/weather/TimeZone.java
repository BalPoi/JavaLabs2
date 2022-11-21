package weather;

public class TimeZone {
    int shiftFromUTC;       // Shift in seconds from UTC

    public TimeZone() {
    }

    public TimeZone(int shiftFromUTC) {
        this.shiftFromUTC = shiftFromUTC;
    }

    public TimeZone(String shiftFromUTC) {
        this.shiftFromUTC = Integer.parseInt(shiftFromUTC);
    }

    public int getShiftFromUTC() {
        return shiftFromUTC;
    }

    public void setShiftFromUTC(int shiftFromUTC) {
        this.shiftFromUTC = shiftFromUTC;
    }

    @Override
    public String toString() {
        return "TimeZone{" +
                "shiftFromUTC=" + shiftFromUTC +
                '}';
    }
}
