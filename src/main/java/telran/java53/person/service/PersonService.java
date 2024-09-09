package telran.java53.person.service;

import telran.java53.person.dto.AddressDto;
import telran.java53.person.dto.CityPopulationDto;
import telran.java53.person.dto.PersonDto;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(Integer id);

	PersonDto removePerson(Integer id);

	PersonDto updatePersonName(Integer id, String name);

	PersonDto updatePersonAddress(Integer id, AddressDto addressDto);

	Iterable<PersonDto> findPersonByCity(String city);

	Iterable<PersonDto> findPersonByName(String name);

	Iterable<PersonDto> findPersonBetweenAge(Integer minAge, Integer maxAge);

	Iterable<CityPopulationDto> getCitiesPopulation();

	Iterable<PersonDto> findEmployeeBySalary(int min, int max);

	Iterable<PersonDto> getChildren();
}
