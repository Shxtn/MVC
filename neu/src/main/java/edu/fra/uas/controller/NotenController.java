package edu.fra.uas.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.fra.uas.NotenService.NotenService;
import edu.fra.uas.model.Note;

@Controller
public class NotenController {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(NotenController.class);

    @Autowired
    private NotenService notenService;

    @RequestMapping
    public String get() {
        log.debug("get() wird aufgerufen : ");
        return "index.html";
    }

    @RequestMapping(value = { "/list" }, method = RequestMethod.GET)
    public String list(Model model) {
        log.debug("list() wird aufgerufen : ");
        Iterable<Note> notenIter = notenService.getAllNoten();
        List<Note> notenliste = new ArrayList<>();
        for (Note noten : notenIter) {
            notenliste.add(noten);
        }
        model.addAttribute("Noten", notenliste);
        return "list.html";

    }

    @RequestMapping(value = { "/find" }, method = RequestMethod.GET)
    public String find(@RequestParam("id") Long notenId, Model model) {
        log.debug("find() is called");
        Note noten = notenService.getNotenById(notenId);
        model.addAttribute("note", noten);
        return "find.html";
    }

    @RequestMapping(value = { "/add" }, method = RequestMethod.GET)
    public String add() {
        log.debug("add() is called");
        return "add.html";
    }

    @RequestMapping(value = { "/added" }, method = RequestMethod.GET)
    public String added(@RequestParam("Modul") String modul, @RequestParam("note") double noteZahl, Model model)
            throws MissingServletRequestParameterException {
        log.debug("add() wird aufgerufen");
        Note note = new Note();
        note.setFach(modul);
        note.setNote(noteZahl);
        notenService.createNote(note);
        model.addAttribute("note", note);
        return "added.html"; // hast hier nur add geschrieben und nicht "add.html"
    }

    @RequestMapping(value = { "/update" }, method = RequestMethod.GET)
    public String update() {
        log.debug("update() is called");
        return "update.html";
    }

    @RequestMapping(value = { "/updated" }, method = { RequestMethod.GET, RequestMethod.POST })
    public String update(@RequestParam("notenId") Long notenId,
            @RequestParam("Modul") String modul,
            @RequestParam("Note") double note,
            Model model) {
        log.debug("updated() is called");
        Note neueNote = notenService.getNotenById(notenId);
        neueNote.setId(notenId);
        neueNote.setFach(modul);
        neueNote.setNote(note);
        notenService.updateNote(neueNote);
        model.addAttribute("note", neueNote);
        return "updated.html";
    }

    @RequestMapping(value = { "/delete/{id}" }, method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long notenid, Model model) {
        log.debug("delete() is called");
        Note note = notenService.getNotenById(notenid);
        notenService.deleteNote(notenid);
        model.addAttribute("note", note);
        return "deleted.html";
    }

}
