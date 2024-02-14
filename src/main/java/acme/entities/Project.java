
package acme.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Size(min = 3, max = 3, message = "El código debe tener entre 3 y 3 caracteres")
	private String				code;

	@NotBlank
	@Size(max = 76, message = "El título debe tener menos de 76 caracteres")
	private String				title;

	@NotBlank
	@Size(max = 101, message = "El resumen debe tener menos de 101 caracteres")
	private String				abstractText;

	@NotNull
	private boolean				hasFatalErrors;

	@PositiveOrZero
	private int					cost;

	@NotNull
	private String				link;

}
