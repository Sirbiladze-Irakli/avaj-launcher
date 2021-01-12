package ru.jormond.avajlauncher.aircraft;

// Координаты
public class Coordinates {

	// Долгота
	private int	longitude;
	// Широта
	private int latitude;
	// Высота
	private int height;

	Coordinates(int longitude, int latitude, int height) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.height = height;
	}

	public int getLongitude() {
		return longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getHeight() {
		return height;
	}

}
