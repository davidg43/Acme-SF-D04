
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class AuditRecords extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Pattern(regexp = "AU-[0-9]{4}-[0-9]{3}")
	@NotBlank
	@Column(unique = true)
	private String				code;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank
	private Date				periodInit;

	@Temporal(TemporalType.TIMESTAMP)
	@NotBlank
	private Date				periodEnd;

	@NotBlank
	private Mark				mark;

	@URL
	private String				link;

	/*
	 * @ManyToOne
	 * private CodeAudits codeAudits;
	 */

}
