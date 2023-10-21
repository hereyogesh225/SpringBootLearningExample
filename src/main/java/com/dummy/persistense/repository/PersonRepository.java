package com.dummy.persistense.repository;

import com.dummy.persistense.entity.PersonHE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonHE,Integer> {

    long deleteAllByPersonNameAndUserType(String personName, String userType);
}
