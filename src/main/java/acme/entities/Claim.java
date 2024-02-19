
package acme.entities;

import java.time.Instant;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Claim extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "C-[0-9]{4}", message = "El código debe seguir el patrón C-####")
	private String				code;

	@NotNull
	private Instant				instantiationMoment;

	@NotBlank
	@Size(max = 76, message = "El título debe tener menos de 76 caracteres")
	private String				heading;

	@NotBlank
	@Size(max = 101, message = "La descripción debe tener menos de 101 caracteres")
	private String				description;

	@NotBlank
	@Size(max = 101, message = "El departamento debe tener menos de 101 caracteres")
	private String				department;

	private String				emailAddress;

	private String				link;
}
