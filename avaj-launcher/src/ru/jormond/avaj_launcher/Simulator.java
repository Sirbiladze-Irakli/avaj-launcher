package ru.jormond.avaj_launcher;

import ru.jormond.avaj_launcher.aircraft.AircraftFactory;
import ru.jormond.avaj_launcher.exceptions.SimulationException;
import ru.jormond.avaj_launcher.exceptions.ValidationException;

import java.io.*;


public class Simulator {

    private static final int TYPE = 0;
    private static final int NAME = 1;
    private static final int LONGITUDE = 2;
    private static final int LATITUDE = 3;
    private static final int HEIGHT = 4;
    public static PrintWriter writer;

    public static void main(String[] args) {
        if (args.length == 1) {
            int simulationTimes = 0;
            String currentLine;
            String aircraft[];
            BufferedReader bufferedReader;
            File simulationFile = new File("simulation.txt");
            WeatherTower weatherTower = new WeatherTower();

            try {
                writer = new PrintWriter(simulationFile);
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
            try {
                bufferedReader = new BufferedReader(new FileReader(new File(args[0])));
                simulationTimes = Integer.parseInt(bufferedReader.readLine());  // Первой строкой достаем время симуляции
                while ((currentLine = bufferedReader.readLine()) != null) {
                    aircraft = currentLine.split("\\s+");       // Сплитим строку по пробельным символам
                    AircraftFactory.newAircraft(aircraft[TYPE], aircraft[NAME], Integer.parseInt(aircraft[LONGITUDE]),
                            Integer.parseInt(aircraft[LATITUDE]), Integer.parseInt(aircraft[HEIGHT])).registerTower(weatherTower);

                }
                bufferedReader.close();
            } catch (IOException | ValidationException  | SimulationException exception) {
                System.out.println(exception.getMessage());
            }

            while (simulationTimes-- > 0) {
                weatherTower.changeWeather();
            }
            writer.close();
        }
    }

}
