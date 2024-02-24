
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Invoices extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "IN-[0-9]{4}-[0-9]{4}")
	private String				code;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank
	private Date				registrationTime;

	@NotBlank
	private Date				due_date;

	@NotBlank
	@Positive
	private Integer				quantity;

	@NotBlank
	@PositiveOrZero
	private Integer				tax;

	@NotBlank
	private Integer				totalAmount;

	@URL
	private String				link;
}
