package weather;

public class Coorditates {
    String lon, lat;

    public Coorditates() {
    }

    public Coorditates(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coorditates{" +
                "lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                '}';
    }
}
