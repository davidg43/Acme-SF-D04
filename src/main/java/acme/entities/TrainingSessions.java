
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrainingSessions extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "TS-[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	//dividir entre inicio y final
	//private Date				period;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				finalPeriod;

	@NotBlank
	@Length(max = 75)
	private String				location;

	@NotNull
	private String				email;

	@NotBlank
	@Length(max = 75)
	private String				instructor;

	@URL
	private String				link;

}
