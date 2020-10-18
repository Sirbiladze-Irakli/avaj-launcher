package ru.jormond.avaj_launcher.aircraft;

import ru.jormond.avaj_launcher.Flyable;
import ru.jormond.avaj_launcher.tower.WeatherTower;

import java.util.HashMap;
import java.util.Map;

public class Balloon extends Aircraft implements Flyable {

	private static final int LONGITUDE = 0;
	private static final int LATITUDE = 1;
	private static final int HEIGHT = 2;
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
	// Изменение положения в зависимости от погоды
	// Первое значение долгота(Longitude)
	// Второе значение широта(Latitude)
	// Третье значение высота(Height)
	private int[] positionInSun = new int[]{2, 0, 4};
	private int[] positionInRain = new int[]{0, 0, -5};
	private int[] positionInFog = new int[]{0, 0, -3};
	private int[] positionInSnow = new int[]{0, 0, -15};

	protected Balloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() {
		String weather = this.newCoordinates(weatherTower.getWeather(this.coordinates));
		System.out.println("Balloon#" + this.name + "(" + this.id + "): " + message.get(weather));
		if (weather.equals("LANDED")) {
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

	private String newCoordinates(String weather) {
		int height = this.coordinates.getHeight();
		int[] changePosition = null;

		if (weather.equals("SUN")) {
			changePosition = positionInSun;
		} else if (weather.equals("RAIN")) {
			changePosition = positionInRain;
		} else if (weather.equals("FOG")) {
			changePosition = positionInFog;
		} else if (weather.equals("SNOW"))
			changePosition = positionInSnow;

		try  {
			height += changePosition[HEIGHT];
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		if (height > 100)
			height = 100;

		this.coordinates = new Coordinates(this.coordinates.getLongitude() + changePosition[LONGITUDE],
				this.coordinates.getLatitude() + changePosition[LATITUDE], height);

		if (height <= 0)
			return "LANDED";
		return weather;
	}
}
