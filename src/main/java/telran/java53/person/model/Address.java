package telran.java53.person.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = -335865397457421859L;

	String city;
	String street;
	Integer building;
}
