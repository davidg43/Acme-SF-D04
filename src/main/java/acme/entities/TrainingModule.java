
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.checkerframework.common.aliasing.qual.Unique;
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
	@Unique
	@Pattern(regexp = "“[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@Past
	@NotNull
	private Date				creationMoment;

	@NotBlank
	@Length(max = 101)
	private String				details;

	@NotNull
	private DifficultyLevel		difficultyLevel;

	private Date				updateMoment;

	@URL
	private String				link;

	private Date				totalTime;

}
