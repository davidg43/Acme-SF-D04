
package acme.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProgressLog extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "PG-[A-Z]{1,2}-[0-9]{4}")
	@Column(unique = true)
	private String				recordId;

	private int					completeness;

	@NotBlank
	@Size(max = 100)
	private String				comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				registrationMoment;

	@NotBlank
	@Size(max = 75)
	private String				reponsiblePerson;

}
