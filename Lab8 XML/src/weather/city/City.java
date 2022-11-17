package weather.city;

public class City {
    private long id;
    private String name;
    private Coorditates cords;
    private String country;
    private TimeZone timeZone;
    private Sun sun;

    public City() {
    }

    public City(long id, String name, Coorditates cords, String country, TimeZone timeZone, Sun sun) {
        this.id = id;
        this.name = name;
        this.cords = cords;
        this.country = country;
        this.timeZone = timeZone;
        this.sun = sun;
    }

    public City(String id, String name, Coorditates cords, String country, TimeZone timeZone, Sun sun) {
        this.id = Long.parseLong(id);
        this.name = name;
        this.cords = cords;
        this.country = country;
        this.timeZone = timeZone;
        this.sun = sun;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coorditates getCords() {
        return cords;
    }

    public void setCords(Coorditates cords) {
        this.cords = cords;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun = sun;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cords=" + cords +
                ", country='" + country + '\'' +
                ", timeZone=" + timeZone +
                ", sun=" + sun +
                '}';
    }
}
