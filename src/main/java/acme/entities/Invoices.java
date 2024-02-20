import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.checkerframework.common.aliasing.qual.Unique;
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
	@Unique
	@Pattern(regexp = "IN-[0-9]{4}-[0-9]{4}")
	private String				Code;

	@Past
	private Date				registration_time;

	private Date				due_date;

	@Positive
	private Integer				quantity;

	@PositiveOrZero
	private Integer				tax;

	private Integer				total_amount;

	@URL
	private String				link;
}
