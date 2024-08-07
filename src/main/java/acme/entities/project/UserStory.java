
package acme.entities.project;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import acme.roles.Manager;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(indexes = {
//	@Index(columnList = "id"), @Index(columnList = "manager_id")
//})
public class UserStory extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(max = 75)
	private String				title;

	@NotBlank
	@Length(max = 100)
	private String				description;

	@Positive
	private int					estimatedCost;

	@NotBlank
	@Length(max = 100)
	private String				acceptanceCriteria;


	public enum Priority {
		MUST, SHOULD, COULD, WONT
	}


	@NotNull
	private Priority	priority;

	@URL
	@Length(max = 255)
	private String		link;

	private boolean		isDraft;

	@Valid
	@ManyToOne(optional = false)
	@NotNull
	private Manager		manager;

}
