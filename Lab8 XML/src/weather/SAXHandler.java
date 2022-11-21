package weather;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;

public class SAXHandler extends DefaultHandler {
    private static final String CITY = "city";
    private static final String COORD = "coord";
    private static final String COUNTRY = "country";
    private static final String TIMEZONE = "timezone";
    private static final String SUN = "sun";
    private static final String TEMPERATURE = "temperature";
    private static final String FEELS_LIKE = "feels_like";
    private static final String HUMIDITY = "humidity";
    private static final String PRESSURE = "pressure";
    private static final String SPEED = "speed";
    private static final String GUSTS = "gusts";
    private static final String DIRECTION = "direction";
    private static final String CLOUDS = "clouds";
    private static final String VISIBILITY = "visibility";
    private static final String PRECIPITATION = "precipitation";
    private static final String WEATHER = "weather";
    private static final String LAST_UPDATE = "lastupdate";


    private CurrentWeather weather;
    private StringBuilder elementValue;

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void startDocument(){
        weather = new CurrentWeather();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case CITY:
                weather.city = new City();
                weather.city.setId(attributes.getValue("id"));
                weather.city.setName(attributes.getValue("name"));
                break;
            case COORD:
                weather.city.setCords(new Coorditates(
                        attributes.getValue("lon"),
                        attributes.getValue("lat")
                ));
                break;
            case COUNTRY:
                elementValue = new StringBuilder();
                break;
            case TIMEZONE:
                elementValue = new StringBuilder();
                break;
            case SUN:
                weather.city.sun = new Sun(
                        attributes.getValue("rise"),
                        attributes.getValue("set")
                );
                break;
            case TEMPERATURE:
                weather.setTemperature(new Temperature(
                        attributes.getValue("value"),
                        attributes.getValue("min"),
                        attributes.getValue("max"),
                        attributes.getValue("unit")
                ));
                break;
            case FEELS_LIKE:
                weather.setFeelsLikeTemp(new Temperature(
                        attributes.getValue("value"),
                        attributes.getValue("unit")
                ));
                break;
            case HUMIDITY:
                weather.setHumidity(new Humidity(
                        attributes.getValue("value"),
                        attributes.getValue("unit")
                ));
                break;
            case PRESSURE:
                weather.setPressure(new Pressure(
                        attributes.getValue("value"),
                        attributes.getValue("unit")
                ));
                break;
            case SPEED:
                weather.wind = new Wind();
                weather.wind.setSpeed(new Speed(
                        attributes.getValue("value"),
                        attributes.getValue("unit"),
                        attributes.getValue("name")
                ));
                break;
            case GUSTS:
                weather.wind.setGusts(new Gusts(
                        attributes.getValue("value")
                ));
                break;
            case DIRECTION:
                weather.wind.setDirection(new Direction(
                        attributes.getValue("value"),
                        attributes.getValue("code"),
                        attributes.getValue("name")
                ));
                break;
            case CLOUDS:
                weather.setClouds(new Clouds(
                        attributes.getValue("value"),
                        attributes.getValue("name")
                ));
                break;
            case VISIBILITY:
                weather.setVisibility(new Visibility(
                        attributes.getValue("value")
                ));
                break;
            case PRECIPITATION:
                weather.precipitation = new Precipitation();
                String mode = attributes.getValue("mode");
                weather.precipitation.setMode(mode);
                weather.precipitation.setValue(attributes.getValue("value"));
                weather.precipitation.setUnit(attributes.getValue("unit"));
                break;
            case WEATHER:
                weather.setWeather(new Weather(
                        attributes.getValue("number"),
                        attributes.getValue("value"),
                        attributes.getValue("icon")
                ));
                break;
            case LAST_UPDATE:
                try {
                    weather.setLastUpdate(attributes.getValue("value"));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case COUNTRY -> weather.city.setCountry(elementValue.toString());
            case TIMEZONE -> weather.city.setTimeZone(new TimeZone(elementValue.toString()));
        }
    }

    public CurrentWeather getWeather() {
        return weather;
    }
}
