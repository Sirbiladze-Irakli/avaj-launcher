package ru.jormond.avajlauncher;

import ru.jormond.avajlauncher.aircraft.Coordinates;

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
