package tests;

import page.CurrentWeather;
import page.TomorrowWeather;

import java.util.Objects;
import java.util.Scanner;

public class TestAPI {

    public static void chooseDay(String day) throws Exception {
        if(Objects.equals(day, "current")){
            CurrentWeather.currentWeather();
        } else if (Objects.equals(day, "two") || Objects.equals(day, "two days")) {
            CurrentWeather.currentWeather();
            TomorrowWeather.tomorrowWeather();
        } else if(Objects.equals(day, "tomorrow")){
            TomorrowWeather.tomorrowWeather();
        } else {
            System.out.println("Please, make your choose. \n Which the weather forecast do you want to see: current, tomorrow or two days?");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which the weather forecast do you want to see: current, tomorrow or two days?");
        String day = scanner.next();

        chooseDay(day);
    }
}