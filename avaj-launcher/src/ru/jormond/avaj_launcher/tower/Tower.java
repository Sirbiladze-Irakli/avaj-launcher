package ru.jormond.avaj_launcher.tower;

import ru.jormond.avaj_launcher.Flyable;

import java.util.ArrayList;

// Реализация паттерна (Observer).
// Мы записываем в наш список объекты которые на нас подписаны
// При вызове метода conditionChanged()
// Пробегаемся по всем подписчикам и вызываем у них соответствующий метод
public class Tower {

	private ArrayList<Flyable> observers = new ArrayList<>();

	public void register(Flyable flyable) {
		observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		observers.remove(flyable);
	}

	protected void conditionChanged() {
		for (int i = 0; i < observers.size(); i++) {
			observers.get(i).updateConditions();
		}
	}

}
