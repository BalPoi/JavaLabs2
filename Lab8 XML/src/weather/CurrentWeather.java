package weather;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CurrentWeather {
    City city;
    Temperature temperature;
    Temperature feelsLikeTemp;
    Humidity humidity;
    Pressure pressure;
    Wind wind;
    Clouds clouds;
    Visibility visibility;
    Precipitation precipitation;
    Weather weather;
    Date lastUpdate;

    public CurrentWeather() {
    }

    public CurrentWeather(City city, Temperature temperature, Temperature feelsLikeTemp, Humidity humidity,
                          Pressure pressure, Wind wind, Clouds clouds, Visibility visibility,
                          Precipitation precipitation, Weather weather, Time lastUpdate) {
        this.city = city;
        this.temperature = temperature;
        this.feelsLikeTemp = feelsLikeTemp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
        this.clouds = clouds;
        this.visibility = visibility;
        this.precipitation = precipitation;
        this.weather = weather;
        this.lastUpdate = lastUpdate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Temperature getFeelsLikeTemp() {
        return feelsLikeTemp;
    }

    public void setFeelsLikeTemp(Temperature feelsLikeTemp) {
        this.feelsLikeTemp = feelsLikeTemp;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public void setPressure(Pressure pressure) {
        this.pressure = pressure;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        this.lastUpdate = formatter.parse(lastUpdate);
    }

    @Override
    public String toString() {
        return "CurrentWeather{\n" +
                "\tcity=" + city +
                ",\n\ttemperature=" + temperature +
                ",\n\tfeelsLikeTemp=" + feelsLikeTemp +
                ",\n\thumidity=" + humidity +
                ",\n\tpressure=" + pressure +
                ",\n\twind=" + wind +
                ",\n\tclouds=" + clouds +
                ",\n\tvisibility=" + visibility +
                ",\n\tprecipitation=" + precipitation +
                ",\n\tweather=" + weather +
                ",\n\tlastUpdate=" + lastUpdate +
                "\n}";
    }
}
