package ru.jormond.avaj_launcher.aircraft;

import ru.jormond.avaj_launcher.Flyable;
import ru.jormond.avaj_launcher.Simulator;
import ru.jormond.avaj_launcher.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private Map<String, String> message = new HashMap<>() {
		{
			put("SUN", "How well everything is visible in sunny weather!");
			put("RAIN", "Oh! I'm starting to get carried away!");
			put("FOG", "It's interesting to fly in a helicopter in the fog.");
			put("SNOW", "If the blades freeze, then I will definitely fall");
			put("LANDED", "Well, finally we sat down.");
		}
	};

	protected Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String condition = this.newCoordinates(weatherTower.getWeather(this.coordinates), AircraftTypes.HELICOPTER);
		Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): " + message.get(condition));
		if (condition.equals("LANDED")) {
			Simulator.writer.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
			Simulator.writer.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
					+ "] Latitude: [" + this.coordinates.getLatitude()
					+ "] Height: [" + this.coordinates.getHeight() + "]");
			this.weatherTower.unregister(this);
			Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")"
					+ " unregistered from weather tower.");
		}
	}

	// Начинаем следить за метеорологической башней
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.writer.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

}
