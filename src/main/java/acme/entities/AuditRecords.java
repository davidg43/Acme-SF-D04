
package acme.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class AuditRecords extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@Pattern(regexp = "AU-[0-9]{4}-[0-9]{3}")
	@NotBlank
	@Column(unique = true)
	private String				code;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				periodInit;

	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date				periodEnd;

	@NotNull
	private Mark				mark;

	@URL
	private String				link;

	@NotNull
	@Valid
	@ManyToOne
	private CodeAudits			codeAudits;


	@Transient
	public Date period() {
		if (this.periodInit != null && this.periodEnd != null) {
			long diffInMillies = Math.abs(this.periodEnd.getTime() - this.periodInit.getTime());
			return new Date(diffInMillies);
		}
		return null;
	}

	@AssertTrue(message = "El periodo debe durar como minimo una hora, el periodo inicial debe comenzar antes del final y tambien debe empezar posteriormente a la fecha de ejecucion de la auditoría de codigo")
	public boolean isPeriodValid() {
		if (this.periodEnd != null && this.periodInit != null && this.codeAudits != null && this.codeAudits.getExecution() != null) {
			long diffInMillies = Math.abs(this.periodEnd.getTime() - this.periodInit.getTime());
			long diffInHours = diffInMillies / (1000 * 60 * 60);
			return diffInHours >= 1 && this.periodEnd.after(this.codeAudits.getExecution()) && this.periodInit.after(this.codeAudits.getExecution()) && this.periodEnd.after(this.periodInit);
		}
		return false;
	}

}
