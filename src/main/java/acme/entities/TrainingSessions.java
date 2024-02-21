
package acme.entities;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.checkerframework.common.aliasing.qual.Unique;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;

public class TrainingSessions extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Unique
	@Pattern(regexp = "TS-[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	private Date				period;

	@NotBlank
	@Length(max = 76)
	private String				location;

	@NotNull
	private String				email;

	@URL
	private String				link;

}
