package telran.java53.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import telran.java53.person.dto.CityPopulationDto;
import telran.java53.person.model.Child;
import telran.java53.person.model.Employee;
import telran.java53.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
//	Set<Person> findByNameIgnoreCase(String name);
//
//	Set<Person> findByAddressCityIgnoreCase(String city);
//
//	Set<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
	@Query("select p from Citizen p where upper(p.name)=upper(?1)")
	Stream<Person> findByNameIgnoreCase(String name);

	@Query("select p from Citizen p where upper(p.address.city)=upper(:cityName)")
	Stream<Person> findByAddressCityIgnoreCase(@Param("cityName") String city);

	Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

//	@Query(value = "SELECT city, COUNT(*) AS population FROM java53.persons GROUP BY city ORDER BY population DESC", nativeQuery = true)
//	@Query("select p.address.city, count(p) from Citizen p group by p.address.city order by count(p) desc")
//	List<Object[]> getCityPopulation();

	@Query("select new telran.java53.person.dto.CityPopulationDto(p.address.city, count(p)) from Citizen p group by p.address.city order by count(p) desc")
	List<CityPopulationDto> getCitiesPopulation();

	Stream<Employee> findBySalaryBetween(int min, int max);

	Stream<Child> findChildrenBy();

}
