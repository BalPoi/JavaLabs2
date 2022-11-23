import by.gsu.pms.CurrentWeather;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final String URL = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&mode=json&APPID=8bf776b009b377ed3825a356c5b4d8dd";
        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                final URL templatePath = App.class.getResource("template.html");

                JsonReader reader = new JsonReader(new InputStreamReader(conn.getInputStream()));
                Gson gson = new Gson();
                CurrentWeather weather = gson.fromJson(reader, CurrentWeather.class);

                final String city = weather.getCityName();
                final String code = weather.getCode();
                final String icon = weather.getIcon();
                final String description = weather.getDescription();
                final String fullDescription = weather.getFullDescription();
                final String temp = weather.getTemp();
                final String pressure = weather.getPressure();
                final String humidity = weather.getHumidity();
                final String minTemp = weather.getMinTemp();
                final String maxTemp = weather.getMaxTemp();
                final String windSpeed = weather.getWindSpeed();
                final String windDirection = weather.getWindDirection();
                final String clouds = weather.getClouds();

                assert templatePath != null;
                File htmlTemplateFile = new File(templatePath.getFile());
                StringBuilder htmlText = new StringBuilder();
                try (Scanner sc = new Scanner(new FileReader(htmlTemplateFile))) {
                    while (sc.hasNext()) {
                        htmlText.append(sc.nextLine());
                    }
                }
                String htmlString = htmlText.toString();

                htmlString = htmlString.replace("$city", city);
                htmlString = htmlString.replace("$code", code);
                htmlString = htmlString.replace("$icon", icon);
                htmlString = htmlString.replace("$description", description);
                htmlString = htmlString.replace("$full_description", fullDescription);
                htmlString = htmlString.replace("$temp_avg", temp);
                htmlString = htmlString.replace("$pressure", pressure);
                htmlString = htmlString.replace("$humidity", humidity);
                htmlString = htmlString.replace("$temp_min", minTemp);
                htmlString = htmlString.replace("$temp_max", maxTemp);
                htmlString = htmlString.replace("$wind_speed", windSpeed);
                htmlString = htmlString.replace("$wind_deg", windDirection);
                htmlString = htmlString.replace("$clouds", clouds);

                File newHtmlFile = new File("target/widget.html");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(newHtmlFile, false))) {
                    writer.append(htmlString);
                }
            } else {
                System.err.println("GET request did not work.");
            }
            conn.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
