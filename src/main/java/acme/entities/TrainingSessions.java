
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	private Date				iniDate;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Past
	private Date				finalDate;

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


	@Transient
	public Date period() {
		if (this.iniDate != null && this.finalDate != null) {
			long diffInMillies = Math.abs(this.finalDate.getTime() - this.iniDate.getTime());
			return new Date(diffInMillies);
		}
		return null;
	}

	@AssertTrue(message= "La fecha inicial de la sesion de entrenamiento debe empezar despues de la fecha de creacion del modulo de entrenamiento, el periodo debe ser minimo de una semsna y tambien tiene que empezar despues de la fecha de creacion del modulo de entrenamiento")
	public boolean isFinalPeriodAfterCreationMoment() {
		if (this.iniDate != null && this.finalDate != null && this.trainingModule != null && this.trainingModule.getCreationMoment() != null) {
			long diffInMillies = Math.abs(this.finalDate.getTime() - this.trainingModule.getCreationMoment().getTime());
			long diffInDays = diffInMillies / (1000 * 60 * 60 * 24);
			return diffInDays >= 7 && this.finalDate.after(this.trainingModule.getCreationMoment()) && this.iniDate.after(this.trainingModule.getCreationMoment());
		}
		return false;
	}

	//-----


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private TrainingModule trainingModule;

}
