package telran.java53.person.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java53.person.dto.PersonDto;
import telran.java53.person.dto.AddressDto;
import telran.java53.person.model.Person;
import telran.java53.person.model.Address;

@RequiredArgsConstructor
@Component
public class PersonModelDtoMapperImpl implements PersonModelDtoMapper {

	private static final String MODEL_PACKAGE = "telran.java53.person.model.";
	private static final String DTO_SUFFIX = "Dto";
	private static final String DTO_PACKAGE = "telran.java53.person.dto.";

	private final ModelMapper modelMapper;

	@Override
	public Person mapToModel(PersonDto personDto) {
		return modelMapper.map(personDto, getModelClass(personDto));
	}

	@Override
	public Person mapToModel(PersonDto personDto, Class<? extends Person> targetClass) {
		return modelMapper.map(personDto, targetClass);
	}

	@Override
	public Address mapToModel(AddressDto addressDto, Class<Address> targetClass) {
		return modelMapper.map(addressDto, targetClass);
	}

	@Override
	public PersonDto mapToDto(Person person) {
		return modelMapper.map(person, getDtoClass(person));
	}

	private Class<? extends Person> getModelClass(PersonDto personDto) {
		String modelClassName = personDto.getClass().getSimpleName();
		modelClassName = modelClassName.substring(0, modelClassName.length() - 3); 
		try {
			@SuppressWarnings("unchecked")
			Class<? extends Person> clazz = (Class<? extends Person>) Class.forName(MODEL_PACKAGE + modelClassName);
			return clazz;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unknown person model class: " + modelClassName, e);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<? extends PersonDto> getDtoClass(Person person) {
		String dtoClassName = person.getClass().getSimpleName() + DTO_SUFFIX;
		try {
			Class<? extends PersonDto> clazz = (Class<? extends PersonDto>) Class.forName(DTO_PACKAGE + dtoClassName);
			return clazz;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unknown person DTO class: " + dtoClassName, e);
		}
	}
}
