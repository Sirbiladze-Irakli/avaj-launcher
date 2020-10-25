package ru.jormond.avaj_launcher.aircraft;

import ru.jormond.avaj_launcher.Flyable;
import ru.jormond.avaj_launcher.Simulator;
import ru.jormond.avaj_launcher.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;
	private Map<String, String> message = new HashMap<>() {
		{
			put("SUN", "The sun is shining overboard. beautiful view.");
			put("RAIN", "How good it is to be on the plane in this rainy weather.");
			put("FOG", "How can you fly at such speed without seeing anything.");
			put("SNOW", "Can the plane freeze in snowy weather?");
			put("LANDED", "Well, here we are.");
		}
	};

	protected JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String condition = this.newCoordinates(weatherTower.getWeather(this.coordinates), AircraftTypes.JETPLANE);
		Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + "): " + message.get(condition));
		if (condition.equals("LANDED")) {
			Simulator.writer.println("JetPlane#" + this.name + "(" + this.id + "): landing.");
			Simulator.writer.println("Current coordinates: Longtitude: [" + this.coordinates.getLongitude()
					+ "] Latitude: [" + this.coordinates.getLatitude()
					+ "] Height: [" + this.coordinates.getHeight() + "]");
			this.weatherTower.unregister(this);
			Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")"
					+ " unregistered from weather tower.");
		}
	}

	// Начинаем следить за метеорологической башней
	@Override
	public void registerTower(WeatherTower weatherTower) {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Simulator.writer.println("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
	}

}
