package ru.jormond.avaj_launcher.aircraft;

import ru.jormond.avaj_launcher.Flyable;
import ru.jormond.avaj_launcher.SimulationException;

// Фабрика по созданию авиации. Реализация паттерна (Factory Method)
public class AircraftFactory {

	// Метод создания авиации
	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws SimulationException {

		// Если попадаются отрицательные координаты то кидаем исключение
		if (longitude < 0 || latitude < 0 || height < 0)
			throw new SimulationException("Error: coordinates cannot be negative");

		// Если высота больше 100, то просто оставляем её на максимальном значении
		if ( height > 100)
			height = 100;

		if (type.equalsIgnoreCase(AircraftTypes.BALLOON.value)) {
			System.out.println(type + " " + name + " has been created!");
			return new Balloon(name, new Coordinates(longitude, latitude, height));
		} else if (type.equalsIgnoreCase(AircraftTypes.HELICOPTER.value)) {
			System.out.println(type + " " + name + " has been created!");
			return new Helicopter(name, new Coordinates(longitude, latitude, height));
		} else if (type.equalsIgnoreCase(AircraftTypes.JETPLANE.value)) {
			System.out.println(type + " " + name + " has been created!");
			return new JetPlane(name, new Coordinates(longitude, latitude, height));
		}
		return null;
	}
}