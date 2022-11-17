import org.w3c.dom.*;
import org.xml.sax.SAXException;
import weather.*;
import weather.air.Humidity;
import weather.air.Pressure;
import weather.air.Temperature;
import weather.city.City;
import weather.city.Coorditates;
import weather.city.Sun;
import weather.city.TimeZone;
import weather.wind.Direction;
import weather.wind.Gusts;
import weather.wind.Speed;
import weather.wind.Wind;

import javax.xml.parsers.*;
import java.io.*;
import java.net.URL;
import java.text.ParseException;

public class RunnerDOM {
    private static Node getNode(Element root, String  nodeName) {
        return root.getElementsByTagName(nodeName).item(0);
    }

    private static String getAttribute(Node node, String attrName) {
        try {
            String result = node.getAttributes().getNamedItem(attrName).getNodeValue();
            return result;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new URL(Constants.URL).openStream());
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();

            CurrentWeather currentWeather = new CurrentWeather();
            currentWeather.setCity(new City(
                    getAttribute(getNode(root, "city"), "id"),
                    getAttribute(getNode(root, "city"), "name"),
                    new Coorditates(
                            getAttribute(getNode(root, "coord"), "lon"),
                            getAttribute(getNode(root, "coord"), "lat")),
                    getNode(root, "country").getTextContent(),
                    new TimeZone(getNode(root, "timezone").getTextContent()),
                    new Sun(getAttribute(getNode(root, "sun"), "rise"),
                            getAttribute(getNode(root, "sun"), "set"))
            ));
            currentWeather.setTemperature(new Temperature(
                    getAttribute(getNode(root, "temperature"), "value"),
                    getAttribute(getNode(root, "temperature"), "min"),
                    getAttribute(getNode(root, "temperature"), "max"),
                    getAttribute(getNode(root, "temperature"), "unit")
            ));
            currentWeather.setFeelsLikeTemp(new Temperature(
                    getAttribute(getNode(root, "feels_like"), "value"),
                    getAttribute(getNode(root, "feels_like"), "unit")
            ));
            currentWeather.setHumidity(new Humidity(
                    getAttribute(getNode(root, "humidity"), "value"),
                    getAttribute(getNode(root, "humidity"), "unit")
            ));
            currentWeather.setPressure(new Pressure(
                    getAttribute(getNode(root, "pressure"), "value"),
                    getAttribute(getNode(root, "pressure"), "unit")
            ));
            currentWeather.setWind(new Wind(
                    new Speed(
                            getAttribute(getNode(root, "speed"), "value"),
                            getAttribute(getNode(root, "speed"), "unit"),
                            getAttribute(getNode(root, "speed"), "name")
                    ),
                    new Gusts(
                            getAttribute(getNode(root, "gusts"), "value"),
                            getAttribute(getNode(root, "gusts"), "unit")
                    ),
                    new Direction(
                            getAttribute(getNode(root, "direction"), "value"),
                            getAttribute(getNode(root, "direction"), "code"),
                            getAttribute(getNode(root, "direction"), "name")
                    )
            ));
            currentWeather.setClouds(new Clouds(
                        getAttribute(getNode(root, "clouds"), "value"),
                        getAttribute(getNode(root, "clouds"), "name")
                    )
            );
            currentWeather.setVisibility(new Visibility(
                    getAttribute(getNode(root, "visibility"), "value")
            ));
            currentWeather.setPrecipitation(new Precipitation(
                    getAttribute(getNode(root, "precipitation"), "value"),
                    getAttribute(getNode(root, "precipitation"), "mode"),
                    getAttribute(getNode(root, "precipitation"), "unit")
            ));
            currentWeather.setWeather(new Weather(
                    getAttribute(getNode(root, "weather"), "number"),
                    getAttribute(getNode(root, "weather"), "value"),
                    getAttribute(getNode(root, "weather"), "icon")
            ));
            currentWeather.setLastUpdate(
                    getAttribute(getNode(root, "lastupdate"), "value")
            );

            System.out.println(currentWeather);

        } catch (ParserConfigurationException | IOException | SAXException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}