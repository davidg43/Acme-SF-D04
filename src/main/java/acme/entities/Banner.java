
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Banner extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				instantiationOrUpdateDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				periodInit;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				periodEnd;


	@URL
	@NotBlank
	private String				picture;


	@NotBlank
	@Length(max = 75)
	private String				slogan;


	@NotBlank
	@URL
	private String				link;

}
