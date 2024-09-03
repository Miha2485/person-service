package telran.java53.person.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.java53.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	List<Person> findByName(String name);
	
}
