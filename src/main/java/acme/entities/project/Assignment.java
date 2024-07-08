
package acme.entities.project;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
//@Table(indexes = {
//	@Index(columnList = "project_id"), @Index(columnList = "user_story_id")
//})
public class Assignment extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Valid
	@ManyToOne(optional = false)
	@NotNull
	private Project				project;

	@Valid
	@ManyToOne(optional = false)
	@NotNull
	private UserStory			userStory;

}
