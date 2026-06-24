# Beleg PZR2 (94)
Checkboxen gemäß Bearbeitung befüllen und _kursiv_ gesetzten Text durch entsprechende Angaben ersetzten.
Bei keiner Angabe wird nur Entwurf, Tests (ohne Testabdeckung Rest), Fehlerfreiheit und Basisfunktionalität bewertet.
Die Zahl in der Klammer sind die jeweiligen Punkte für die Bewertung.
Die empfohlenen Realisierungen sind **fett** gesetzt und ergeben 53 Punkte.
Ergänzende Anmerkungen bitte immer _kursiv_ setzen. Andere Änderungen sind nicht zulässig.

## Vorrausetzungen für das Bestehen
- [x] Quellen angegeben
- [x] zip Archiv mit dem Projekt im root
- [x] IntelliJ-Projekt (kein Gradle, Maven o.ä.)
- [x] keine weiteren Bibliotheken außer JUnit5, Mockito und JavaFX (und deren Abhängigkeiten)
- [x] keine Umlaute, Sonderzeichen, etc. in Datei- und Pfadnamen
- [x] Trennung zwischen Test- und Produktiv-Code
- [x] kompilierbar
- [x] geforderte main-Methoden nur im default package des module belegProg3
- [x] keine vorgetäuschte Funktionalität (inkl. leere Tests)

## Entwurf (11)
- [x] **Benennung** (2)
- [x] **Zuständigkeit** (2)
- [x] **Paketierung** (2)
- [x] **Schichtenaufteilung (via modules)** (2)
- [x] **nur absolut notwendige down casts** (2)
- [x] keine Duplikate (1)

## Tests (33)
- [x] **Testqualität** (9)
- [x] **Testabdeckung GL (inkl. Abhängigkeiten)** (8) _Abdeckung in Prozent angeben_
- [ ] Testabdeckung Rest (6)
  - [ ] Einfügen von Kund*innen über das CLI _Tests angeben_
  - [ ] Anzeigen von Kund*innen über das CLI _Tests angeben_
  - [ ] ein Beobachter _Tests angeben_
  - [ ] deterministische Funktionalität der Simulationen _Tests angeben_
  - [ ] Speichern via JOS oder JBP _Tests angeben_
  - [ ] Laden via JOS oder JBP _Tests angeben_
- [x] **mindestens 5 Unittests, die Mockito verwenden** (5)
- [x] mindestens 4 Spy- / Verhaltens-Tests (4)
- [x] **keine unbeabsichtigt fehlschlagenden Test** (1)

## Fehlerfreiheit (10)
- [x] **Kapselung** (5)
- [x] **keine Ablauffehler** (5)

## Basisfunktionalität (10)
- [x] **CRUD** (2)
- [x] **CLI** (2)
  * Syntax gemäß Anforderungen
- [x] **Simulation** (2)
  * ohne race conditions, ohne sleep
- [x] **I/O** (2)
- [ ] **Net** (2)

## Funktionalität (20)
- [x] vollständige GL (2)
- [x] threadsichere GL (1)
- [x] vollständiges CLI (2)
- [x] ausdifferenziertes event-System mit mindestens 3 events (2)
- [x] alternativ konfiguriertes CLI (1)
  * _angeben welche Funktionalität im alternativen CLI deaktiviert_
- [x] observer (2)
- [ ] angemessene Collections bzgl. Anforderungen (2)
- [ ] Simulation 2 (1)
- [ ] Simulation 3 (1)
- [x] sowohl JBP als auch JOS (2)
- [ ] sowohl TCP als auch UDP (2)
- [ ] Server unterstützt konkurierende Clients für TCP oder UDP (2)

## zusätzliche Anforderungen GUI (5)
- [x] Basis-GUI (CRUD) (1)
- [x] skalierbare GUI (1)
- [x] vollständige GUI (1)
- [x] Sortierung in der GUI (1)
- [x] FXML verwendet (1)

## zusätzliche Anforderungen (5)
- [ ] Übersetzung der Befehle (1)
- [ ] Übersetzung der Typbezeichnungen (2)
- [ ] erweiterbare Implementierung (2)

