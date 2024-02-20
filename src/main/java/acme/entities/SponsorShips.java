
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SponsorShips extends AbstractEntity {

	@NotBlank
	@Unique
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String	code;

	@Past
	private Date	moment;

	@Future
	//@Temporal(SpecDuration)
	private Date	duration;

	@Positive
	private Integer	amount;


	@NotBlank
	public enum Type {
		FINANCIAL, IN_KIND
	}


	@Email
	public String	contact;

	@URL
	public String	link;

}
