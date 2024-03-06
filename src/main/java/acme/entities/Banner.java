
package acme.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.AssertTrue;
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
	@NotNull
	private String				picture;

	@NotNull
	@Length(max = 75)
	private String				slogan;

	@NotNull
	@URL
	private String				link;


	@Transient
	public Date period() {
		if (this.periodInit != null && this.periodEnd != null) {
			long diffInMillies = Math.abs(this.periodEnd.getTime() - this.periodInit.getTime());
			return new Date(diffInMillies);
		}
		return null;
	}

	@AssertTrue(message = "El periodo debe durar como minimo una semana, el periodo inicial debe comenzar antes del final y tambien debe empezar posteriormente a la fecha de instalacion o actualizacion")
	public boolean isPeriodValid() {
		if (this.periodEnd != null && this.periodInit != null && this.instantiationOrUpdateDate != null) {
			long diffInMillies = Math.abs(this.periodEnd.getTime() - this.periodInit.getTime());
			long diffInWeeks = diffInMillies / (1000 * 60 * 60 * 24 * 7);
			return diffInWeeks >= 1 && this.periodEnd.after(this.instantiationOrUpdateDate) && this.periodInit.after(this.instantiationOrUpdateDate) && this.periodEnd.after(this.periodInit);
		}
		return false;
	}

}
