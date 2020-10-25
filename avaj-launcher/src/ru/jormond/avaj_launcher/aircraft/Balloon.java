package ru.jormond.avaj_launcher.aircraft;

import ru.jormond.avaj_launcher.Flyable;
import ru.jormond.avaj_launcher.tower.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Balloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private Map<String, String> message = new HashMap<>() {
		{
			put("SUN", "SUN");
			put("RAIN", "RAIN");
			put("FOG", "FOG");
			put("SNOW", "SNOW");
			put("LANDED", "LANDED");
		}
	};

	protected Balloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String condition = this.newCoordinates(weatherTower.getWeather(this.coordinates), AircraftTypes.BALLOON);
		System.out.println("Balloon#" + this.name + "(" + this.id + "): " + message.get(condition));
		if (condition.equals("LANDED")) {
			System.out.println("Balloon#" + this.name + "(" + this.id + "): landing.");
			System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
					+ "] Latitude: [" + this.coordinates.getLatitude()
					+ "] Height: [" + this.coordinates.getHeight() + "]");
		}
		this.weatherTower.unregister(this);
		System.out.println("Tower says: Balloon#" + this.name + "(" + this.id + ")"
				+ " unregistered from weather tower.");
	}

	// Начинаем следить за метеорологической башней
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		System.out.println("Tower says: Ballon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

}
