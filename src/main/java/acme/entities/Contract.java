
package acme.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contract extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	@Column(unique = true)
	private String				code;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotBlank
	@Size(max = 75)
	private String				providerName;

	@NotBlank
	@Size(max = 75)
	private String				customerName;

	@NotBlank
	@Size(max = 100)
	private String				goals;

	@NotNull
	private Money				budget;

	@NotNull
	@Valid
	@ManyToOne
	private Project				project;


	@AssertTrue(message = "Budget should be less or equal than the project cost.")
	private boolean isBudgetValid() {
		return this.budget.getAmount() <= this.project.getCost().getAmount() ? true : false;
	}
}
