package edu.fra.uas.model;

import java.io.Serializable;

import org.slf4j.Logger;

public class Note implements Serializable {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Note.class);

    private long id;
    private double note;
    private String fach;

    public Note() {
        log.debug("Note erstellt ohne werte ");
    }

    public Note(long id, double note, String fach) {
        this.id = id;
        this.note = note;
        this.fach = fach;
    }

    /*
     * Aiaiaiai, setNote(), dabei wurde hier die ID gesettet ich lass das mal hier
     * als absolutes Negativbeispiel
     * 
     * public void setNote(long id){
     * this.id = id;
     * }
     * public long getNote(long id){
     * return id;
     * }
     */

    public void setNote(double note) {
        this.note = note;
    }

    public void setFach(String fach) {
        this.fach = fach;
    }

    public double getNote() {
        return note;
    }

    public String getFach() {
        return fach;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(note);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((fach == null) ? 0 : fach.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Note other = (Note) obj;
        if (Double.doubleToLongBits(note) != Double.doubleToLongBits(other.note))
            return false;
        if (fach == null) {
            if (other.fach != null)
                return false;
        } else if (!fach.equals(other.fach))
            return false;
        return true;
    }

}
