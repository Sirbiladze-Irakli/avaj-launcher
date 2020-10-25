package ru.jormond.avaj_launcher;

import java.util.ArrayList;

public class Tower {

	private ArrayList<Flyable> observers = new ArrayList<>();

	public void register(Flyable flyable) {
		if (!(observers.contains(flyable))) {
			observers.add(flyable);
		}
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
