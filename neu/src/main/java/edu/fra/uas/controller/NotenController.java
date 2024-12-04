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
import edu.fra.uas.model.Noten;

@Controller
public class NotenController {
    private final Logger log = org.slf4j.LoggerFactory.getLogger(NotenController.class);

    @Autowired
    private NotenService notenService;

    @RequestMapping
    public String get(){
        log.debug("get() wird aufgerufen : ");
        return "index.html";
    }
    
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String list(Model model){
        log.debug("list() wird aufgerufen : ");
        Iterable<Noten> notenIter = notenService.getAllNoten();
        List<Noten> notenliste = new ArrayList<>();
        for (Noten noten : notenIter) {
            notenliste.add(noten);
        }
        model.addAttribute("Noten", notenliste);
        return "list.html";

    }

    @RequestMapping(value = {"/find"}, method = RequestMethod.GET)
    public String find(@RequestParam("id") Long notenId, Model model) {
        log.debug("find() is called");
        Noten noten = notenService.getNotenById(notenId);
        model.addAttribute("Noten", noten);
        return "find.html";
    }

    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String add(@RequestParam("Modul") String modul, @RequestParam("Note") double noten, Model model) throws MissingServletRequestParameterException {
        log.debug("add() wird aufgerufen");
        Noten note = new Noten();
        note.setFach(modul);
        note.setNote(noten);
        notenService.createNote(note);
        model.addAttribute("note", note);
        return "add";
    }

    @RequestMapping(value =     {"/update"}, method = RequestMethod.GET)
    public String update() {
        log.debug("update() is called");
        return "update.html";
}
@RequestMapping(value = {"/updated"}, method = { RequestMethod.GET, RequestMethod.POST })
    public String update(@RequestParam("id") Long notenId, 
                         @RequestParam("Modul ") String modul, 
                         @RequestParam("Noten") double noten, 
                         Model model) {
        log.debug("updated() is called");
        Noten note = notenService.getNotenById(notenId);
        note.setId(notenId);
        note.setFach(modul);
        note.setNote(noten);
        notenService.updateUser(note);
        model.addAttribute("Noten", note);
        return "updated.html";
    }    

    @RequestMapping(value = {"/delete/{id}"}, method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long notenid, Model model) {
        log.debug("delete() is called");
        Noten note = notenService.getNotenById(notenid);
        notenService.deleteUser(notenid);
        model.addAttribute("user", note);
        return "deleted.html";
    }

}

