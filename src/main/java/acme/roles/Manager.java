
package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manager extends AbstractRole {
	// Serialisation identifier -----------------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Size(max = 75)
	private String				degree;

	@NotBlank
	@Size(max = 100)
	private String				overview;

	@NotBlank
	@Size(max = 100)
	private String				certifications;

	@URL
	private String				furtherInformationLink;
}
