
package acme.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import acme.client.data.AbstractForm;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SponsorDashBoard extends AbstractForm {

	private static final long	serialVersionUID	= 1L;

	@NotNull
	private int					totalSponsorShipsLessOrEqualThan21;

	@PositiveOrZero
	private double				averageAmountOfSponsorShips;

	@PositiveOrZero
	private double				deviationOfAmountOfSponsorShips;

	@PositiveOrZero
	private int					minimumAmountOfSponsorShips;

	@PositiveOrZero
	private int					maximumAmountOfSponsorShips;

	@PositiveOrZero
	private double				averageQuantityOfInvoices;

	@PositiveOrZero
	private double				deviationOfQuantityOfInvoices;

	@PositiveOrZero
	private int					minimumQuantityOfInvoices;

	@PositiveOrZero
	private int					maximumQuantityOfInvoices;

}
