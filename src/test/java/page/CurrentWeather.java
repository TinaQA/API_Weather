package page;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrentWeather {

    protected URI createURI(String cityName) {
        return URI.create("https://api.openweathermap.org/data/2.5/weather?q=" + cityName +
                "&units=metric&lang=en&appid=4de50bdb7a09d89968121e05092b7b8f");
    }

    public String getCityName() { //city selection method
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter any city: ");
        return scanner.next();
    }

    HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    protected String sendGETRequest(String cityName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(createURI(cityName))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static void currentWeather() throws Exception {

        CurrentWeather object = new CurrentWeather();
        String json = object.sendGETRequest(object.getCityName());

        JSONObject obj = new JSONObject(json);
        printWeatherInfo(obj);
    }

    protected static void printWeatherInfo(JSONObject obj) {
        String cityName = obj.getString("name");
        String countryCode = obj.getJSONObject("sys").getString("country");
        int temp = obj.getJSONObject("main").getInt("temp");
        int feel = obj.getJSONObject("main").getInt("feels_like");
        int humidity = obj.getJSONObject("main").getInt("humidity");
        double windSpeed = obj.getJSONObject("wind").getDouble("speed");

        JSONArray weather = obj.getJSONArray("weather");
        for (int i = 0; i < weather.length(); i++) {
            String main = weather.getJSONObject(i).getString("main");
            String description = weather.getJSONObject(i).getString("description");

            System.out.println("\n"
                    + cityName + ", " + countryCode + " \uD83D\uDDFA️\nWEATHER ➢  " + main + ", " + description +
                    "\n\nTEMPERATURE \uD83C\uDF21 " + temp + "°C\t\tHUMIDITY \uD83D\uDCA7 " + humidity + "%" +
                    "\nFEELS LIKE  \uD83C\uDF21 " + feel + "°C\t\tWIND     \uD83D\uDCA8 " + windSpeed + "m/s" +
                    "\n");
        }
    }

}
