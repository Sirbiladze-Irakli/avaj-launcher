package ru.jormond.avajlauncher;

public enum WeatherType {
	SUN("SUN"),
	RAIN("RAIN"),
	FOG("FOG"),
	SNOW("SNOW");

	private String value;

	WeatherType(String value) {
		this.value = value;
	}
}
