
package acme.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class Contract extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	@Unique
	private String				code;

	@Past
	private Date				moment;

	@NotBlank
	@Size(max = 76, message = "El nombre del proveedor no puede tener mas de 76 caracteres")
	private String				providerName;

	@NotBlank
	@Size(max = 76, message = "El nombre del cliente no puede tener mas de 76 caracteres")
	private String				customerName;

	@NotBlank
	@Size(max = 101, message = "Los objetivos no pueden tener mas de 101 caracteres")
	private String				goals;

	@NotNull
	@Positive
	private int					budget;

	@NotNull
	@JoinColumn(name = "project_cost")
	private int					cost;

}
