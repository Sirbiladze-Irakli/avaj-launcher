package ru.jormond.avaj_launcher.aircraft;

public enum AircraftTypes {
	HELICOPTER("helicopter"),
	JETPLANE("jetplane"),
	BALLOON("baloon");

	String value;

	AircraftTypes(String value) {
		this.value = value;
	}
}
