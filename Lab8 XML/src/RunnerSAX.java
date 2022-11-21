import weather.CurrentWeatherHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import weather.Constants;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URL;

public class RunnerSAX {
    public static void main(String[] args) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        CurrentWeatherHandler handler = new CurrentWeatherHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(new InputSource(new URL(Constants.URL).openStream()), handler);

            System.out.println(handler.getWeather());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

