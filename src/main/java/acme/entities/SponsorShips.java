
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SponsorShips extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				moment;

	//@Temporal(SpecDuration)
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				duration;

	@Valid
	@NotNull
	private Money				amount;

	@NotNull
	private Type				type;

	@Email
	private String				contact;

	@URL
	private String				link;

	@NotNull
	@ManyToOne
	@Valid
	private Invoices			invoices;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project				project;

}
