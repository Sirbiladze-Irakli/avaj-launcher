package ru.jormond.avaj_launcher.tower;

import ru.jormond.avaj_launcher.WeatherProvider;
import ru.jormond.avaj_launcher.aircraft.Coordinates;

// Метеорологическая башня
public class WeatherTower extends Tower {

	// Получаем погоду по координатам
	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	// меняем погоду
	void changeWeather() {
		this.conditionChanged();
	}
}
