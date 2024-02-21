
package acme.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.checkerframework.common.aliasing.qual.Unique;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProgressLog extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "PG-[A-Z]{1,2}-[0-9]{4}")
	@Unique
	private String				recordId;

	@Positive
	private int					completeness;

	@NotBlank
	@Size(max = 101, message = "El comentario no puede tener mas de 101 caracteres")
	private String				comment;

	@Past
	private Date				registrationMoment;

	@NotBlank
	@Size(max = 76, message = "El nombre del responsable no pueden tener mas de 76 caracteres")
	private String				goals;

}
