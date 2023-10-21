package com.dummy.service;

import com.dummy.persistense.entity.PersonHE;
import com.dummy.persistense.repository.PersonRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonHE savePerson(PersonHE person) {
       return personRepository.save(person);
    }

    @Transactional
    public void deletePerson(String personName, String userType) {
        long l = personRepository.deleteAllByPersonNameAndUserType(personName, userType);
        System.out.println(" vlaue is  ------------ "+ l);

    }


}
