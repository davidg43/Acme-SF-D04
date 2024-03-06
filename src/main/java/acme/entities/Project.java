
package acme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import acme.roles.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{3}-[0-9]{4}")
	private String				code;

	@NotBlank
	@Length(max = 75)
	private String				title;

	@NotBlank
	@Length(max = 100)
	private String				abstractText;

	@AssertFalse(message = "This project has fatal errors")
	private boolean				hasFatalErrors;

	@Valid
	private Money				cost;

	@URL
	private String				link;

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	private Manager				manager;


	@AssertTrue(message = "Money should be positive or zero.")
	private boolean isCostValid() {
		int res = this.getCost().getAmount().compareTo(0.);
		return res >= 0 ? true : false;
	}

}
