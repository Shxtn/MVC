package edu.fra.uas.config;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.NotenService.NotenService;
import edu.fra.uas.model.Note;
import jakarta.annotation.PostConstruct;

@Component
public class InitDatabase {

    /*
     * Diese Klasse soll für die Initialiseierung der daten notwendig sein.
     * Hier kann man schon die Anwendung darauf testen, ob Objekte vom Model
     * (hier: Noten) auch wirklich erstellt, geändert, geloesht etc. werden.
     * In unserem Fall erstellen wir beispielhaft mehrere Noten.
     */

    // hierfuer wurde ein Logger initialisiert, der die Klasse loggt
    private final Logger log = org.slf4j.LoggerFactory.getLogger(InitDatabase.class);

    // Hier wurde die Klasse NotenService (im Packet service) in die Klasse InitDate
    // injiziert mittels Autowired
    @Autowired
    NotenService notenService;

    @PostConstruct
    public void init() {
        log.debug("### Initialize Data ###"); // hier wird die Initialisierung angekuendigt

        log.debug("create Mathenote"); // Logausgabe kuendigt Erstellung einer Note an
        Note matheNote = new Note(); // Hier wird klassisch eine neue Note initilaisiert aus der Klasse Note | Hier
                                     // wird genauer gesagt eine Note "ohne value" erzeugt => Leerer Konstruktur
        // jetzt werden mit Setter die einzelenen Daten der Note zugewiesen

        matheNote.setFach("Mathe"); // Mathenote
        matheNote.setNote(2); // Note 2, wobei ich das bezweifele

        notenService.createNote(matheNote); // Hier wird die Nothe in NotenService hinzugefuegt, auch von da bekommt er
                                            // seine ID, auch wird dieser user automatisch in die userRepository
                                            // hinzugefuegt!

        log.debug("create Englsichnote");
        Note englischNote = new Note();

        englischNote.setFach("Englisch");
        englischNote.setNote(3);

        notenService.createNote(englischNote);

        log.debug("create Sportnote");
        Note sportNote = new Note();

        sportNote.setFach("Sport");
        sportNote.setNote(1);

        notenService.createNote(sportNote);

        notenService.getAverage();

        log.debug("### Data initialized ###");
    }

}
