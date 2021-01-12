package ru.jormond.avajlauncher;

import ru.jormond.avajlauncher.aircraft.Aircraft;
import ru.jormond.avajlauncher.aircraft.AircraftFactory;
import ru.jormond.avajlauncher.exceptions.SimulationException;
import ru.jormond.avajlauncher.exceptions.ValidationException;

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
                simulationTimes = firstLine(bufferedReader);  // Первой строкой достаем время симуляции
                readFile(bufferedReader, weatherTower);
                bufferedReader.close();
            } catch (IOException | ValidationException  | SimulationException exception) {
                System.out.println(exception.getMessage());
            }

            int step = 1;
            while (simulationTimes-- > 0) {
                writer.println("Step: " + step++);
                weatherTower.changeWeather();
                writer.println();
            }
            writer.close();
        }
    }

    private static int firstLine(BufferedReader bufferedReader) throws IOException, ValidationException {
        String line = bufferedReader.readLine();

        if (line == null) {
            throw new ValidationException("File is empty.");
        }

        if (line.split("\\s+").length != 1) {
            throw new ValidationException("First line is expected only one positive integer number.");
        }

        int simulationTimes = Integer.parseInt(line);
        if (simulationTimes < 1) {
            throw new ValidationException("First line is expected only positive number.");
        }
        return simulationTimes;
    }

    private static void readFile(BufferedReader bufferedReader, WeatherTower weatherTower) throws IOException, ValidationException, SimulationException {
        String currentLine;
        String aircraft[];
        int line = 2;

        while ((currentLine = bufferedReader.readLine()) != null) {
            aircraft = currentLine.split("\\s+");       // Сплитим строку по пробельным символам

            if (aircraft.length != 5) {
                throw new ValidationException("Line " + line + ": Has " + aircraft.length + " arguments. Expected 5 arguments.");
            }

            int longitude = Integer.parseInt(aircraft[LONGITUDE]);
            if (longitude < 1) {
                throw new ValidationException("Line " + line + ": Longitude is " + longitude + ". Expected > 0.");
            }

            int latitude = Integer.parseInt(aircraft[LATITUDE]);
            if (latitude < 1) {
                throw new ValidationException("Line " + line + ": Latitude is " + latitude + ". Expected > 0.");
            }

            int height = Integer.parseInt(aircraft[HEIGHT]);
            if (height < 1 ) {
                throw new ValidationException("Line " + line + ": Height is " + height + ". Expected > 0");
            }

            AircraftFactory.newAircraft(aircraft[TYPE], aircraft[NAME], Integer.parseInt(aircraft[LONGITUDE]),
                    Integer.parseInt(aircraft[LATITUDE]), Integer.parseInt(aircraft[HEIGHT])).registerTower(weatherTower);
            writer.println();
            line++;
        }
        writer.println();
    }

}
