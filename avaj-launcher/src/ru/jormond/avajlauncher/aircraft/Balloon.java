package ru.jormond.avajlauncher.aircraft;

import ru.jormond.avajlauncher.Flyable;
import ru.jormond.avajlauncher.Simulator;
import ru.jormond.avajlauncher.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Balloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private Map<String, String> message = new HashMap<String, String>() {
		{
			put("SUN", "Nice to fly in sunny weather on a hot air balloon.");
			put("RAIN", "Nasty rain pounds right in the face.");
			put("FOG", "You can't see anything behind this fog.");
			put("SNOW", "Flying in a hot air balloon in the snow is not a good idea.");
			put("LANDED", "The landing was soft.");
		}
	};

	protected Balloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String condition = this.newCoordinates(weatherTower.getWeather(this.coordinates), AircraftTypes.BALLOON);
		Simulator.writer.println("Balloon#" + this.name + "(" + this.id + "): " + message.get(condition));
		if (condition.equals("LANDED")) {
			Simulator.writer.println("Balloon#" + this.name + "(" + this.id + "): landing.");
			Simulator.writer.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
					+ "] Latitude: [" + this.coordinates.getLatitude()
					+ "] Height: [" + this.coordinates.getHeight() + "]");
			this.weatherTower.unregister(this);
			Simulator.writer.println("Tower says: Balloon#" + this.name + "(" + this.id + ")"
					+ " unregistered from weather tower.");
		}
	}

	// Начинаем следить за метеорологической башней
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.writer.println("Tower says: Ballon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

}
