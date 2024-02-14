
package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserStory extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Size(max = 76, message = "El título debe tener menos de 76 caracteres")
	private String				title;

	@NotBlank
	@Size(max = 101, message = "La descripción debe tener menos de 101 caracteres")
	private String				description;

	@Positive
	private int					estimatedCostInHours;

	@NotBlank
	@Size(max = 101, message = "Los criterios de aceptación deben tener menos de 101 caracteres")
	private String				acceptanceCriteria;


	@NotBlank
	public enum Priority {
		MUST, SHOULD, COULD, WONT
	}


	private String link;

}
