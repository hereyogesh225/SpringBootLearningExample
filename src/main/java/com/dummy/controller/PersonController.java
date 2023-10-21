package com.dummy.controller;

import com.dummy.persistense.entity.PersonHE;
import com.dummy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/")
    public void savePerson(@RequestBody PersonHE person) {
        personService.savePerson(person);
    }

    @DeleteMapping("/{personName}/{type}")
    public void deletePerson(@PathVariable String personName, @PathVariable String type) {
        personService.deletePerson(personName, type);
    }
}
