package page;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;

public class TomorrowWeather extends CurrentWeather {

    @Override
    protected URI createURI(String cityName) {
        return URI.create("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName +
                "&units=metric&lang=en&appid=4de50bdb7a09d89968121e05092b7b8f&cnt=8");
    }

    public static void tomorrowWeather() throws IOException, InterruptedException {

        TomorrowWeather object = new TomorrowWeather();

        String json = object.sendGETRequest(object.getCityName());//запись ответа в переменную типа String

        JSONObject obj = new JSONObject(json);
        JSONArray list = obj.getJSONArray("list");//в body list содержит массив из json
        JSONObject newJson = list.optJSONObject(7);//последний элемент массива записываем в новую переменную

        printWeatherInfo(newJson);
    }

    protected static void printWeatherInfo(JSONObject obj) {
        String dateTime = obj.getString("dt_txt");
        int temp = obj.getJSONObject("main").getInt("temp");
        int feel = obj.getJSONObject("main").getInt("feels_like");
        int humidity = obj.getJSONObject("main").getInt("humidity");
        double windSpeed = obj.getJSONObject("wind").getDouble("speed");

        JSONArray weather = obj.getJSONArray("weather");
        for (int i = 0; i < weather.length(); i++) {
            String main = weather.getJSONObject(i).getString("main");
            String description = weather.getJSONObject(i).getString("description");

            System.out.println("\n"
                    + dateTime + ", " + " \uD83D\uDDFA️\nWEATHER ➢  " + main + ", " + description +
                    "\n\nTEMPERATURE \uD83C\uDF21 " + temp + "°C\t\tHUMIDITY \uD83D\uDCA7 " + humidity + "%" +
                    "\nFEELS LIKE  \uD83C\uDF21 " + feel + "°C\t\tWIND     \uD83D\uDCA8 " + windSpeed + "m/s" +
                    "\n");
        }
    }
}
