package org.example.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.example.core.api.dto.PersonDTO;
import org.example.core.domain.entities.PersonEntity;
import org.example.core.repositories.entities.PersonEntityRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PersonEntityFactory {

    private final PersonEntityRepository repository;

    PersonEntity createPersonEntity(PersonDTO personDTO) {
        Optional<PersonEntity> personOpt = repository.findBy(
                personDTO.getPersonFirstName(),
                personDTO.getPersonLastName(),
                personDTO.getPersonCode());
        if (personOpt.isPresent()) {
            return personOpt.get();
        } else {
            PersonEntity person = new PersonEntity();
            person.setFirstName(personDTO.getPersonFirstName());
            person.setLastName(personDTO.getPersonLastName());
            person.setPersonCode(personDTO.getPersonCode());
            person.setBirthDate(personDTO.getPersonBirthDate());
            return repository.save(person);
        }
    }

}
