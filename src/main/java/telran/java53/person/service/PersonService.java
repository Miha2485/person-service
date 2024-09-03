package telran.java53.person.service;

import telran.java53.person.dto.AddressDto;
import telran.java53.person.dto.CityPopulationDto;
import telran.java53.person.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	PersonDto removePerson(Integer id);

	PersonDto updatePersoneName(Integer id, String name);

	PersonDto updatePersoneAddress(Integer id, AddressDto addressDto);
	
	PersonDto[] findPersonByCity(String city);
	
	PersonDto[] findPersonByName(String name);
	
	PersonDto[] findPersonBetweenAge(Integer minAge, Integer maxAge);
	
	Iterable<CityPopulationDto> getCitiesPopulation();
}
