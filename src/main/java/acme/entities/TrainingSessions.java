
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
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

	@NotBlank
	@Length(max = 75)
	private String				instructor;

	@NotNull
	@Email
	private String				contactEmail;

	@URL
	private String				link;


	@AssertTrue(message = "Time period must be at least one week long and start after module creation moment")
	public boolean isFinalPeriodAfterCreationMoment() {
		long diffInMillies = Math.abs(this.finalPeriod.getTime() - this.trainingModule.getCreationMoment().getTime());
		long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
		return diffInDays >= 7 && this.finalPeriod.after(this.trainingModule.getCreationMoment());
	}

	//-----


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private TrainingModule trainingModule;

}
