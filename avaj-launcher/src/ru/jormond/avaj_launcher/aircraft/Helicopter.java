package ru.jormond.avaj_launcher.aircraft;

import ru.jormond.avaj_launcher.Flyable;
import ru.jormond.avaj_launcher.tower.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Helicopter extends Aircraft implements Flyable {

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

	protected Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String condition = this.newCoordinates(weatherTower.getWeather(this.coordinates), AircraftTypes.HELICOPTER);
		System.out.println("Helicopter#" + this.name + "(" + this.id + "): " + message.get(condition));
		if (condition.equals("LANDED")) {
			System.out.println("Helicopter#" + this.name + "(" + this.id + "): landing.");
			System.out.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
					+ "] Latitude: [" + this.coordinates.getLatitude()
					+ "] Height: [" + this.coordinates.getHeight() + "]");
		}
		this.weatherTower.unregister(this);
		System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")"
				+ " unregistered from weather tower.");
	}

	// Начинаем следить за метеорологической башней
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		System.out.println("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

}
