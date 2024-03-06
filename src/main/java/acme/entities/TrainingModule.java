
package acme.entities;

import java.util.Date;

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
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TrainingModule extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creationMoment;

	@NotBlank
	@Length(max = 100)
	private String				details;


	private enum DifficultyLevel {
		Basic, Intermediate, Advanced
	}


	private DifficultyLevel	difficultyLevel;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date			updateMoment;

	@URL
	private String			link;

	@Positive
	private int				totalTime;


	@AssertTrue(message = "El momento de actualizacion del modulo debe ser posterior a su momento de creacion")
	public boolean isUpdateMomentAfterCreationMoment() {
		return this.updateMoment != null && this.creationMoment != null && this.updateMoment.after(this.creationMoment);
	}

	//-----------------


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Project project;

}
