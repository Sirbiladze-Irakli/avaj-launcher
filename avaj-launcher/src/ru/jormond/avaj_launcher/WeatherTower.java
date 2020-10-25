package ru.jormond.avaj_launcher;

import ru.jormond.avaj_launcher.aircraft.Coordinates;

// Метеорологическая башня
public class WeatherTower extends Tower {

	// Получаем погоду по координатам
	public String getWeather(Coordinates coordinates) {
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}

	// меняем погоду
	public void changeWeather() {
		this.conditionChanged();
	}
}
