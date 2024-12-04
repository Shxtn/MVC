package edu.fra.uas.NotenService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.fra.uas.model.Noten;
import edu.fra.uas.repo.NotenRepo;

@Service
public class NotenService {
    private static final Logger log = LoggerFactory.getLogger(NotenService.class);
    @Autowired
    private NotenRepo notenRepo;
    private long nextID = 1;

    public Noten createNote(Noten note){
        log.debug("Sikerim : ");
        note.setId(nextID++);
        notenRepo.put(note.getId(), note);
        return note;
    }
    public Iterable<Noten> getAllNoten() {
        log.debug("get all grades");
        return notenRepo.values();
    }
    public Noten getNotenById(long id) {
        log.debug("getNote: " + id);
        return notenRepo.get(id);
    }
    public Noten updateUser(Noten user) {
        log.debug("updateNote: " + user);
        notenRepo.put(user.getId(), user);
        return user;
    }
    public void deleteUser(long id) {
        log.debug("deleteNote : " + id);
        notenRepo.remove(id);
    }
}

