package weather;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class StAXHandler {
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

    CurrentWeather weather = new CurrentWeather();
    XMLEventReader reader;

    boolean bCountry, bTimeZone;

    public StAXHandler(XMLEventReader reader) {
        this.reader = reader;
    }

    public void parse() {
        bCountry = bTimeZone = false;
        while (reader.hasNext()) {
            XMLEvent event = null;
            try {
                event = reader.nextEvent();
            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }

            if (event.isStartElement()) {
                StartElement element = (StartElement) event;
                switch (element.getName().toString()) {
                    case CITY:
                        weather.setCity(new City());
                        weather.city.setId(element.getAttributeByName(QName.valueOf("id")).getValue());
                        weather.city.setName(element.getAttributeByName(QName.valueOf("name")).getValue());
                        break;
                    case COORD:
                        weather.city.setCords(new Coorditates(
                                element.getAttributeByName(QName.valueOf("lat")).getValue(),
                                element.getAttributeByName(QName.valueOf("lon")).getValue()
                        ));
                        break;
                    case COUNTRY:
                        bCountry = true;
                        break;
                    case TIMEZONE:
                        bTimeZone = true;
                        break;
                    case SUN:
                        weather.city.setSun(new Sun(
                                element.getAttributeByName(QName.valueOf("rise")).getValue(),
                                element.getAttributeByName(QName.valueOf("set")).getValue()
                        ));
                        break;
                    case TEMPERATURE:
                        weather.setTemperature(new Temperature(
                                element.getAttributeByName(QName.valueOf("value")).getValue(),
                                element.getAttributeByName(QName.valueOf("min")).getValue(),
                                element.getAttributeByName(QName.valueOf("max")).getValue(),
                                element.getAttributeByName(QName.valueOf("unit")).getValue()
                        ));
                        break;
                    case FEELS_LIKE:
                        weather.setFeelsLikeTemp(new Temperature(
                                element.getAttributeByName(QName.valueOf("value")).getValue(),
                                element.getAttributeByName(QName.valueOf("unit")).getValue()
                        ));
                        break;
                    case HUMIDITY:
                        weather.setHumidity(new Humidity(
                                element.getAttributeByName(QName.valueOf("value")).getValue(),
                                element.getAttributeByName(QName.valueOf("unit")).getValue()
                        ));
                        break;
                    case PRESSURE:
                        weather.setPressure(new Pressure(
                                element.getAttributeByName(QName.valueOf("value")).getValue(),
                                element.getAttributeByName(QName.valueOf("unit")).getValue()
                        ));
                        break;
                    case SPEED:
                        weather.wind = new Wind();
                        weather.wind.setSpeed(new Speed(
                                element.getAttributeByName(QName.valueOf("value")).getValue(),
                                element.getAttributeByName(QName.valueOf("unit")).getValue(),
                                element.getAttributeByName(QName.valueOf("name")).getValue()
                        ));
                        break;
                    case GUSTS:
                        try {
                            weather.wind.setGusts(new Gusts(
                                    element.getAttributeByName(QName.valueOf("value")).getValue()
                            ));
                        } catch (NullPointerException e) {
                            weather.wind.setGusts(new Gusts());
                        }
                        break;
                    case DIRECTION:
                        weather.wind.setDirection(new Direction(
                                element.getAttributeByName(QName.valueOf("value")).getValue(),
                                element.getAttributeByName(QName.valueOf("code")).getValue(),
                                element.getAttributeByName(QName.valueOf("name")).getValue()
                        ));
                        break;
                    case CLOUDS:
                        weather.setClouds(new Clouds(
                                element.getAttributeByName(QName.valueOf("value")).getValue(),
                                element.getAttributeByName(QName.valueOf("name")).getValue()
                        ));
                        break;
                    case VISIBILITY:
                        weather.setVisibility(new Visibility(
                                element.getAttributeByName(QName.valueOf("value")).getValue()
                        ));
                        break;
                    case PRECIPITATION:
                        weather.setPrecipitation(new Precipitation());
                        weather.precipitation.setMode(element.getAttributeByName(QName.valueOf("mode")).getValue());
                        if (Objects.equals(weather.precipitation.getMode(), "yes")) {
                            weather.precipitation.setValue(element.getAttributeByName(QName.valueOf("value")).getValue());
                            weather.precipitation.setUnit(element.getAttributeByName(QName.valueOf("unit")).getValue());
                        }
                        break;
                    case WEATHER:
                        weather.setWeather(new Weather(
                                element.getAttributeByName(QName.valueOf("number")).getValue(),
                                element.getAttributeByName(QName.valueOf("value")).getValue(),
                                element.getAttributeByName(QName.valueOf("icon")).getValue()
                        ));
                        break;
                    case LAST_UPDATE:
                        try {
                            weather.setLastUpdate(
                                    element.getAttributeByName(QName.valueOf("value")).getValue()
                            );
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                }
            } else if (event.isCharacters()) {
                Characters element = (Characters) event;
                if (bCountry) weather.city.setCountry(element.getData());
                if (bTimeZone) weather.city.setTimeZone(new TimeZone(element.getData()));
            }
        }
    }

    public CurrentWeather getResult() {
        return weather;
    }
}
