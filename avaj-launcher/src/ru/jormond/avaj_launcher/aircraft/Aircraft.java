package ru.jormond.avaj_launcher.aircraft;

// Базовый класс для всей авиации
public class Aircraft {

	// Долгота
	private final int LONGITUDE = 0;
	// Широта
	private final int LATITUDE = 1;
	// Высота
	private final int HEIGHT = 2;
	// Идентификатор Воздушного судна
	protected long id;
	// Название Воздушного судна
	protected String name;
	// Координаты Воздушного судна
	protected Coordinates coordinates;
	// Общий счетчик идентификаторов
	private static long idCounter  = 0;
	/*
	 *	Изменение положения в зависимости от погоды
	 *	Первое значение долгота(Longitude)
	 *	Второе значение широта(Latitude)
	 *	Третье значение высота(Height)
	 */
	private int[] helicopterInSun = new int[]{10, 0, 2};
	private int[] helicopterInRain = new int[]{5, 0, 0};
	private int[] helicopterInFog = new int[]{1, 0, 0};
	private int[] helicopterInSnow = new int[]{0, 0, -12};

	private int[] balloonInSun = new int[]{2, 0, 4};
	private int[] balloonInRain = new int[]{0, 0, -5};
	private int[] balloonInFog = new int[]{0, 0, -3};
	private int[] balloonInSnow = new int[]{0, 0, -15};

	private int[] jetPlaneInSun = new int[]{0, 10, 2};
	private int[] jetPlaneInRain = new int[]{0, 5, 0};
	private int[] jetPlaneInFog = new int[]{0, 1, 0};
	private int[] jetPlaneInSnow = new int[]{0, 0, -7};

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = this.nextId();
	}

	private long nextId() {
		return ++idCounter;
	}

	protected String newCoordinates(String weather, AircraftTypes type) {
		int height = this.coordinates.getHeight();
		int[] changePosition = null;

		if (type == AircraftTypes.BALLOON) {
			if (weather.equals("SUN")) {
				changePosition = balloonInSun;
			} else if (weather.equals("RAIN")) {
				changePosition = balloonInRain;
			} else if (weather.equals("FOG")) {
				changePosition = balloonInFog;
			} else if (weather.equals("SNOW"))
				changePosition = balloonInSnow;
		} else if (type == AircraftTypes.HELICOPTER) {
			if (weather.equals("SUN")) {
				changePosition = helicopterInSun;
			} else if (weather.equals("RAIN")) {
				changePosition = helicopterInRain;
			} else if (weather.equals("FOG")) {
				changePosition = helicopterInFog;
			} else if (weather.equals("SNOW"))
				changePosition = helicopterInSnow;
		} else if (type == AircraftTypes.JETPLANE) {
			if (weather.equals("SUN")) {
				changePosition = jetPlaneInSun;
			} else if (weather.equals("RAIN")) {
				changePosition = jetPlaneInRain;
			} else if (weather.equals("FOG")) {
				changePosition = jetPlaneInFog;
			} else if (weather.equals("SNOW"))
				changePosition = jetPlaneInSnow;
		}

		try  {
			height += changePosition[HEIGHT];
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		if (height > 100)
			height = 100;

		if (height <= 0) {
			height = 0;
			weather = "LANDED";
		}

		this.coordinates = new Coordinates(this.coordinates.getLongitude() + changePosition[LONGITUDE],
				this.coordinates.getLatitude() + changePosition[LATITUDE], height);

		return weather;
	}

}
