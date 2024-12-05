package edu.fra.uas.NotenService;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fra.uas.model.Note;
import edu.fra.uas.repo.NotenRepo;

@Service
public class NotenService {

    private static final Logger log = LoggerFactory.getLogger(NotenService.class);
    @Autowired
    private NotenRepo notenRepo;

    private long nextID = 1;

    // CRUD METHODEN
    // C: CREATE
    // R: READ/RETRIEVE
    // U: UPDATE
    // D: DELETE

    // FAZIT: Sehr gute copy paste Kenntnisse, wo ist aber jetzt die eigentliche
    // Notendurchschnittsberechnung!?

    public Note createNote(Note note) {
        log.debug("Note wird erstellt : " + note.getFach() + " Note: " + note.getNote()); // Richtig bitte und nicht
                                                                                          // "Sikerim"
        note.setId(nextID++);
        notenRepo.put(note.getId(), note);
        return note;
    }

    public Iterable<Note> getAllNoten() {
        log.debug("get all grades");
        return notenRepo.values();
    }

    public Note getNotenById(long id) {
        log.debug("getNote: " + id);
        return notenRepo.get(id);
    }

    public Note updateNote(Note note) {
        log.debug("updateNote: " + note.getId());
        notenRepo.put(note.getId(), note);
        return note;
    }

    public void deleteNote(long id) {
        log.debug("deleteNote : " + id);
        notenRepo.remove(id);
    }

    // NOTENDURCHSCHNITTSBERECHNUNG

    public Double getAverage() {
        Iterable<Note> noten = getAllNoten();
        
        // Initialisiere Variablen zur Berechnung des Durchschnitts
        double sum = 0.0;
        int count = 0;
        
        // Iteriere durch alle Noten und berechne die Summe und die Anzahl
        for (Note note : noten) {
            sum += note.getNote(); // `getNote()` gibt den Wert einer Note zurück
            count++;
        }
        
        // Vermeide Division durch Null
        if (count == 0) {
            return 0.0;
        }
        
        // Berechne den Durchschnitt
        log.debug("Durchschnitt: "+ sum / count);
        return sum / count;
    }
    
}