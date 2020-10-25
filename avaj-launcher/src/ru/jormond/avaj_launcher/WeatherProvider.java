package ru.jormond.avaj_launcher;

import ru.jormond.avaj_launcher.aircraft.Coordinates;

// Источник информации о погоде. Он у нас в единственном экземпляре (Singleton)
public class WeatherProvider {

	// Создаем источник информации о погоде
	private static WeatherProvider weatherProvider = new WeatherProvider();
	// Храним в массиве строк все погодные состояния
	private static String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

	private WeatherProvider() {}

	// Статический метод при помощи которого получаем доступ к объекту
	public static WeatherProvider getProvider() {
		return WeatherProvider.weatherProvider;
	}

	// Получаем информацию по погоде в конкретных координатах
	public String getCurrentWeather(Coordinates coordinates) {
		int value = (coordinates.getHeight() + coordinates.getLatitude() + coordinates.getLongitude()) / 2 % 4;
		return weather[value];
	}

}
