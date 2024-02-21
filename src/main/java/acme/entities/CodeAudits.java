
package acme.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.checkerframework.common.aliasing.qual.Unique;
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
	@Unique
	@Pattern(regexp = "[A-Z]{1,3}-[0-9]{3}")
	private String				code;

	@Past
	@NotBlank
	private Date				execution;


	@NotBlank
	public enum type {
		STATIC, DYNAMIC
	}


	@NotBlank
	@Column(unique = true)
	private List<String>	correctiveActions;

	@NotBlank
	@OneToOne
	private AuditRecords	mark;

	@URL
	private String			link;

}
