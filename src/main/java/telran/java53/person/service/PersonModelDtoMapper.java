package telran.java53.person.service;

import telran.java53.person.dto.AddressDto;
import telran.java53.person.dto.PersonDto;
import telran.java53.person.model.Address;
import telran.java53.person.model.Person;

public interface PersonModelDtoMapper {
	Person mapToModel(PersonDto personDto);

	Person mapToModel(PersonDto personDto, Class<? extends Person> targetClass);

	Address mapToModel(AddressDto addressDto, Class<Address> targetClass);

	PersonDto mapToDto(Person person);
}
