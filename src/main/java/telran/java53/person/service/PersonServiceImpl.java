package telran.java53.person.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java53.person.dao.PersonRepository;
import telran.java53.person.dto.AddressDto;
import telran.java53.person.dto.CityPopulationDto;
import telran.java53.person.dto.PersonDto;
import telran.java53.person.dto.exception.PersonNotFoundException;
import telran.java53.person.model.Person;
import telran.java53.person.model.Address;

@Transactional
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto removePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersoneName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updatePersoneAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException());
		person.setAddress(modelMapper.map(addressDto, Address.class));
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto[] findPersonByCity(String city) {
	    return StreamSupport.stream(personRepository.findAll().spliterator(), false)
	            .filter(person -> person.getAddress() != null && person.getAddress().getCity().equalsIgnoreCase(city))
	            .map(person -> modelMapper.map(person, PersonDto.class))
	            .toArray(PersonDto[]::new);
	}


	@Override
	public PersonDto[] findPersonByName(String name) {
		List<Person> people = personRepository.findByName(name);
		return people.stream().map(person -> modelMapper.map(person, PersonDto.class)).toArray(PersonDto[]::new);
	}

	@Override
	public PersonDto[] findPersonBetweenAge(Integer minAge, Integer maxAge) {
	    LocalDate from = LocalDate.now().minusYears(maxAge);
	    LocalDate to = LocalDate.now().minusYears(minAge);

	    return StreamSupport.stream(personRepository.findAll().spliterator(), false)
	            .filter(p -> p.getBirthDate() != null &&
	                          p.getBirthDate().isAfter(from) &&
	                          p.getBirthDate().isBefore(to))
	            .map(p -> modelMapper.map(p, PersonDto.class))
	            .toArray(PersonDto[]::new);
	}


	@Override
	public Iterable<CityPopulationDto> getCitiesPopulation() {
		// TODO Auto-generated method stub
		return null;
	}

}
