# Schere \- Stein \- Papier

Ein einfaches JavaFX\-Spiel als ITP\-Schulprojekt der HTBLA Steyr.  
Entwickelt von Marcel Stübl und Moritz Raschko.

---

## Übersicht

Schere \- Stein \- Papier ist ein kleines Desktop\-Spiel, das mit Java, JavaFX und Maven umgesetzt wurde. Der Spieler wählt eine Geste und spielt gegen einen zufälligen Bot.

---

## Features

- Einfache, saubere JavaFX\-UI
- Zufälliger Botgegner
- Visualisierung der gewählten Gesten mit Icons
- Aufbau als Maven\-Projekt (inkl. Wrapper)

---

## Voraussetzungen

- Java 17 oder neuer (empfohlen)
- Maven (oder im Projekt enthaltene Wrapper)
- Windows (Entwicklung/Anleitung orientiert)

---

## Schnellstart (Windows)

Im Projektordner:

- Mit Maven Wrapper ausführen:
    - Spiel starten: `.\mvnw.cmd javafx:run`
    - Paket bauen: `.\mvnw.cmd clean package`

- Alternativ in IntelliJ IDEA:
    - `Launcher` als Hauptklasse starten (oder `GameApplication`).

---

## Projektstruktur (wichtigste Pfade)

- Quellcode: `src/main/java/htl/steyr/scheresteinpapier/`
- Ressourcen (FXML, CSS, Bilder): `src/main/resources/htl/steyr/scheresteinpapier/`
    - Bilder: `src/main/resources/htl/steyr/scheresteinpapier/img/`
- Maven: `pom.xml`
- Launcher (Main): `Launcher.java`

---

## Bedienung

- Buttons:
    - `Schere`, `Stein`, `Papier` — wählt die Geste.
    - Nach Auswahl werden die Buttons ausgeblendet, Spieler- und Botgesten angezeigt und der Gewinner ermittelt.
- Zurücksetzen: `Reset` - um es noch einmal zu versuchen.

---

## Architektur / Komponenten

- `GameController` — steuert UI, Auswahl, Anzeige und Spielablauf.
- `Player` — hält die gewählte `Gesture` und kann zufällig wählen.
- `Model/Gesture` — einfache Datenklasse mit Geste als ID (0 = Schere, 1 = Stein, 2 = Papier, 3 = unset).
- `GameApplication` / `Launcher` — JavaFX\-Application und Startpunkt.

---

## Anpassungen & Entwicklung

- Assets (Icons, CSS, FXML) befinden sich in `src/main/resources/htl/steyr/scheresteinpapier/`.
- UI anpassen: `game-view.fxml` und `style.css`.
- Neue Features: Highscore, Animationen, KI mit Gewichtung.

---

## Lizenz

Dieses Projekt ist für schulische Zwecke erstellt. Lizensierung nach Absprache (optional: MIT).

---

## Autoren

- Marcel Stübl
- Moritz Raschko

HTBLA Steyr — ITP Schulprojekt
