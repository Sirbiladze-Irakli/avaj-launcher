#!/bin/sh
javac -d . src/ru/jormond/avaj_launcher/*.java src/ru/jormond/avaj_launcher/aircraft/*.java src/ru/jormond/avaj_launcher/exceptions/*.java
java -cp . ru.jormond.avaj_launcher.Simulator scenario.txt
