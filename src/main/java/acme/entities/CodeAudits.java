
package acme.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CodeAudits extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@Past
	@NotBlank
	private Date				execution;

	@NotBlank
	private Type				type;

	@NotBlank
	@Column(unique = true)
	private List<String>		correctiveActions;

	@NotBlank
	private Mark				mark;

	@URL
	private String				link;

	/*
	 * @OneToOne
	 * private Project project;
	 */

}
