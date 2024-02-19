
package acme.entities;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;

public class TrainingSessions extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@NotNull
	@Pattern(regexp = "TS-[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	private Date				period;

	@NotBlank
	@Size(max = 76, message = "Los detalles del entrenamiento deben tener menos de 76 caracteres")
	private String				location;

	@NotNull
	private String				email;

	@URL
	private String				link;

}
