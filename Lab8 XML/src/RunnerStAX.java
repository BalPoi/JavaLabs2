import weather.Constants;
import weather.StAXHandler;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.net.URL;

public class RunnerStAX {
    public static void main(String[] args) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = null;

        try {
            eventReader = factory.createXMLEventReader(new URL(Constants.URL).openStream());
        } catch (XMLStreamException | IOException e) {
            throw new RuntimeException(e);
        }

        StAXHandler handler = new StAXHandler(eventReader);
        handler.parse();

        System.out.println(handler.getResult());
    }
}
