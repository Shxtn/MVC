package edu.fra.uas.model;

import java.io.Serializable;

import org.slf4j.Logger;



public class Noten implements Serializable {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(Noten.class);

    private long id;
    private double note;
    private String fach;

    public Noten(){
       log.debug("Note erstellt ohne werte ");
    }
    public Noten(long id,double note, String fach) {
        this.id=id;
        this.note = note;
        this.fach = fach;
    }
    
    public void setNote(long id){
        this.id = id;
    }
    public long getNote(long id){
        return id;
    }

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
        Noten other = (Noten) obj;
        if (Double.doubleToLongBits(note) != Double.doubleToLongBits(other.note))
            return false;
        if (fach == null) {
            if (other.fach != null)
                return false;
        } else if (!fach.equals(other.fach))
            return false;
        return true;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }
    

    

}
