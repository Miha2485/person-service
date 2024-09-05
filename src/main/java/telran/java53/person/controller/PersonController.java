package telran.java53.person.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java53.person.dto.AddressDto;
import telran.java53.person.dto.CityPopulationDto;
import telran.java53.person.dto.PersonDto;
import telran.java53.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    final PersonService personService;

    @PostMapping
    public Boolean addPesron(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }

    @DeleteMapping("/{id}")
    public PersonDto removePerson(@PathVariable Integer id) {
        return personService.removePerson(id);
    }

    @PutMapping("/{id}/name/{name}")
    public PersonDto updatePersoneName(@PathVariable Integer id, @PathVariable String name) {
        return personService.updatePersoneName(id, name);
    }

    @PutMapping("/{id}/address")
    public PersonDto updatePersoneAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        return personService.updatePersoneAddress(id, addressDto);
    }

    @GetMapping("/city/{city}")
    public PersonDto[] findPersonByCity(@PathVariable String city) {
        return personService.findPersonByCity(city);
    }

    @GetMapping("/name/{name}")
    public PersonDto[] findPersonByName(@PathVariable String name) {
        return personService.findPersonByName(name);
    }

    @GetMapping("/ages/{min}/{max}")
    public PersonDto[] findPersonBetweenAge(@PathVariable Integer min, @PathVariable Integer max) {
        return personService.findPersonBetweenAge(min, max);
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCitiesPopulation() {
        return personService.getCitiesPopulation();
    }
}


