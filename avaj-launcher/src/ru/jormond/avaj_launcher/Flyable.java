package ru.jormond.avaj_launcher;

import ru.jormond.avaj_launcher.tower.WeatherTower;

public interface Flyable {

	public void updateConditions();

	public void registerTower(WeatherTower weatherTower);

}
