
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SponsorShips extends AbstractEntity {

	//Falta relacion a project ? 

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank
	private Date				moment;

	//@Temporal(SpecDuration)
	@NotBlank
	private Date				duration;

	@Positive
	@NotBlank
	private Money				amount;

	@NotBlank
	private Type				type;

	@Email
	private String				contact;

	@URL
	private String				link;

	@NotBlank
	@ManyToOne
	private Invoices			invoices;

}
