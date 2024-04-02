
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.entities.project.Project;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Objective extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	private Date				instantiationMoment;

	@NotBlank
	@Length(max = 75)
	private String				title;

	@NotBlank
	@Length(max = 100)
	private String				description;


	private enum Priority {
		Low, Medium, High
	}


	@NotNull
	private Priority	priority;

	private boolean		criticalStatus;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date		initDuration;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date		endDuration;

	@URL
	private String		link;


	@Transient
	public Date duration() {
		if (this.initDuration != null && this.endDuration != null) {
			long diffInMillies = Math.abs(this.endDuration.getTime() - this.initDuration.getTime());
			return new Date(diffInMillies);
		}
		return null;
	}


	@Valid
	@ManyToOne
	private Project project;

}
